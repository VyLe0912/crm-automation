package page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CreateCustomerPage {
    WebDriver driver;

    By nameInputSelector = By.id("j_idt70:name");
    By emailInputSelector = By.id("j_idt70:email");
    By phoneInputSelector = By.id("j_idt70:phone");
    By addressInputSelector = By.id("j_idt70:address");
    By createACustomerButtonSelector = By.name("j_idt70:j_idt80");
    By cancelButtonSelector = By.xpath("//a[@href='/CRMweb/faces/listCustomer.xhtml']");

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
}
