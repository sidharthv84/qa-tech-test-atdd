Feature: Check email endpoint

  @integrationTest
  Scenario: Get operation for check email end point returns
    Given User registration Api is up and running
    When I perform the get operation for check email end point
    Then Api returns success response