/*
 * Hibernate, Relational Persistence for Idiomatic Java
 *
 * Copyright (c) 2006-2011, Red Hat Inc. or third-party contributors as
 * indicated by the @author tags or express copyright attribution
 * statements applied by the authors.  All third-party contributions are
 * distributed under license by Red Hat Inc.
 *
 * This copyrighted material is made available to anyone wishing to use, modify,
 * copy, or redistribute it subject to the terms and conditions of the GNU
 * Lesser General Public License, as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License
 * for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this distribution; if not, write to:
 * Free Software Foundation, Inc.
 * 51 Franklin Street, Fifth Floor
 * Boston, MA  02110-1301  USA
 */
package org.hibernate.test.legacy;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.dialect.DB2Dialect;
import org.hibernate.dialect.HSQLDialect;
import org.hibernate.dialect.InterbaseDialect;
import org.hibernate.dialect.MySQLDialect;
import org.hibernate.dialect.Oracle8iDialect;
import org.hibernate.dialect.PointbaseDialect;
import org.hibernate.dialect.PostgreSQL81Dialect;
import org.hibernate.dialect.PostgreSQLDialect;
import org.hibernate.dialect.SAPDBDialect;
import org.hibernate.dialect.Sybase11Dialect;
import org.hibernate.dialect.SybaseASE15Dialect;
import org.hibernate.dialect.SybaseDialect;
import org.jboss.logging.Logger;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.assertTrue;

public class FooBarTest extends LegacyTestCase {
	private static final Logger log = Logger.getLogger( FooBarTest.class );

	@Override
	public String[] getMappings() {
		return new String[] {
			"legacy/FooBar.hbm.xml",
			"legacy/Baz.hbm.xml",
			"legacy/Qux.hbm.xml",
			"legacy/Glarch.hbm.xml",
			"legacy/Fum.hbm.xml",
			"legacy/Fumm.hbm.xml",
			"legacy/Fo.hbm.xml",
			"legacy/One.hbm.xml",
			"legacy/Many.hbm.xml",
			"legacy/Immutable.hbm.xml",
			"legacy/Fee.hbm.xml",
			"legacy/Vetoer.hbm.xml",
			"legacy/Holder.hbm.xml",
			"legacy/Location.hbm.xml",
			"legacy/Stuff.hbm.xml",
			"legacy/Container.hbm.xml",
			"legacy/Simple.hbm.xml",
			"legacy/XY.hbm.xml"
		};
	}

	@Test
	public void testCollectionsInSelect() throws Exception {
		Session s = openSession();
		Transaction t = s.beginTransaction();
		Foo[] foos = new Foo[] { null, new Foo() };
		s.save( foos[1] );
		Baz baz = new Baz();
		baz.setDefaults();
		baz.setFooArray(foos);
		s.save(baz);
		Baz baz2 = new Baz();
		baz2.setDefaults();
		s.save(baz2);

		Bar bar = new Bar();
		bar.setBaz(baz);
		s.save(bar);

		//s.find("select max( elements(bar.baz.fooArray) ) from Bar as bar");
		//The following test is disabled for databases with no subselects...also for Interbase (not sure why).
		if ( !(getDialect() instanceof MySQLDialect) && !(getDialect() instanceof HSQLDialect) /*&& !(dialect instanceof MckoiDialect)*/ && !(getDialect() instanceof SAPDBDialect) && !(getDialect() instanceof PointbaseDialect) )  {
			if ( !(getDialect() instanceof DB2Dialect) &&  !(getDialect() instanceof Oracle8iDialect ) && !( getDialect() instanceof SybaseDialect ) && !( getDialect() instanceof Sybase11Dialect ) && !( getDialect() instanceof SybaseASE15Dialect ) && !( getDialect() instanceof PostgreSQLDialect ) && !(getDialect() instanceof PostgreSQL81Dialect)) {
				// SybaseAnywhereDialect supports implicit conversions from strings to ints
				s.createQuery(
						"select max( elements(bar.baz.fooArray) ) from Bar as bar, bar.component.glarch.proxyArray as g where g.id in indices(bar.baz.fooArray)"
				).list();
			}
		}

		s.delete(baz);
		s.delete(baz2);
		s.delete( foos[1] );
		t.commit();
		s.close();
	}

    @Test
    public void testAutoFlushCollections() throws Exception {
        Session s = openSession();
        Transaction tx = s.beginTransaction();
        Baz baz = new Baz();
        baz.setDefaults();
        s.save(baz);
        tx.commit();
        s.close();

        s = openSession();
        tx = s.beginTransaction();
        baz = (Baz) s.load(Baz.class, baz.getCode());
        baz.getStringArray()[0] = "bark";
        Iterator i = s.createQuery( "select elements(baz.stringArray) from Baz baz" ).iterate();
        boolean found = false;
        while ( i.hasNext() ) {
            if ( "bark".equals( i.next() ) ) found = true;
        }
        assertTrue(found);
        baz.setStringArray(null);
        i = s.createQuery( "select distinct elements(baz.stringArray) from Baz baz" ).iterate();
        assertTrue( !i.hasNext() );
        baz.setStringArray( new String[] { "foo", "bar" } );
        i = s.createQuery( "select elements(baz.stringArray) from Baz baz" ).iterate();
        assertTrue( i.hasNext() );

        Foo foo = new Foo();
        s.save(foo);
        s.flush();
        baz.setFooArray( new Foo[] {foo} );

        i = s.createQuery( "select foo from Baz baz join baz.fooArray foo" ).iterate();
        found = false;
        while ( i.hasNext() ) {
            if ( foo==i.next() ) found = true;
        }
        assertTrue(found);

        baz.getFooArray()[0] = null;
        i = s.createQuery( "select foo from Baz baz join baz.fooArray foo" ).iterate();
        assertTrue( !i.hasNext() );
        baz.getFooArray()[0] = foo;
        i = s.createQuery( "select elements(baz.fooArray) from Baz baz" ).iterate();
        assertTrue( i.hasNext() );

        if ( !(getDialect() instanceof MySQLDialect)
                && !(getDialect() instanceof HSQLDialect)
                && !(getDialect() instanceof InterbaseDialect)
                && !(getDialect() instanceof PointbaseDialect)
                && !(getDialect() instanceof SAPDBDialect) )  {
            baz.getFooArray()[0] = null;
            i = s.createQuery( "from Baz baz where ? in elements(baz.fooArray)" )
                    .setParameter( 0, foo, s.getTypeHelper().entity( Foo.class ) )
                    .iterate();
            assertTrue( !i.hasNext() );
            baz.getFooArray()[0] = foo;
            i = s.createQuery( "select foo from Foo foo where foo in (select elt from Baz baz join baz.fooArray elt)" )
                    .iterate();
            assertTrue( i.hasNext() );
        }
        s.delete(foo);
        s.delete(baz);
        tx.commit();
        s.close();
    }
}
