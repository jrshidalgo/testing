Feature: Login functionality

  Scenario: Successful login
    Given I am on the login page
    When I enter valid credentials
    Then I am logged in successfully