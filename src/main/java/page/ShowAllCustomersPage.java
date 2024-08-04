package page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ShowAllCustomersPage {
    WebDriver driver;

    By newCustomerButtonSelector = By.xpath("//a[text()='New Customer']");
    By nameInputToSearchSelector;
    By customerNameButtonSelector;
    By customerQuantityDropdownListSelector;
    By firstPageButtonSelector;
    By previousPageButtonSelector;
    By nextPageButtonSelector;
    By lastPageButtonSelector;
    By openCreateCustomerPage = By.xpath("//h5[text()='Add Customer']");

    public ShowAllCustomersPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean showAllCustomerPageIsDisplayed() {
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(d->d.findElement(newCustomerButtonSelector).isDisplayed());
    }

    public void verifyShowAllCustomersPageIsDisplayed() {
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(d->d.findElement(newCustomerButtonSelector).isDisplayed());
    }

    public boolean createCustomerPageIsDisplayed() {
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(d->d.findElement(openCreateCustomerPage).isDisplayed());
    }

    public void verifyCreateCustomerPageIsDisplayed() {
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(d->d.findElement(openCreateCustomerPage).isDisplayed());
    }

    @Step ("Click [New Customer] button")
    public void clickNewCustomerButton() {
        driver.findElement(newCustomerButtonSelector).click();
    }
}
