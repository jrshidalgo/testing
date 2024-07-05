Feature: Payroll Run Creation

  Scenario: Successfully create and process a payroll run
    Given I am logged in to the payroll system
    When I create a new payroll run
    And I set up the payroll run
    And I save the payroll run
    And I proceed to the next step
    And I save and process the payroll run
    Then the Payroll Summary table is visible