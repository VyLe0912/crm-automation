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
    By nameInputToSearchSelector = By.xpath("//th/span[text()='Name']/following-sibling::input");
    By customerNameButtonSelector;
    By customerQuantityDropdownListSelector;
    By firstPageButtonSelector;
    By previousPageButtonSelector;
    By nextPageButtonSelector;
    By lastPageButtonSelector;
    By openCreateCustomerPageSelector = By.xpath("//h5[text()='Add Customer']");

    By messageWhenSearchNameDoesNotExistSelector = By.xpath("//td[text()='No records found.']");

    By nameOfCustomerAtTheTopOfTheListSelector = By.xpath("//tr/td/a[1]");
    By emailOfCustomerAtTheTopOfTheListSelector = By.xpath("//tr/td[2]");
    By addressOfCustomerAtTheTopOfTheListSelector = By.xpath("//tr/td[3]");
    By phoneOfCustomerAtTheTopOfTheListSelector = By.xpath("//tr/td[4]");

    public ShowAllCustomersPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isShowAllCustomerPageDisplayed() {
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(d->d.findElement(newCustomerButtonSelector).isDisplayed());
    }

    public void waitForShowAllCustomersPageIsDisplayed() {
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

    public String nameOfCustomerAtTheTopOfTheList() {
        return driver.findElement(nameOfCustomerAtTheTopOfTheListSelector).getText();
    }

    public String emailOfCustomerAtTheTopOfTheList() {
        return driver.findElement(emailOfCustomerAtTheTopOfTheListSelector).getText();
    }

    public String phoneOfCustomerAtTheTopOfTheList() {
        return driver.findElement(phoneOfCustomerAtTheTopOfTheListSelector).getText();
    }

    public String addressOfCustomerAtTheTopOfTheList() {
        return driver.findElement(addressOfCustomerAtTheTopOfTheListSelector).getText();
    }

    public void inputCustomerNameToSearch(String name) {
        driver.findElement(nameInputToSearchSelector).sendKeys(name);
    }

    public String messageWhenSearchNameDoesNotExist() {
        return driver.findElement(messageWhenSearchNameDoesNotExistSelector).getText();
    }
}
