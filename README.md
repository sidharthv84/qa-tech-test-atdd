# qa-tech-test-atdd

#Description
Automated tests for Rails React App

# Prerequisites
- Git
- Gradle
- JRE 8

# How to build
The project can be built using ./gradlew clean build

# How to run Acceptance Test

## Add email_id, first_name, last_name and password in create-user.feature file
Update create-user.feature file with unique email_id

## Acceptance Test
After updating create-user.feature file run the below command

./gradlew acceptanceTest

## Reports
Reports can be found in build/reports/tests/test/index.html





