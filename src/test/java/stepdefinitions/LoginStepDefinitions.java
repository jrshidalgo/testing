package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.loginPage; // Assuming you have a loginPage class that handles login operations

public class LoginStepDefinitions {
    private static boolean isLoggedIn = false; // Static variable to track login state
    private WebDriver driver;
    private loginPage loginPage;

    public LoginStepDefinitions(WebDriver driver) {
        this.driver = driver;
        this.loginPage = new loginPage(driver);
    }

    @Given("I am on the login page")
    public void i_am_on_the_login_page() {
        if (!isLoggedIn) { // Check if not logged in before navigating
            driver.get("https://payroll-staging.sprout.ph/Login.aspx");
        }
    }

    @When("I enter valid credentials")
    public void i_enter_valid_credentials() {
        if (!isLoggedIn) { // Check if not logged in before entering credentials
            loginPage.login("JRCom\\admin", "Napoli07!");
        }
    }

    @Then("I am logged in successfully")
    public void i_am_logged_in_successfully() {
        if (!isLoggedIn) { // Check if not logged in before verifying login success
            boolean isLoginSuccessful = loginPage.isLoginSuccessful(); 
            Assert.assertTrue("Login was not successful", isLoginSuccessful);
            isLoggedIn = true; // Set the flag to true after successful login
        }else{
            driver.get("https://payroll-staging.sprout.ph/Client/Payrolls.aspx");
        }
    }

    // Additional step definitions and methods as needed
}