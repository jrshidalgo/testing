package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.WebDriverManager;
import pages.loginPage;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginStepDefinitions {
    private WebDriver driver;
    private loginPage loginPage;

    public LoginStepDefinitions(WebDriver driver) {
        this.driver = driver;
        this.loginPage = new loginPage(driver);
    }

    @Given("I am on the login page")
    public void i_am_on_the_login_page() {
        driver.get("https://payroll-staging.sprout.ph/Login.aspx");
    }

    @When("I enter valid credentials")
    public void i_enter_valid_credentials() {
        loginPage.login("JRCom\\admin", "Napoli07!");
    }

    @Then("I am logged in successfully")
    public void i_am_logged_in_successfully() {
        // Use JUnit Assert for better error handling and reporting
        boolean isLoginSuccessful = loginPage.isLoginSuccessful(); 
        Assert.assertTrue("Login was not successful", isLoginSuccessful);
    }
}