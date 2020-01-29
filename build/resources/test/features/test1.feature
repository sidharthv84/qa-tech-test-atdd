Feature: Register user for Application
@integration
Scenario: User registration returns success response - with valid user info
  Given User registration Api is up and running
  When I submit a request to perform registration with below info
  |email      | Irelandnot20@gmail.com |
  |last_name  | Integration            |
  |first_name | Testing12345           |
  |Password   | Test@1234              |
  Then Validate the response body

