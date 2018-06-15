package org.hibernate.test;

import java.io.InputStream;
import java.io.Reader;
import java.io.Serializable;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.NClob;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import org.hibernate.CacheMode;
import org.hibernate.Criteria;
import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.Interceptor;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.collection.spi.PersistentCollection;
import org.hibernate.engine.jdbc.connections.spi.JdbcConnectionAccess;
import org.hibernate.engine.jdbc.spi.JdbcCoordinator;
import org.hibernate.engine.query.spi.sql.NativeSQLQuerySpecification;
import org.hibernate.engine.spi.EntityKey;
import org.hibernate.engine.spi.LoadQueryInfluencers;
import org.hibernate.engine.spi.NamedQueryDefinition;
import org.hibernate.engine.spi.NamedSQLQueryDefinition;
import org.hibernate.engine.spi.PersistenceContext;
import org.hibernate.engine.spi.QueryParameters;
import org.hibernate.engine.spi.SessionEventListenerManager;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.loader.custom.CustomQuery;
import org.hibernate.persister.entity.EntityPersister;
import org.hibernate.resource.transaction.TransactionCoordinator;
import org.hibernate.type.descriptor.WrapperOptions;
import org.hibernate.usertype.UserType;

public class App {

    public static void main(String[] args) {
        TestType t = new TestType();
        TestPreparedStatement tp = new TestPreparedStatement();
        TestSessionImplementor tsi = new TestSessionImplementor();

        try {
            t.nullSafeSet(tp, "value", 1, tsi);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static class TestType implements UserType {

        @Override
        public int[] sqlTypes() {
            return new int[0];
        }

        @Override
        public Class returnedClass() {
            return null;
        }

        @Override
        public boolean equals(Object x, Object y) throws HibernateException {
            return false;
        }

        @Override
        public int hashCode(Object x) throws HibernateException {
            return 0;
        }

        @Override
        public Object nullSafeGet(ResultSet rs, String[] names, SessionImplementor session, Object owner) throws HibernateException, SQLException {
            return null;
        }

        @Override
        public void nullSafeSet(PreparedStatement st, Object value, int index, SessionImplementor session) throws HibernateException, SQLException {

        }

        @Override
        public Object deepCopy(Object value) throws HibernateException {
            return null;
        }

        @Override
        public boolean isMutable() {
            return false;
        }

        @Override
        public Serializable disassemble(Object value) throws HibernateException {
            return null;
        }

        @Override
        public Object assemble(Serializable cached, Object owner) throws HibernateException {
            return null;
        }

        @Override
        public Object replace(Object original, Object target, Object owner) throws HibernateException {
            return null;
        }
    }

    public static class TestPreparedStatement implements PreparedStatement {

        @Override
        public ResultSet executeQuery() throws SQLException {
            return null;
        }

        @Override
        public int executeUpdate() throws SQLException {
            return 0;
        }

        @Override
        public void setNull(int parameterIndex, int sqlType) throws SQLException {

        }

        @Override
        public void setBoolean(int parameterIndex, boolean x) throws SQLException {

        }

        @Override
        public void setByte(int parameterIndex, byte x) throws SQLException {

        }

        @Override
        public void setShort(int parameterIndex, short x) throws SQLException {

        }

        @Override
        public void setInt(int parameterIndex, int x) throws SQLException {

        }

        @Override
        public void setLong(int parameterIndex, long x) throws SQLException {

        }

        @Override
        public void setFloat(int parameterIndex, float x) throws SQLException {

        }

        @Override
        public void setDouble(int parameterIndex, double x) throws SQLException {

        }

        @Override
        public void setBigDecimal(int parameterIndex, BigDecimal x) throws SQLException {

        }

        @Override
        public void setString(int parameterIndex, String x) throws SQLException {

        }

        @Override
        public void setBytes(int parameterIndex, byte[] x) throws SQLException {

        }

        @Override
        public void setDate(int parameterIndex, Date x) throws SQLException {

        }

        @Override
        public void setTime(int parameterIndex, Time x) throws SQLException {

        }

        @Override
        public void setTimestamp(int parameterIndex, Timestamp x) throws SQLException {

        }

        @Override
        public void setAsciiStream(int parameterIndex, InputStream x, int length) throws SQLException {

        }

        @Override
        public void setUnicodeStream(int parameterIndex, InputStream x, int length) throws SQLException {

        }

        @Override
        public void setBinaryStream(int parameterIndex, InputStream x, int length) throws SQLException {

        }

        @Override
        public void clearParameters() throws SQLException {

        }

        @Override
        public void setObject(int parameterIndex, Object x, int targetSqlType) throws SQLException {

        }

        @Override
        public void setObject(int parameterIndex, Object x) throws SQLException {

        }

        @Override
        public boolean execute() throws SQLException {
            return false;
        }

        @Override
        public void addBatch() throws SQLException {

        }

        @Override
        public void setCharacterStream(int parameterIndex, Reader reader, int length) throws SQLException {

        }

        @Override
        public void setRef(int parameterIndex, Ref x) throws SQLException {

        }

        @Override
        public void setBlob(int parameterIndex, Blob x) throws SQLException {

        }

        @Override
        public void setClob(int parameterIndex, Clob x) throws SQLException {

        }

        @Override
        public void setArray(int parameterIndex, Array x) throws SQLException {

        }

        @Override
        public ResultSetMetaData getMetaData() throws SQLException {
            return null;
        }

        @Override
        public void setDate(int parameterIndex, Date x, Calendar cal) throws SQLException {

        }

        @Override
        public void setTime(int parameterIndex, Time x, Calendar cal) throws SQLException {

        }

        @Override
        public void setTimestamp(int parameterIndex, Timestamp x, Calendar cal) throws SQLException {

        }

        @Override
        public void setNull(int parameterIndex, int sqlType, String typeName) throws SQLException {

        }

        @Override
        public void setURL(int parameterIndex, URL x) throws SQLException {

        }

        @Override
        public ParameterMetaData getParameterMetaData() throws SQLException {
            return null;
        }

        @Override
        public void setRowId(int parameterIndex, RowId x) throws SQLException {

        }

        @Override
        public void setNString(int parameterIndex, String value) throws SQLException {

        }

        @Override
        public void setNCharacterStream(int parameterIndex, Reader value, long length) throws SQLException {

        }

        @Override
        public void setNClob(int parameterIndex, NClob value) throws SQLException {

        }

        @Override
        public void setClob(int parameterIndex, Reader reader, long length) throws SQLException {

        }

        @Override
        public void setBlob(int parameterIndex, InputStream inputStream, long length) throws SQLException {

        }

        @Override
        public void setNClob(int parameterIndex, Reader reader, long length) throws SQLException {

        }

        @Override
        public void setSQLXML(int parameterIndex, SQLXML xmlObject) throws SQLException {

        }

        @Override
        public void setObject(int parameterIndex, Object x, int targetSqlType, int scaleOrLength) throws SQLException {

        }

        @Override
        public void setAsciiStream(int parameterIndex, InputStream x, long length) throws SQLException {

        }

        @Override
        public void setBinaryStream(int parameterIndex, InputStream x, long length) throws SQLException {

        }

        @Override
        public void setCharacterStream(int parameterIndex, Reader reader, long length) throws SQLException {

        }

        @Override
        public void setAsciiStream(int parameterIndex, InputStream x) throws SQLException {

        }

        @Override
        public void setBinaryStream(int parameterIndex, InputStream x) throws SQLException {

        }

        @Override
        public void setCharacterStream(int parameterIndex, Reader reader) throws SQLException {

        }

        @Override
        public void setNCharacterStream(int parameterIndex, Reader value) throws SQLException {

        }

        @Override
        public void setClob(int parameterIndex, Reader reader) throws SQLException {

        }

        @Override
        public void setBlob(int parameterIndex, InputStream inputStream) throws SQLException {

        }

        @Override
        public void setNClob(int parameterIndex, Reader reader) throws SQLException {

        }

        @Override
        public ResultSet executeQuery(String sql) throws SQLException {
            return null;
        }

        @Override
        public int executeUpdate(String sql) throws SQLException {
            return 0;
        }

        @Override
        public void close() throws SQLException {

        }

        @Override
        public int getMaxFieldSize() throws SQLException {
            return 0;
        }

        @Override
        public void setMaxFieldSize(int max) throws SQLException {

        }

        @Override
        public int getMaxRows() throws SQLException {
            return 0;
        }

        @Override
        public void setMaxRows(int max) throws SQLException {

        }

        @Override
        public void setEscapeProcessing(boolean enable) throws SQLException {

        }

        @Override
        public int getQueryTimeout() throws SQLException {
            return 0;
        }

        @Override
        public void setQueryTimeout(int seconds) throws SQLException {

        }

        @Override
        public void cancel() throws SQLException {

        }

        @Override
        public SQLWarning getWarnings() throws SQLException {
            return null;
        }

        @Override
        public void clearWarnings() throws SQLException {

        }

        @Override
        public void setCursorName(String name) throws SQLException {

        }

        @Override
        public boolean execute(String sql) throws SQLException {
            return false;
        }

        @Override
        public ResultSet getResultSet() throws SQLException {
            return null;
        }

        @Override
        public int getUpdateCount() throws SQLException {
            return 0;
        }

        @Override
        public boolean getMoreResults() throws SQLException {
            return false;
        }

        @Override
        public void setFetchDirection(int direction) throws SQLException {

        }

        @Override
        public int getFetchDirection() throws SQLException {
            return 0;
        }

        @Override
        public void setFetchSize(int rows) throws SQLException {

        }

        @Override
        public int getFetchSize() throws SQLException {
            return 0;
        }

        @Override
        public int getResultSetConcurrency() throws SQLException {
            return 0;
        }

        @Override
        public int getResultSetType() throws SQLException {
            return 0;
        }

        @Override
        public void addBatch(String sql) throws SQLException {

        }

        @Override
        public void clearBatch() throws SQLException {

        }

        @Override
        public int[] executeBatch() throws SQLException {
            return new int[0];
        }

        @Override
        public Connection getConnection() throws SQLException {
            return null;
        }

        @Override
        public boolean getMoreResults(int current) throws SQLException {
            return false;
        }

        @Override
        public ResultSet getGeneratedKeys() throws SQLException {
            return null;
        }

        @Override
        public int executeUpdate(String sql, int autoGeneratedKeys) throws SQLException {
            return 0;
        }

        @Override
        public int executeUpdate(String sql, int[] columnIndexes) throws SQLException {
            return 0;
        }

        @Override
        public int executeUpdate(String sql, String[] columnNames) throws SQLException {
            return 0;
        }

        @Override
        public boolean execute(String sql, int autoGeneratedKeys) throws SQLException {
            return false;
        }

        @Override
        public boolean execute(String sql, int[] columnIndexes) throws SQLException {
            return false;
        }

        @Override
        public boolean execute(String sql, String[] columnNames) throws SQLException {
            return false;
        }

        @Override
        public int getResultSetHoldability() throws SQLException {
            return 0;
        }

        @Override
        public boolean isClosed() throws SQLException {
            return false;
        }

        @Override
        public void setPoolable(boolean poolable) throws SQLException {

        }

        @Override
        public boolean isPoolable() throws SQLException {
            return false;
        }

        @Override
        public void closeOnCompletion() throws SQLException {

        }

        @Override
        public boolean isCloseOnCompletion() throws SQLException {
            return false;
        }

        @Override
        public <T> T unwrap(Class<T> iface) throws SQLException {
            return null;
        }

        @Override
        public boolean isWrapperFor(Class<?> iface) throws SQLException {
            return false;
        }
    }

    public static class TestSessionImplementor implements SessionImplementor {

        @Override
        public String getTenantIdentifier() {
            return null;
        }

        @Override
        public JdbcConnectionAccess getJdbcConnectionAccess() {
            return null;
        }

        @Override
        public EntityKey generateEntityKey(Serializable id, EntityPersister persister) {
            return null;
        }

        @Override
        public Interceptor getInterceptor() {
            return null;
        }

        @Override
        public void setAutoClear(boolean enabled) {

        }

        @Override
        public void disableTransactionAutoJoin() {

        }

        @Override
        public boolean isTransactionInProgress() {
            return false;
        }

        @Override
        public void initializeCollection(PersistentCollection collection, boolean writing) throws HibernateException {

        }

        @Override
        public Object internalLoad(String entityName, Serializable id, boolean eager, boolean nullable) throws HibernateException {
            return null;
        }

        @Override
        public Object immediateLoad(String entityName, Serializable id) throws HibernateException {
            return null;
        }

        @Override
        public long getTimestamp() {
            return 0;
        }

        @Override
        public SessionFactoryImplementor getFactory() {
            return null;
        }

        @Override
        public List list(String query, QueryParameters queryParameters) throws HibernateException {
            return null;
        }

        @Override
        public Iterator iterate(String query, QueryParameters queryParameters) throws HibernateException {
            return null;
        }

        @Override
        public ScrollableResults scroll(String query, QueryParameters queryParameters) throws HibernateException {
            return null;
        }

        @Override
        public ScrollableResults scroll(Criteria criteria, ScrollMode scrollMode) {
            return null;
        }

        @Override
        public List list(Criteria criteria) {
            return null;
        }

        @Override
        public List listFilter(Object collection, String filter, QueryParameters queryParameters) throws HibernateException {
            return null;
        }

        @Override
        public Iterator iterateFilter(Object collection, String filter, QueryParameters queryParameters) throws HibernateException {
            return null;
        }

        @Override
        public EntityPersister getEntityPersister(String entityName, Object object) throws HibernateException {
            return null;
        }

        @Override
        public Object getEntityUsingInterceptor(EntityKey key) throws HibernateException {
            return null;
        }

        @Override
        public Serializable getContextEntityIdentifier(Object object) {
            return null;
        }

        @Override
        public String bestGuessEntityName(Object object) {
            return null;
        }

        @Override
        public String guessEntityName(Object entity) throws HibernateException {
            return null;
        }

        @Override
        public Object instantiate(String entityName, Serializable id) throws HibernateException {
            return null;
        }

        @Override
        public List listCustomQuery(CustomQuery customQuery, QueryParameters queryParameters) throws HibernateException {
            return null;
        }

        @Override
        public ScrollableResults scrollCustomQuery(CustomQuery customQuery, QueryParameters queryParameters) throws HibernateException {
            return null;
        }

        @Override
        public List list(NativeSQLQuerySpecification spec, QueryParameters queryParameters) throws HibernateException {
            return null;
        }

        @Override
        public ScrollableResults scroll(NativeSQLQuerySpecification spec, QueryParameters queryParameters) {
            return null;
        }

        @Override
        public int getDontFlushFromFind() {
            return 0;
        }

        @Override
        public PersistenceContext getPersistenceContext() {
            return null;
        }

        @Override
        public int executeUpdate(String query, QueryParameters queryParameters) throws HibernateException {
            return 0;
        }

        @Override
        public int executeNativeUpdate(NativeSQLQuerySpecification specification, QueryParameters queryParameters) throws HibernateException {
            return 0;
        }

        @Override
        public CacheMode getCacheMode() {
            return null;
        }

        @Override
        public void setCacheMode(CacheMode cm) {

        }

        @Override
        public boolean isOpen() {
            return false;
        }

        @Override
        public boolean isConnected() {
            return false;
        }

        @Override
        public FlushMode getFlushMode() {
            return null;
        }

        @Override
        public void setFlushMode(FlushMode fm) {

        }

        @Override
        public Connection connection() {
            return null;
        }

        @Override
        public void flush() {

        }

        @Override
        public Query getNamedQuery(String name) {
            return null;
        }

        @Override
        public Query getNamedSQLQuery(String name) {
            return null;
        }

        @Override
        public boolean isEventSource() {
            return false;
        }

        @Override
        public void afterScrollOperation() {

        }

        @Override
        public TransactionCoordinator getTransactionCoordinator() {
            return null;
        }

        @Override
        public JdbcCoordinator getJdbcCoordinator() {
            return null;
        }

        @Override
        public boolean isClosed() {
            return false;
        }

        @Override
        public boolean shouldAutoClose() {
            return false;
        }

        @Override
        public boolean isAutoCloseSessionEnabled() {
            return false;
        }

        @Override
        public LoadQueryInfluencers getLoadQueryInfluencers() {
            return null;
        }

        @Override
        public Query createQuery(NamedQueryDefinition namedQueryDefinition) {
            return null;
        }

        @Override
        public SQLQuery createSQLQuery(NamedSQLQueryDefinition namedQueryDefinition) {
            return null;
        }

        @Override
        public SessionEventListenerManager getEventListenerManager() {
            return null;
        }

        @Override
        public <T> T execute(Callback<T> callback) {
            return null;
        }

        @Override
        public WrapperOptions getWrapperOptions() {
            return null;
        }
    }

}
