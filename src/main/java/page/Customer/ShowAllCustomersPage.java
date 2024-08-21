package page.Customer;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import page.SideBar.SideBar;

import java.time.Duration;
import java.util.List;

public class ShowAllCustomersPage extends SideBar {

    WebDriver driver;

    public ShowAllCustomersPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    By newCustomerButtonSelector = By.xpath("//a[text()='New Customer']");
    By nameInputToSearchSelector = By.xpath("//th/span[text()='Name']/following-sibling::input");
    By firstPageButtonInTopSelector = By.xpath("//div[@class='ui-paginator ui-paginator-top ui-widget-header ui-corner-top']/span/span[@class='ui-icon ui-icon-seek-first']");
    By previousPageButtonInTopSelector = By.xpath("//div[@class='ui-paginator ui-paginator-top ui-widget-header ui-corner-top']/span/span[@class='ui-icon ui-icon-seek-prev']");
    By nextPageButtonInTopSelector = By.xpath("//div[@class='ui-paginator ui-paginator-top ui-widget-header ui-corner-top']/span/span[@class='ui-icon ui-icon-seek-next']");
    protected By lastPageButtonInTopSelector = By.xpath("//div[@class='ui-paginator ui-paginator-top ui-widget-header ui-corner-top']/span/span[@class='ui-icon ui-icon-seek-end']");
    By firstPageButtonInBottomSelector = By.xpath("//div[@class='ui-paginator ui-paginator-bottom ui-widget-header ui-corner-bottom']/span/span[@class='ui-icon ui-icon-seek-first']");
    By previousPageButtonInBottomSelector = By.xpath("//div[@class='ui-paginator ui-paginator-bottom ui-widget-header ui-corner-bottom']/span/span[@class='ui-icon ui-icon-seek-prev']");
    By nextPageButtonInBottomSelector = By.xpath("//div[@class='ui-paginator ui-paginator-bottom ui-widget-header ui-corner-bottom']/span/span[@class='ui-icon ui-icon-seek-next']");
    By lastPageButtonInBottomSelector = By.xpath("//div[@class='ui-paginator ui-paginator-bottom ui-widget-header ui-corner-bottom']/span/span[@class='ui-icon ui-icon-seek-end']");
    By openCreateCustomerPageSelector = By.xpath("//h2[text()='Create Customer']");

    By noRecordFoundLabelSelector = By.xpath("//td[text()='No records found.']");
    By tableRowSelector = By.xpath("//tbody/tr");

    By customerNameLabelSelector = By.xpath("//tbody/tr/td/a");
    By customerEmailLabelSelector = By.xpath("//tr/td[2]");
    By customerAddressLabelSelector = By.xpath("//tr/td[3]");
    By customerPhoneLabelSelector = By.xpath("//tr/td[4]");

    By showAllCustomersBreadCrumbSelector = By.xpath("//strong[text()='Show All Customers']");
    By pageCurrentLabelSelector = By.xpath("//div[@class='ui-paginator ui-paginator-top ui-widget-header ui-corner-top']/span[@class='ui-paginator-current']");

    public boolean isNewCustomerButtonDisplayed() {
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(d->d.findElement(newCustomerButtonSelector).isDisplayed());
    }

    public boolean isShowAllCustomersPageDisplayed() {
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(d->d.findElement(showAllCustomersBreadCrumbSelector).isDisplayed());
    }

    public void waitForNewCustomerButtonIsDisplayed() {
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(d->d.findElement(newCustomerButtonSelector).isDisplayed());
    }

    public boolean isCreateCustomerPageDisplayed() {
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(d->d.findElement(openCreateCustomerPageSelector).isDisplayed());
    }

    public void waitForCreateCustomerPageIsDisplayed() {
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(d->d.findElement(openCreateCustomerPageSelector).isDisplayed());
    }

    @Step ("Click [New Customer] button")
    public void clickNewCustomerButton() {
        driver.findElement(newCustomerButtonSelector).click();
    }

    @Step("Open [Create Customer] page")
    public void openCreateNewCustomerPage() {
        clickNewCustomerButton();
        waitForCreateCustomerPageIsDisplayed();
    }

    @Step("Open [Customer Information] page")
    public void openCustomerInformationPage(int i) {
        driver.findElements(customerNameLabelSelector).get(i-1).click();
    }

    @Step("Get customer name by index")
    public String getCustomerNameByIndex(int i) {
        return driver.findElements(customerNameLabelSelector).get(i-1).getText();
    }

    @Step("Get customer email by index")
    public String getCustomerEmailByIndex(int i) {
        return driver.findElements(customerEmailLabelSelector).get(i-1).getText();
    }

    @Step("Get customer phone by index")
    public String getCustomerPhoneByIndex(int i) {
        return driver.findElements(customerPhoneLabelSelector).get(i-1).getText();
    }

    @Step("Get customer address by index")
    public String getCustomerAddressByIndex(int i) {
        return driver.findElements(customerAddressLabelSelector).get(i-1).getText();
    }

    @Step("Input customer name to search")
    public void searchCustomer(String name) {
        driver.findElement(nameInputToSearchSelector).clear();
        driver.findElement(nameInputToSearchSelector).sendKeys(name);
        try{
            Thread.sleep(2000);
        } catch (Exception e) {
        }
    }

    public boolean isNoRecordFoundIsDisplayed() {
        return driver.findElement(noRecordFoundLabelSelector).isDisplayed();
    }

    @Step("Get number of row customer in first page")
    public int countRowInCurrentPage() {
        return driver.findElements(tableRowSelector).size();
    }

    @Step("Click first customer page button in top")
    public void openFirstCustomerPageTop() {
        driver.findElement(firstPageButtonInTopSelector).click();
    }

    @Step("Click previous customer page button in top")
    public void openPreviousCustomerPageTop() {
        driver.findElement(previousPageButtonInTopSelector).click();
    }

    @Step("Click next customer page button in top")
    public void openNextCustomerPageTop() {
        driver.findElement(nextPageButtonInTopSelector).click();
    }

    @Step("Click last customer page button in top")
    public void openLastCustomerPageTop() {
        driver.findElement(lastPageButtonInTopSelector).click();
    }

    @Step("Click first customer page button in bottom")
    public void openFirstCustomerPageBottom() {
        driver.findElement(firstPageButtonInBottomSelector).click();
    }

    @Step("Click previous customer page button in bottom")
    public void openPreviousCustomerPageBottom() {
        driver.findElement(previousPageButtonInBottomSelector).click();
    }

    @Step("Click next customer page button in bottom")
    public void openNextCustomerPageBottom() {
        driver.findElement(nextPageButtonInBottomSelector).click();
    }

    @Step("Click last customer page button in bottom")
    public void openLastCustomerPageBottom() {
        driver.findElement(lastPageButtonInBottomSelector).click();
    }

    public String getPageCurrentLabel() {
        return driver.findElement(pageCurrentLabelSelector).getText();
    }

    public int getCurrentPage() {
        int start1 = getPageCurrentLabel().indexOf('(');
        int end1 = getPageCurrentLabel().indexOf(' ');
        String currentPage = getPageCurrentLabel().substring(start1 + 1, end1);
        return Integer.parseInt(currentPage);
    }

    public int getTotalPage() {
        int start2 = getPageCurrentLabel().lastIndexOf(' ');
        int end2 = getPageCurrentLabel().indexOf(')');
        String totalPage = getPageCurrentLabel().substring(start2 + 1, end2);
        return Integer.parseInt(totalPage);
    }

    public boolean allNamesAre(String name) {
        List<WebElement> elements = driver.findElements(customerNameLabelSelector);
        for (WebElement element : elements) {
            String allName = element.getText();
            if (!allName.startsWith(name)) {
                return false;
            }
        }
        return true;
    }
}
