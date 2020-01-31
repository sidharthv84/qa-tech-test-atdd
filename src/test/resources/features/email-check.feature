Feature: Check email endpoint

  @integrationTest
  Scenario Outline: Get operation for check email end point returns
    Given User registration Api is up and running
    When I perform the get operation for check email end point
    Then I validate "<status>" for the request
    Then Api returns success response
    Examples:
    |status|
    |200   |

  @componentTest
  Scenario Outline:Bad Request - Get operation returns error for incorrect end point
    Given User registration Api is up and running
    When I call the get operation for ID for incorrect end point
    Then I validate "<status>" for the request
    Then Api returns success response
    Examples:
      |status|
      |404   |

  @todo
  Scenario Outline:Bad Request - Get operation returns error for incorrect headers
    Given User registration Api is up and running
    When I perform the get operation for ID using incorrect "<headers>"
    Then I validate "<status>" for the request
    Then Api returns success response
    Examples:
      |status|headers       |
      |401   | wrong headers|

  @todo
  Scenario Outline:Bad Request - Get operation returns error when API is down
    Given User registration Api is up and running
    When I perform the get operation for ID
    Then I validate "<status>" for the request
    Then Api returns success response
    Examples:
      |status|
      |500   |

  @todo
  Scenario Outline:Bad Request - Not allowed methods
    Given User registration Api is up and running
    When I perform the get operation for ID using incorrect "<methods>"
    Then I validate "<status>" for the request
    Then Api returns success response
    Examples:
      |status  |methods   |
      | 404    | get      |
      | 404    | patch    |
      | 404    | delete   |
      | 404    | put      |


