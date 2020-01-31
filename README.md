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
##line number 7 of feature file to be update with email id
 | email      | placeholder@gmail.com  |

## Acceptance Test
After updating create-user.feature file run the below command

./gradlew acceptanceTest

## Reports
Reports can be found in build/reports/tests/test/index.html

## @IntegrationTest
Runs the user journey/E2E scenarios and passes the data like access token and id from create user endpoint to another token

## @ComponentTest
Runs the component test cases and covers different status codes



