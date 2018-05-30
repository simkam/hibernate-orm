./gradlew :hibernate-testing:build => 66 errors

cd hibernate-testing/src/main/java/org/hibernate/testing/cache/
git diff HEAD upstream/master -- ./ > hibernate-testing.patch
git apply hibernate-testing.patch
rm hibernate-testing.patch

./gradlew :hibernate-testing:build => 2 errors

BaseCoreFunctionalTestCase and BaseNonConfigCoreFunctionalTestCase
replace import org.hibernate.resource.transaction.TransactionCoordinator with import org.hibernate.resource.transaction.spi.TransactionCoordinator

./gradlew :hibernate-testing:build => 12 

org.hibernate.testing.cache.StrategyRegistrationProviderImpl
cast SimpleStrategyRegistrationImpl to StrategyRegistration which it implements (weird)

cd hibernate-testing/src/main/java/org/hibernate/testing/boot/
git diff HEAD upstream/master -- ./ > hibernate-testing.patch
git apply hibernate-testing.patch

other changes - code, disable animalsniffer, disable checkstyle

create custom version of hibernate-testing

./gradlew :hibernate-testing:build
./gradlew :hibernate-testing:publishToMavenLocal
