package page.CustomerInformation;

import io.qameta.allure.Step;
import models.CustomerInFormationForm;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import page.SideBar.SideBar;

import java.time.Duration;

public class CustomerInformationPage extends SideBar {
    WebDriver driver;

    By editButtonSelector = By.xpath("//a[text()='Edit']");
    By customerNameSelector = By.xpath("//div[preceding-sibling::label[text()='Name:']]/span");
    By customerEmailSelector = By.xpath("//div[preceding-sibling::label[text()='Email:']]/span");
    By customerPhoneSelector = By.xpath("//div[preceding-sibling::label[text()='Phone:']]/span");
    By customerAddressSelector = By.xpath("//div[preceding-sibling::label[text()='Address:']]/span");
    By informationBreadCrumbSelector = By.xpath("//strong[text()='Information']");

    By addOrderButtonSelector = By.xpath("//a[text()='Add order']");

    public CustomerInformationPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    @Step("Get customer name in [Customer Information] page")
    public String getCustomerName() {
        return driver.findElement(customerNameSelector).getText();
    }

    @Step("Get customer email in [Customer Information] page")
    public String getCustomerEmail() {
        return driver.findElement(customerEmailSelector).getText();
    }

    @Step("Get customer phone in [Customer Information] page")
    public String getCustomerPhone() {
        return driver.findElement(customerPhoneSelector).getText();
    }

    @Step("Get customer address in [Customer Information] page")
    public String getCustomerAddress() {
        return driver.findElement(customerAddressSelector).getText();
    }

    @Step("Open [Edit Customer Information]")
    public void clickEditButton() {
        driver.findElement(editButtonSelector).click();
    }

    public boolean isCustomerInformationPageDisplayed() {
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(d->d.findElement(informationBreadCrumbSelector).isDisplayed());
    }

    @Step("Click [Add order] button")
    public void clickAddOrderButton() {
        driver.findElement(addOrderButtonSelector).click();
    }

    public void waitForCustomerNameIsDisplayed() {
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(d->d.findElement(customerNameSelector).isDisplayed());
    }

    @Step ("Get customer information in [Customer Information] page")
    public CustomerInFormationForm getCustomer() {
        return new CustomerInFormationForm(getCustomerName(), getCustomerEmail(), getCustomerPhone(), getCustomerAddress());
    }

}
