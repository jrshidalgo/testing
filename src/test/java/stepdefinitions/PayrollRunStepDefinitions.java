// In PayrollRunStepDefinitions.java
package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.PayrollPage;
import utils.WebDriverManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
// import org.openqa.selenium.interactions.Actions;

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

    @And("I set up the payroll run")
    public void i_set_up_the_payroll_run() {
        payrollPage.setupPayrollRun();
        
        
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
        driver.quit();
    }
}