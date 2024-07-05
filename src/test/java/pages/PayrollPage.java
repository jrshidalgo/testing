package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select; // Import the Select class
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class PayrollPage {
    private static WebDriver driver;

    @FindBy(id = "mnuPayrolls")
    private WebElement payrollsMenu;

    @FindBy(id = "ph1_btnCreate")
    private WebElement createButton;

    @FindBy(id = "ctl00_ph1_cmbPayrollType_Input") // Locate the dropdown element
    private WebElement payrollTypeDropdown;

    @FindBy(id = "ctl00_ph1_cmbMonth_Input")
    private WebElement monthDropdown;

    @FindBy(id = "ctl00_ph1_rdpFrom_dateInput")
    private WebElement fromDateInput;

    @FindBy(id = "ctl00_ph1_rdpTo_dateInput")
    private WebElement toDateInput;

    @FindBy(id = "ph1_btnSave")
    private WebElement saveButton;

    @FindBy(id = "ctl00_ph1_cmbEmployeeTypeDynamic_Input")
    private WebElement payGroup;

    @FindBy(how = How.XPATH, using = "//input[contains(@class,'rcbCheckAllItemsCheckBox')]") 
	WebElement selectAllCheckbox;

    @FindBy(how = How.XPATH, using = "//div[@class='raDiv']") 
	WebElement LOADINGSPLASH;

    @FindBy(id = "ph1_btnNext")
    private WebElement nextButton;

    @FindBy(id = "ph1_SaveDiv")
    private WebElement saveHover;

    @FindBy(id = "btnNext")
    private WebElement saveAndProcessButton;

    @FindBy(id = "ctl00_ph1_grdPayroll")
    private WebElement payrollSummaryTable;

    public PayrollPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }



    public void createNewPayrollRun() {
        payrollsMenu.click();
        createButton.click();
    }

    public void setupPayrollRun(){
        selectPayGroup("Check All");
        selectPayrollType("Normal Payroll");
        setFromDate("9/1/2024");
        setToDate("9/15/2024");
        selectMonth("October");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }

    public void validatePayrollSummaryTableIsVisible() {
        // Step 2: Check if the element is displayed
        boolean isVisible = payrollSummaryTable.isDisplayed();

        // Step 3: Assert that the element is displayed
        Assert.assertTrue("Payroll Summary table is not visible.", isVisible);
    }

    public void savePayrollRun(){
        saveButton.click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void nextStep(){
        waitElementToBeInvisible(LOADINGSPLASH);
        nextButton.click();
    }

    

    public void saveAndProcessPayrollRun(){
        waitElementToBeInvisible(LOADINGSPLASH);
        hoverOverElement(saveHover);
        // WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // wait.until(ExpectedConditions.elementToBeClickable(saveAndProcessButton));
        saveAndProcessButton.click();
    }

    public void hoverOverElement(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).build().perform();
    }

    public void selectPayrollType(String payrollType) {
        payrollTypeDropdown.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Use the specific payrollType in the XPath expression to find the WebElement
        WebElement specificPayrollTypeOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='ctl00_ph1_cmbPayrollType_DropDown']/div/ul/li[contains(text(),'" + payrollType + "')]")));
        specificPayrollTypeOption.click();
    }

    public void selectMonth(String month) {
        monthDropdown.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Use the specific month in the XPath expression to find the WebElement
        WebElement specificMonthOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='ctl00_ph1_cmbMonth_DropDown']/div/ul/li[contains(text(),'" + month + "')]")));
        specificMonthOption.click();
    }

    public void setFromDate(String date) {
        fromDateInput.clear();
        fromDateInput.sendKeys(date);
    }

    public void setToDate(String date) {
        toDateInput.clear();
        toDateInput.sendKeys(date);
    }

    public void selectPayGroup(String paygroup) {
        payGroup.click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        selectAllCheckbox.click();
        // Use the specific payGroup in the XPath expression to find the WebElement
        // WebElement specificPayGroupOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='ctl00_ph1_cmbEmployeeTypeDynamic_DropDown']/div/ul/li//descendant::label[contains(text(),'" + paygroup + "')]")));
        // specificPayGroupOption.click();
    }

    public static void waitElementToBeInvisible(WebElement element) {
		boolean result = false;
		int attempts = 0;
		while (attempts <= 1 && result != true) {
			try {
				new WebDriverWait(driver, Duration.ofSeconds(10)).ignoring(StaleElementReferenceException.class)
						.ignoring(NoSuchElementException.class).until(ExpectedConditions.invisibilityOf(element));
				result = true;
			} catch (Exception e) {
				e.getMessage();
			}
			attempts++;
		}
	}
}