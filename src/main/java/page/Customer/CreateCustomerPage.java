package page.Customer;

import io.qameta.allure.Step;
import models.CustomerInFormationForm;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import page.SideBar.SideBar;

import java.time.Duration;

public class CreateCustomerPage extends SideBar {
    WebDriver driver;

    protected By nameInputSelector = By.xpath("(//div[preceding-sibling::label[text()='Name']])/input[@class='form-control m-b']");
    protected By emailInputSelector = By.xpath("(//div[preceding-sibling::label[text()='Email']])/input[@class='form-control m-b']");
    protected By phoneInputSelector = By.xpath("(//div[preceding-sibling::label[text()='Phone']])/input[@class='form-control m-b']");
    protected By addressInputSelector = By.xpath("(//div[preceding-sibling::label[text()='Address']])/input[@class='form-control m-b']");
    By createACustomerButtonSelector = By.name("j_idt70:j_idt80");
    By cancelButtonSelector = By.xpath("//a[text()='Cancel']");
    By newCustomerButtonSelector = By.xpath("//a[text()='New Customer']");
    By openCreateCustomerPage = By.xpath("//h5[text()='Add Customer']");
    By nameFieldTextMessageSelector = By.xpath("(//div[preceding-sibling::label[text()='Name']])/span[@style='color: red']");
    By emailFieldTextMessageSelector = By.xpath("(//div[preceding-sibling::label[text()='Email']])/span[@style='color: red']");
    By phoneFieldTextMessageSelector = By.xpath("(//div[preceding-sibling::label[text()='Phone']])/span[@style='color: red']");
    By addressFieldTextMessageSelector = By.xpath("(//div[preceding-sibling::label[text()='Address']])/span[@style='color: red']");

    public CreateCustomerPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    @Step ("Input Name")
    public void inputName(String name) {
        driver.findElement(nameInputSelector).clear();
        driver.findElement(nameInputSelector).sendKeys(name);
    }

    @Step ("Input Email")
    public void inputEmail(String email) {
        driver.findElement(emailInputSelector).clear();
        driver.findElement(emailInputSelector).sendKeys(email);
    }

    @Step ("Input Phone")
    public void inputPhone(String phone) {
        driver.findElement(phoneInputSelector).clear();
        driver.findElement(phoneInputSelector).sendKeys(phone);
    }

    @Step ("Input Address")
    public void inputAddress(String address) {
        driver.findElement(addressInputSelector).clear();
        driver.findElement(addressInputSelector).sendKeys(address);
    }

    @Step ("Click [Create a customer] button")
    public void clickCreateACustomerButton() {
        driver.findElement(createACustomerButtonSelector).click();
    }

    @Step ("Click [Cancel] button")
    public void clickCancelButton() {
        driver.findElement(cancelButtonSelector).click();
    }

    public void waitForCustomerInformationPageIsDisplayed() {
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(d->d.findElement(newCustomerButtonSelector).isDisplayed());
    }

    public void waitForCreateCustomerPageIsDisappear() {
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(d->!d.findElement(createACustomerButtonSelector).isDisplayed());
    }

    public boolean isCreateCustomerPageDisplayed() {
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(d->d.findElement(openCreateCustomerPage).isDisplayed());
    }

    @Step ("Enter customer information")
    public void enterCustomerInformation( CustomerInFormationForm customerInfo) {
        inputName(customerInfo.getName());
        inputEmail(customerInfo.getEmail());
        inputPhone(customerInfo.getPhone());
        inputAddress(customerInfo.getAddress());
    }

    @Step ("Create a Customer")
    public void createCustomerInformation(CustomerInFormationForm customerInfo) {
        enterCustomerInformation(customerInfo);
        clickCreateACustomerButton();
    }

    @Step ("Clear data for [Name] field")
    public void clearName() {
        driver.findElement(nameInputSelector).clear();
    }

    @Step ("Clear data for [Email] field")
    public void clearEmail() {
        driver.findElement(emailInputSelector).clear();
    }

    @Step ("Clear data for [Phone] field")
    public void clearPhone() {
        driver.findElement(phoneInputSelector).clear();
    }

    @Step ("Clear data for [Address] field")
    public void clearAddress() {
        driver.findElement(addressInputSelector).clear();
    }

    public String getErrorForNameField() {
        return driver.findElement(nameFieldTextMessageSelector).getText();
    }

    public String getErrorForEmailField() {
        return driver.findElement(emailFieldTextMessageSelector).getText();
    }

    public String getErrorForPhoneField() {
        return driver.findElement(phoneFieldTextMessageSelector).getText();
    }

    public String getErrorForAddressField() {
        return driver.findElement(addressFieldTextMessageSelector).getText();
    }

}
