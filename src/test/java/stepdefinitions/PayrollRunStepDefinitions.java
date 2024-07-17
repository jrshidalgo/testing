// In PayrollRunStepDefinitions.java
package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.PayrollPage;
import utils.WebDriverManager;
import org.openqa.selenium.WebDriver;

public class PayrollRunStepDefinitions {
    private WebDriver driver;
    private LoginStepDefinitions loginSteps;
    private PayrollPage payrollPage;


    public PayrollRunStepDefinitions() {
        this.driver = WebDriverManager.getDriver();
        this.loginSteps = new LoginStepDefinitions(driver);
        this.payrollPage = new PayrollPage(driver);
    }

    @Given("I am logged in to the payroll system")
    public void i_am_logged_in_to_the_system() {
        loginSteps.i_am_on_the_login_page();
        loginSteps.i_enter_valid_credentials();
        loginSteps.i_am_logged_in_successfully();
    }

    @When("I create a new payroll run")
    public void i_create_a_new_payroll_run() {
       payrollPage.createNewPayrollRun();
    }

    // In src/test/java/stepdefinitions/PayrollRunStepDefinitions.java
    @And("^I set up the payroll run for (.*) from (.*) to (.*) with (.*) pay group and (.*) month$")
    public void i_set_up_the_payroll_run_with_parameters(String payrollType, String fromDate, String toDate, String payGroup, String month) {
        System.out.println("Payroll Type: " + payrollType + ", From Date: " + fromDate + ", To Date: " + toDate + ", Pay Group: " + payGroup + ", Month: " + month);
        payrollPage.setupPayrollRun(fromDate, toDate, payrollType, payGroup, month);
    }

    @And("^I complete the payroll run process$")
    public void i_complete_the_payroll_run_process() {
        i_save_the_payroll_run();
        i_proceed_to_the_next_step();
        i_save_and_process_the_payroll_run();
    }

    @And("I save the payroll run")
    public void i_save_the_payroll_run() {
       payrollPage.savePayrollRun();
    }

    @And("I proceed to the next step")
    public void i_proceed_to_the_next_step() {
        payrollPage.nextStep();
    }

    @And("I save and process the payroll run")
    public void i_save_and_process_the_payroll_run() {
        payrollPage.saveAndProcessPayrollRun();
    }

    @Then("the Payroll Summary table is visible")
    public void the_payroll_summary_table_is_visible() {
        payrollPage.validatePayrollSummaryTableIsVisible();
        // driver.quit();
    }

    @When("I navigate to the REPORTS menu")
    public void I_navigate_to_the_menu() {
        payrollPage.navigateToReportsMenu();
        // Write code here that turns the phrase above into concrete actions
    }

    @When("I select PAYROLL from the dropdown")
    public void I_select_from_the_dropdown() {
        payrollPage.selectPayrollFromDropdown();
        // Write code here that turns the phrase above into concrete actions
    }

    @When("I choose PAYROLL SUMMARY from the options")
    public void I_choose_from_the_options() {
        payrollPage.choosePayrollSummaryFromOptions();
        // Write code here that turns the phrase above into concrete actions
    }

    @When("^I select the report for (.*) run type in (.*)$")
    public void I_filter_the_report_for_run_type_in(String payrollRunType, String month) {
        payrollPage.filterReportForNormalPayrollInJanuary(payrollRunType, month);
        // Write code here that turns the phrase above into concrete actions
    }

    @And("I download the Payroll Summary Excel file")
    public void I_download_the_Payroll_Summary_Excel_file_for_January_Normal_Payroll_Run_Type() {
        payrollPage.exportPayrollSummaryExcelFile();
        // Write code here that turns the phrase above into concrete actions
    }

    @Then("I click Download")
    public void I_click_Download() {
        payrollPage.clickDownloadButton();
        // Write code here that turns the phrase above into concrete actions
    }

    @When("I go to Reports Log Page")
    public void I_go_to_Reports_Log_Page() {
        payrollPage.gotoReportsLogPage();
        // Write code here that turns the phrase above into concrete actions
    }

    @When("^I click the adjustments link for (.*)$")
    public void I_click_the_adjustments_link_for_(String id) {
        payrollPage.clickAdjustmentsLink(id);

        // Write code here that turns the phrase above into concrete actions
    }

    @When("^I enter the adjustment details with (.*) type, named as (.*), Code (.*), Amount of (.*) and a Remarks of (.*)$")
    public void I_enter_the_adjustment_details_with_named_as_Code_Amount_of_and_a_Remarks_of(String type, String name, String code, String amount, String remarks) {
        payrollPage.enterAdjustmentDetails(type, name, code, amount, remarks);
    }

    @When("^I enter the recurring adjustment details with (.*) type, named as (.*), Code (.*), Amount of (.*), Adjustment date of (.*) and a Remarks of (.*)$")
    public void I_enter_the_adjustment_details_with_named_as_Code_Amount_of_Adjustment_date_of_and_a_Remarks_of(String type, String name, String code, String amount, String date, String remarks) {
        payrollPage.enterRecurringAdjustmentDetails(type, name, code, amount, date, remarks);
    }

    @When("^I validate if adjustment record is created successfully with (.*) type, named as (.*), Code (.*), Amount of (.*) and a Remarks of (.*)$")
    public void I_validate_if_adjustment_is_created(String type, String name, String code, String amount, String remarks) {
        payrollPage.validateAdjustmentRecord(type, name, code, amount, remarks);
        // Write code here that turns the phrase above into concrete actions
    }

    @When("I close the modal")
    public void I_close_the_modal() {
        payrollPage.closeAdjustmentModal();
        // Write code here that turns the phrase above into concrete actions
    }

    @When("I click add new record button")
    public void I_click_add_new_record_button() {
        payrollPage.I_click_add_new_record_button();

    }

    @When("I click the Employees tab")
    public void I_click_the_Employees_tab() {
        payrollPage.clickEmployeesTab();
        // Write code here that turns the phrase above into concrete actions
    }

    @Given("^I validate if recurring adjustment record is created successfully with (.*) type, named as (.*), Code (.*), Amount of (.*) and a Remarks of (.*)$")
    public void I_validate_if_recurring_adjustment_record_is_created_successfully_with_Basic_Adjustment_type_named_as_Code_Amount_of_Adjustment_date_of_and_a_Remarks_of(String type, String name, String code, String amount, String remarks) {
        payrollPage.validateRecurringAdjustmentRecord(type, name, code, amount, remarks);
    }

    @Given("^I validate if recurring adjustment record in the modal is created successfully with (.*) type, named as (.*), Code (.*), Amount of (.*) and a Remarks of (.*)$")
    public void I_validate_if_recurring_adjustment_record_in_the_modal_is_created_successfully_with_type_named_as_Code_Amount_of_and_a_Remarks_of_(String type, String name, String code, double amount, String remarks) {
        payrollPage.validateAdjustmentRecordModal(type, name, code, amount, remarks);
    }
}