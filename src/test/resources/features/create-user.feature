Feature: Register user for Application

  @integrationTest
  Scenario Outline: Successful request - Create User API returns success response - with valid user info
    Given User registration Api is up and running
    When I submit a request to perform registration with below info
      | email      | place_holder@gmail.com |
      | last_name  | Integration            |
      | first_name | Testing12345           |
      | password   | Test@1234              |
    Then I validate "<status>" for the request
    Then Validate the response body
    Examples:
      | status |
      | 200    |

  @componentTest
  Scenario Outline: Bad request - Create User API returns error response - with invalid email address
    Given User registration Api is up and running
    When I submit a request to perform registration with below info
      | <field> | <invalid_value> |
    Then I validate "<status>" for the request
    And Validate the error response "<error_message>"
    Examples:
      | field | invalid_value | status | error_message                         |
      | email | blank         | 422    | Email is invalid                      |
      | email | null          | 422    | Email is invalid                      |
      | email | 12.test       | 422    | Email is invalid                      |
      | email | 123456        | 422    | Email is invalid                      |
      | email | 123456**1234  | 422    | Email is invalid                      |
      | email | 123456!1234   | 422    | Email is invalid                      |
      | email |               | 422    | Email can't be blank                  |
      | email | 1             | 422    | Email is invalid                      |
      | email | **            | 422    | Email is invalid                      |
      | email | test@123      | 422    | A user with that email already exists |

  @componentTest
  Scenario Outline: Bad request - Create User API returns error response - with invalid password
    Given User registration Api is up and running
    When I submit a request to perform registration with password and below info "<value>"
      | email      | test@gmail.com |
      | last_name  | Integration            |
      | first_name | Test                   |
      | password   | <value>                |
    Then I validate "<status>" for the request
    Examples:
      | status | value |
      | 422    | test  |
      | 422    | blank |
      | 422    | null  |
      | 422    | NULL  |
      | 422    | NULL  |

  @componentTest
  Scenario Outline: Bad request - Create User API returns error response - with invalid first name
    Given User registration Api is up and running
    When I submit a request to perform registration with below info
      | email      | test@gmail.com |
      | last_name  | Integration            |
      | first_name |                        |
      | password   | Test@1234              |
    Then I validate "<status>" for the request
    Examples:
      | status |
      | 422    |

  @todo
  Scenario Outline: Bad request - Create User API returns error response - with invalid first name
    Given User registration Api is up and running
    When I submit a request to perform registration with below info
      | email      | test@gmail.com |
      | last_name  |                        |
      | first_name | Test                   |
      | password   | Test@1234              |
    Then I validate "<status>" for the request
    Examples:
      | status |
      | 422    |

  @todo
  Scenario Outline: Bad request - Not allowed methods
    Given User registration Api is up and running
    When I submit a request to api to perform registration with below "<methods>"
      | email      | test@gmail.com |
      | last_name  | Integration            |
      | first_name | Test                   |
      | password   | 12345678               |
    Then I validate "<status>" for the request
    Examples:
      | status | methods  |
      | 404    | get      |
      | 404    | patch    |
      | 404    | delete   |
      | 404    | put      |

  @todo
  Scenario Outline: Bad request - Invalid headers
    Given User registration Api is up and running
    When I submit a request to api to perform registration with below "<headers>"
      | email      | test@gmail.com |
      | last_name  | Integration            |
      | first_name | Test                   |
      | password   | 12345678               |
    Then I validate "<status>" for the request
    And Validate the error response "<error_message>"
    Examples:
      | status | headers  |error_message|
      | 401    | application/xml|This client is not authorized to perform that action.|
      | 401    |                |This client is not authorized to perform that action.|

  @todo
  Scenario Outline: Bad request - Internal Server errors - API is down
    Given User registration Api is up and running
    When I submit a request to api to perform registration with below "<headers>"
      | email      | test@gmail.com |
      | last_name  | Integration            |
      | first_name | Test                   |
      | password   | 12345678               |
    Then I validate "<status>" for the request
    And Validate the error response "<error_message>"
    Examples:
      | status | headers  |error_message|
      | 500    | application/xml|This client is not authorized to perform that action.|

    @todo
    Scenario Outline: Bad request - 404 - wrong end point
      Given User registration Api is up and running
      When I submit a request to api to perform registration with below "<endpoint>"
        | email      | test@gmail.com |
        | last_name  | Integration            |
        | first_name | Test                   |
        | password   | 12345678               |
      Then I validate "<status>" for the request
      Examples:
        | status | endpoint  |
        | 404    | https://showoff-rails-react-production.herokuapp.com/api/ |

  @todo
  Scenario Outline: Revoke Access Token and Refresh Access Token
    Given User registration Api is up and running
    When I submit a request to perform registration with below info
      | email      | placeholder@gmail.com  |
      | last_name  | Integration            |
      | first_name | Testing12345           |
      | password   | Test@1234              |
    Then I validate "<status>" for the request
    Then Validate the response body
    Examples:
      | status |
      | 200    |