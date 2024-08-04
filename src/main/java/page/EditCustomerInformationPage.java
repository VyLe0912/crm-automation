package page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EditCustomerInformationPage {
    WebDriver driver;

    By nameEditInputSelector = By.id("j_idt74:name");
    By emailEditInputSelector = By.id("j_idt74:email");
    By phoneEditInputSelector = By.id("j_idt74:phone");
    By addressEditInputSelector = By.id("j_idt74:address");
    By saveButtonSelector = By.name("j_idt74:j_idt85");
    By cancelButtonSelector = By.xpath("//a[text()='Cancel']");

    By informationBreadcrumbButtonSelector = By.xpath("//a[text()='Information']");
    By showAllCustomersBreadcrumbButtonSelector = By.xpath("//ol/li/a[@href='listCustomer.xhtml']");
    By homeBreadcrumbButtonSelector = By.xpath("//i[@class='fa fa-home']");

    @Step ("Edit name")
    public void nameEditInput(String name) {
        driver.findElement(nameEditInputSelector).clear();
        driver.findElement(nameEditInputSelector).sendKeys(name);
    }

    @Step ("Edit email")
    public void emailEditInput(String email) {
        driver.findElement(emailEditInputSelector).clear();
        driver.findElement(emailEditInputSelector).sendKeys(email);
    }
    @Step ("Edit phone")
    public void phoneEditInput(String phone) {
        driver.findElement(phoneEditInputSelector).clear();
        driver.findElement(phoneEditInputSelector).sendKeys(phone);
    }

    @Step ("Edit address")
    public void addressEditInput(String address) {
        driver.findElement(addressEditInputSelector).clear();
        driver.findElement(addressEditInputSelector).sendKeys(address);
    }

    @Step("Click [Save] button")
    public void clickSaveButton() {
        driver.findElement(saveButtonSelector).click();
    }

    @Step("Click [Cancel] button")
    public void clickCancelButton() {
        driver.findElement(cancelButtonSelector).click();
    }

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
}
