Feature: Get endpoint - Show ID

  @integrationTest
  Scenario: Get operation returns user search by ID
    Given User registration Api is up and running
    When I perform the get operation for ID
    Then Api returns success response
