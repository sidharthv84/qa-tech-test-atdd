Feature: Put Operation - Update User

  @integrationTest
  Scenario Outline: Put operation - Updates my user info
    Given User registration Api is up and running
    When I perform the update operation for the user
      | last_name  | Integration            |
      | first_name | Testing12345           |
    Then I validate "<status>" for the request
    Then Api returns success response
    Examples:
    |status|
    |200   |

  @todo
  Scenario Outline: Put operation - Updates my user info
    Given User registration Api is up and running
    When I perform the update operation for the user
      | last_name  |             |
      | first_name | Testing12345           |
    Then I validate "<status>" for the request
    Then Api returns success response
    Examples:
      |status|
      |422   |

  @todo
  Scenario Outline: Put operation - Updates my user info
    Given User registration Api is up and running
    When I perform the update operation for the user
      | last_name  |Testing12345|
      | first_name |            |
    Then I validate "<status>" for the request
    Then Api returns success response
    Examples:
      |status|
      |422   |

  @todo
  Scenario Outline: Put operation - Updates my user info
    Given User registration Api is up and running
    When I perform the update operation for the user
      | last_name  |             |
      | first_name |            |
    Then I validate "<status>" for the request
    Then Api returns success response
    Examples:
      |status|
      |422   |


  @todo
  Scenario Outline:Bad Request - Not allowed methods
    Given User registration Api is up and running
    When I perform the operation for USER using incorrect "<methods>"
      | last_name  | Integration            |
      | first_name | Testing12345           |
    Then I validate "<status>" for the request
    Then Api returns success response
    Examples:
      |status  |methods   |
      | 404    | get      |
      | 404    | post     |
      | 404    | delete   |



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
  Scenario Outline: Put operation - Revoke Access Token and Refresh Access Token
    Given User registration Api is up and running
    When I perform the get operation for check email end point
    Then I validate "<status>" for the request
    Then Api returns success response
    Examples:
      |status|
      |200   |


