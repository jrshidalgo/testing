package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class PayrollPage {
    private static WebDriver driver;
    private static WebDriverWait wait;

    @FindBy(id = "mnuPayrolls")
    private WebElement payrollsMenu;

    @FindBy(id = "ph1_btnCreate")
    private WebElement createButton;

    @FindBy(id = "ctl00_ph1_cmbPayrollType_Input") 
    private WebElement payrollTypeDropdown;

    @FindBy(id = "ctl00_ph1_cmbMonth_Input")
    private WebElement monthDropdown;

    @FindBy(id = "ctl00_ph1_rdpFrom_dateInput")
    private WebElement fromDateInput;

    @FindBy(id = "ctl00_ph1_rdpTo_dateInput")
    private WebElement toDateInput;

    @FindBy(id = "ctl00_ph1_cmbEmployeeTypeDynamic_Input")
    private WebElement payGroup;

    @FindBy(how = How.XPATH, using = "//input[contains(@class,'rcbCheckAllItemsCheckBox')]") 
	WebElement selectAllCheckbox;

    @FindBy(how = How.XPATH, using = "//div[@class='raDiv']") 
	WebElement LOADINGSPLASH;

    @FindBy(how = How.XPATH, using = "//*[@id=\"ph1_btnNext\"]")
    private WebElement nextButton;

    @FindBy(id = "ph1_SaveDiv")
    private WebElement saveHover;

    @FindBy(id = "btnNext")
    private WebElement saveAndProcessButton;

    @FindBy(id = "ctl00_ph1_grdPayroll")
    private WebElement payrollSummaryTable;

    @FindBy(how = How.XPATH, using = "//*[@id=\"mnuReports\"]")
    private WebElement REPORTSMENU;

    @FindBy(how = How.XPATH, using = "//*[@id=\"smoothmenu1\"]/ul/li[2]/ul/li[1]")
    private WebElement payrollReports;

    @FindBy(how = How.XPATH, using = "//*[@id=\"mnuPayrollSummary\"]")
    private WebElement payrollSummaryOption;

    @FindBy(how = How.XPATH, using = "//*[@id=\"ph1_btnToExcel\"]")
    private WebElement downloadButton;
    

    

    public PayrollPage(WebDriver driver) {
        PayrollPage.driver = driver;
        PageFactory.initElements(driver, this);
        PayrollPage.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }



    public void createNewPayrollRun() {
        payrollsMenu.click();
        createButton.click();
    }

    public void setupPayrollRun(String fromDate, String toDate, String payrollType, String payGroup, String month){
        selectPayGroup(payGroup);
        selectPayrollType(payrollType);
        setFromDate(fromDate);
        setToDate(toDate);
        selectMonth(month);
    }

    public void validatePayrollSummaryTableIsVisible() {
        // Step 2: Check if the element is displayed
        boolean isVisible = payrollSummaryTable.isDisplayed();

        // Step 3: Assert that the element is displayed
        Assert.assertTrue("Payroll Summary table is not visible.", isVisible);
    }

    public void savePayrollRun(){
        waitElementToBeInvisible(LOADINGSPLASH);
        WebElement saveButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"ph1_btnSave\"]")));
        saveButton.click();

    }

    public void nextStep(){
        waitElementToBeInvisible(LOADINGSPLASH);
        WebElement nextButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"ph1_btnNext\"]")));
        nextButton.click();
    }

    

    public void saveAndProcessPayrollRun(){
        waitElementToBeInvisible(LOADINGSPLASH);
        hoverOverElement(saveHover);
        saveAndProcessButton.click();
    }

    public void hoverOverElement(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).build().perform();
    }

    public void selectPayrollType(String payrollType) {
        payrollTypeDropdown.click();
        
        // Use the specific payrollType in the XPath expression to find the WebElement
        WebElement specificPayrollTypeOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='ctl00_ph1_cmbPayrollType_DropDown']/div/ul/li[contains(text(),'" + payrollType + "')]")));
        specificPayrollTypeOption.click();
    }

    public void selectMonth(String month) {
        monthDropdown.click();

        waitElementToBeInvisible(LOADINGSPLASH);

        

        @SuppressWarnings("unused")
        WebElement dropdownOptionsContainer = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"ctl00_ph1_cmbMonth_DropDown\"]/div/ul")));

        // Now, find and click the specific month option within the dropdown
        WebElement specificMonthOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='ctl00_ph1_cmbMonth_DropDown']/div/ul/li[contains(text(),'" + month + "')]")));
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
        
        // Use the specific payGroup in the XPath expression to find the WebElement by its text 
        WebElement specificPayGroupOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='ctl00_ph1_cmbEmployeeTypeDynamic_DropDown']/div/ul/li//descendant::label[contains(text(),'" + paygroup + "')]")));
        specificPayGroupOption.click();
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

    public void navigateToReportsMenu() {
        
        hoverOverElement(REPORTSMENU);
    }

    public void selectPayrollFromDropdown() {
        hoverOverElement(payrollReports);
    }

    public void choosePayrollSummaryFromOptions() {
        wait.until(ExpectedConditions.visibilityOf(payrollSummaryOption));
        payrollSummaryOption.click();
    }

    public void filterReportForNormalPayrollInJanuary(String payrollRunType, String month) {
        // Assuming the table rows are within a tbody tag under a table with a specific ID or class
        List<WebElement> rows = driver.findElements(By.xpath("//table[@id='ctl00_ph1_grdPayrolls_ctl00']/tbody/tr"));
        
        for (WebElement row : rows) {
            // Assuming the first column contains the month and the second column contains the Payroll Run Type
            String selectedMonth = row.findElement(By.xpath(".//td[3]")).getText();
            String selectedPayrollRunType = row.findElement(By.xpath(".//td[5]")).getText();
            System.out.println("Month: " + selectedMonth + ", Payroll Run Type: " + selectedPayrollRunType);
            System.out.println(month + " " + payrollRunType);
            if (selectedMonth.equals(month) && selectedPayrollRunType.equals(payrollRunType)) {
                // Assuming there's an action to be performed on the row, like clicking a checkbox or a button
                WebElement firstElement = row.findElement(By.xpath(".//td[1]")); // Using XPath to get the first <td> element of the row
                firstElement.click();
                break; // Exit the loop once the correct row is found and action is performed
            }
        }
        waitElementToBeInvisible(LOADINGSPLASH);
    }

    public void exportPayrollSummaryExcelFile() {
        downloadButton.click();
    }

    public void gotoReportsLogPage() {
       
        WebElement reportsLog = wait.until(ExpectedConditions.visibilityOfElementLocated(By .xpath("//*[@id='ph1_lblReportStatusReady']/span/a")));
        reportsLog.click();
    }



    public void clickDownloadButton() {
        WebElement row = driver.findElement(By.xpath("//table[@id='ctl00_ph1_grdQueue_ctl00']/tbody/tr[1]"));
        WebElement downloadButton = row.findElement(By.xpath(".//td[4]"));
        downloadButton.click();
        
        
    }







}