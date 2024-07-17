package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
// Import the SelfHealingDriver class
import com.epam.healenium.SelfHealingDriver; // Replace 'com.yourpackage' with the actual package name

public class WebDriverManager {
    private static SelfHealingDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {
            System.setProperty("webdriver.chrome.driver", "C:\\temp\\testing\\src\\test\\java\\drivers\\chromedriver.exe");
            // Declare delegate WebDriver
            WebDriver delegate = new ChromeDriver();
            // Create Self-healing driver
            driver = SelfHealingDriver.create(delegate);
        }
        return driver;
    }

    public static void closeDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}