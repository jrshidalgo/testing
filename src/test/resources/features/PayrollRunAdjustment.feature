Feature: Payroll Run with Adjustments
    Scenario: Successfully create and process a Normal Payroll run for Rank and File in January with a One Time Adjustment to Jan Ryan Hidalgo
        Given I am logged in to the payroll system
        When I create a new payroll run
        And I set up the payroll run for Normal Payroll from 1/1/2024 to 1/31/2024 with Rank and File pay group and January month
        And I save the payroll run
        And I click the adjustments link for JRC001
        And I click add new record button
        And I enter the adjustment details with Basic Adjustment type, named as Basic Adj1, Code BA1, Amount of 133.29 and a Remarks of test remark
        And I validate if adjustment record is created successfully with Basic Adjustment type, named as Basic Adj1, Code BA1, Amount of 133.29 and a Remarks of test remark
        And I close the modal
        And I proceed to the next step
        And I save and process the payroll run
        Then the Payroll Summary table is visible
    
    Scenario: Successfully create and process a Normal Payroll run for Rank and File in January with Recurring Adjustment to Jan Ryan Hidalgo
        Given I am logged in to the payroll system
        And I click the Employees tab
        And I click the adjustments link for JRC001
        And I click add new record button
        And I enter the recurring adjustment details with Basic Adjustment type, named as Basic adj2, Code BA2, Amount of 133.29, Adjustment date of 01/01/2024 and a Remarks of test remark
        And I validate if recurring adjustment record is created successfully with Basic Adjustment type, named as Basic adj2, Code BA1, Amount of 133.29 and a Remarks of test remark
        And I create a new payroll run
        And I set up the payroll run for Normal Payroll from 6/1/2024 to 6/30/2024 with Rank and File pay group and June month
        And I save the payroll run
        And I click the adjustments link for JRC001
        And I validate if recurring adjustment record in the modal is created successfully with Basic Adjustment type, named as Basic adj2, Code BA2, Amount of 133.29 and a Remarks of test remark
        And I close the modal
        And I proceed to the next step
        And I save and process the payroll run
        Then the Payroll Summary table is visible
