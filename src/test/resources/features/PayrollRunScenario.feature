# In src/test/resources/features/PayrollRun.feature
Feature: Payroll Run Creation with Parameters
    Scenario: Successfully create and process a Normal Payroll run for Rank and File in January
        Given I am logged in to the payroll system
        When I create a new payroll run
        And I set up the payroll run for Normal Payroll from 1/1/2024 to 1/31/2024 with Rank and File pay group and January month
        And I complete the payroll run process
        Then the Payroll Summary table is visible

    Scenario: Successfully create and process a Special Run for Rank and File in January
        Given I am logged in to the payroll system
        When I create a new payroll run
        And I set up the payroll run for Special Run from 1/1/2024 to 1/31/2024 with Rank and File pay group and January month
        And I complete the payroll run process
        Then the Payroll Summary table is visible

    Scenario: Successfully create and process a Normal Payroll run for Officer in February
        Given I am logged in to the payroll system
        When I create a new payroll run
        And I set up the payroll run for Normal Payroll from 2/1/2024 to 2/29/2024 with Officer pay group and February month
        And I complete the payroll run process
        Then the Payroll Summary table is visible

    @downloadPayrollSummary
    Scenario: Download Payroll Summary Excel file for January, Normal Payroll Run Type
        Given I am logged in to the payroll system
        When I navigate to the REPORTS menu
        And I select PAYROLL from the dropdown
        And I choose PAYROLL SUMMARY from the options
        And I select the report for Normal run type in January
        And I download the Payroll Summary Excel file
        And I go to Reports Log Page
        Then I click Download