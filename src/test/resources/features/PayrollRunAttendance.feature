Feature: Payroll Run Creation with Attendance Table Adjustments

    Scenario: Successfully create and process a Normal Payroll run for Rank and File in January
        Given I am logged in to the payroll system
        When I create a new payroll run
        And I set up the payroll run for Normal Payroll from 1/1/2024 to 1/31/2024 with Rank and File pay group and January month
        And I save the payroll run
        And I proceed to the next step
        And I add the attendance table adjustments for id JRC001
        And I save and process the payroll run
        Then the Payroll Summary table is visible

