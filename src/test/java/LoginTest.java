import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTest {

    @Test
    public void testSuccessfulLogin() {
        // Set the path to the chromedriver executable
        System.setProperty("webdriver.chrome.driver", "C:\\temp\\testing\\src\\test\\java\\drivers\\chromedriver.exe");

        // Initialize the WebDriver
        WebDriver driver = new ChromeDriver();

        try {
            // Navigate to the login page
            driver.get("https://payroll-test-qa.sprout.ph/Login.aspx");

            // Locate and fill the username and password fields
            driver.findElement(By.id("txtUsername")).sendKeys("Veron\\admin");
            driver.findElement(By.id("txtPassword")).sendKeys("diane");

            // Locate and click the login button
            driver.findElement(By.id("btnLogin")).click();

        } finally {
            // Close the browser
            driver.quit();
        }
    }
}
