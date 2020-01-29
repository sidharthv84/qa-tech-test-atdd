Feature: Get endpoint - Show Me

  @integrationTest
  Scenario: Get operation - Show me returns my user info
    Given User registration Api is up and running
    When I perform the get operation for "me"
    Then Api returns success response

