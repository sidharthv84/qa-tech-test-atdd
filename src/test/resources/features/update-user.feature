Feature: Put Operation - Update User

  @integrationTest
  Scenario: Put operation - Updates my user info
    Given User registration Api is up and running
    When I perform the update operation for the user
      | last_name  | Integration            |
      | first_name | Testing12345           |
   #   | image_url  |https://static.thenounproject.com/png/961-200.png|
    Then Api returns success response