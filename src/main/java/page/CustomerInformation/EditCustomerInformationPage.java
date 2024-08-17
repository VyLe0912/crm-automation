package page.CustomerInformation;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import page.Customer.CreateCustomerPage;

import java.time.Duration;

public class EditCustomerInformationPage extends CreateCustomerPage {
    WebDriver driver;

//    By nameEditInputSelector = By.id("j_idt74:name");
//    By emailEditInputSelector = By.id("j_idt74:email");
//    By phoneEditInputSelector = By.id("j_idt74:phone");
//    By addressEditInputSelector = By.id("j_idt74:address");
//    By cancelButtonSelector = By.xpath("//a[text()='Cancel']");
    By saveButtonSelector = By.name("j_idt74:j_idt85");

    By informationBreadcrumbButtonSelector = By.xpath("//a[text()='Information']");
    By showAllCustomersBreadcrumbButtonSelector = By.xpath("//ol/li/a[@href='listCustomer.xhtml']");
    By homeBreadcrumbButtonSelector = By.xpath("//i[@class='fa fa-home']");
    By editCustomerInformationBreadCrumbSelector = By.xpath("//strong[text()='Edit Customer Information']");

    public EditCustomerInformationPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }


//    @Step ("Edit name")
//    public void nameEditInput(String name) {
//        driver.findElement(nameEditInputSelector).clear();
//        driver.findElement(nameEditInputSelector).sendKeys(name);
//    }

//    @Step ("Edit email")
//    public void emailEditInput(String email) {
//        driver.findElement(emailEditInputSelector).clear();
//        driver.findElement(emailEditInputSelector).sendKeys(email);
//    }
//    @Step ("Edit phone")
//    public void phoneEditInput(String phone) {
//        driver.findElement(phoneEditInputSelector).clear();
//        driver.findElement(phoneEditInputSelector).sendKeys(phone);
//    }

//    @Step ("Edit address")
//    public void addressEditInput(String address) {
//        driver.findElement(addressEditInputSelector).clear();
//        driver.findElement(addressEditInputSelector).sendKeys(address);
//    }

    @Step("Click [Save] button")
    public void clickSaveButton() {
        driver.findElement(saveButtonSelector).click();
    }

//    @Step("Click [Cancel] button")
//    public void clickCancelButton() {
//        driver.findElement(cancelButtonSelector).click();
//    }

    @Step("Click [Information] button on breadcrumb")
    public void clickInformationBreadcrumbButton() {
        driver.findElement(informationBreadcrumbButtonSelector).click();
    }

    @Step("Click [Show All Customers] button on breadcrumb")
    public void clickShowAllCustomersBreadcrumbButton() {
        driver.findElement(showAllCustomersBreadcrumbButtonSelector).click();
    }

    @Step("Click [Home] button on breadcrumb")
    public void clickHomeBreadcrumbButton() {
        driver.findElement(homeBreadcrumbButtonSelector).click();
    }

    @Step("Get customer name in [Edit Customer Information] page")
    public String getCustomerName() {
        return driver.findElement(nameInputSelector).getAttribute("value");
    }

    @Step("Get customer email in [Edit Customer Information] page")
    public String getCustomerEmail() {
        return driver.findElement(emailInputSelector).getAttribute("value");
    }

    @Step("Get customer phone in [Edit Customer Information] page")
    public String getCustomerPhone() {
        return driver.findElement(phoneInputSelector).getAttribute("value");
    }

    @Step("Get customer address in [Edit Customer Information] page")
    public String getCustomerAddress() {
        return driver.findElement(addressInputSelector).getAttribute("value");
    }

    public boolean isEditCustomerInformationDisplayed() {
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(d->d.findElement(editCustomerInformationBreadCrumbSelector).isDisplayed());
    }
}
