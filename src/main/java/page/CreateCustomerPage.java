package page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CreateCustomerPage {
    WebDriver driver;

    By nameInputSelector = By.id("j_idt70:name");
    By emailInputSelector = By.id("j_idt70:email");
    By phoneInputSelector = By.id("j_idt70:phone");
    By addressInputSelector = By.id("j_idt70:address");
    By createACustomerButtonSelector = By.name("j_idt70:j_idt80");
    By cancelButtonSelector = By.xpath("//a[@href='/CRMweb/faces/listCustomer.xhtml']");
    By newCustomerButtonSelector = By.xpath("//a[text()='New Customer']");
    By openCreateCustomerPage = By.xpath("//h5[text()='Add Customer']");
    By nameFieldTextMessageSelector = By.xpath("(//span[@style='color: red'])[1]");
    By emailFieldTextMessageSelector = By.xpath("(//span[@style='color: red'])[2]");
    By phoneFieldTextMessageSelector = By.xpath("(//span[@style='color: red'])[3]");
    By addressFieldTextMessageSelector = By.xpath("(//span[@style='color: red'])[4]");

    public CreateCustomerPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step ("Input Name")
    public void nameInput(String name) {
        driver.findElement(nameInputSelector).sendKeys(name);
    }

    @Step ("Input Email")
    public void emailInput(String email) {
        driver.findElement(emailInputSelector).sendKeys(email);
    }

    @Step ("Input Phone")
    public void phoneInput(String phone) {
        driver.findElement(phoneInputSelector).sendKeys(phone);
    }

    @Step ("Input Address")
    public void addressInput(String address) {
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

    public boolean isCreateCustomerPageDisplayed() {
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(d->d.findElement(openCreateCustomerPage).isDisplayed());
    }

    @Step ("Create a customer")
    public void createCustomer(String name, String email, String phone, String address) {
        nameInput(name);
        emailInput(email);
        phoneInput(phone);
        addressInput(address);
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
