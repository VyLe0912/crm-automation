package page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    WebDriver driver;

    By customerMenuButtonSelector = By.xpath("//span[text() = 'Customer']");
    By openShowAllCustomersPageSelector = By.xpath("//a[text() = 'Show All Customers']");
    By openCreateCustomerPageSelector = By.xpath("//a[text() = 'Create Customer']");

    By reminderMenuButtonSelector;
    By openShowAllRemindersPageSelector;

    By campaignsMenuButtonSelector;
    By openShowAllCampaignsPageSelector;
    By openCreateCampaignPageSelector;
    By openShowAllCampaignTypesPageSelector;
    By openCreateCampaignTypePageSelector;

    By productsMenuButtonSelector;
    By openShowAllProductsPageSelector;
    By openCreateProductPageSelector;

    By opportunitiesMenuButtonSelector = By.xpath("//span[text() = 'Opportunities']");
    By openShowAllOpportunitiesPageSelector = By.xpath("//a[text() = 'Show All Opportunitys']");

    By ordersMenuButtonSelector = By.xpath("//span[text() = 'Orders']");
    By openShowAllOrdersPageSelector = By.xpath("//a[text() = 'Show All Orders']");
    By labelHomePageSelector = By.xpath("//span[@class='m-r-sm text-muted welcome-message']");
    By nameAccountHeaderSelector = By.xpath("(//strong[@class='font-bold'])[2]");

    By btnLogOutInputSelector = By.xpath("//form[@id='j_idt63']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void openHomePage() {
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(d->d.findElement(labelHomePageSelector)).isDisplayed();
    }

    public String headerNameAccount() {
        return driver.findElement(nameAccountHeaderSelector).getText();
    }

    public void clickBtnLogOut() {
        driver.findElement(btnLogOutInputSelector).click();
    }

    @Step ("Click [Menu/ Customer] button on side bar")
    public void clickCustomerMenuButton() {
        driver.findElement(customerMenuButtonSelector).click();
    }

    @Step ("Click [Show All Customers] button on side bar")
    public void openShowAllCustomersPage() {
        driver.findElement(openShowAllCustomersPageSelector).click();
    }

    @Step ("Click [Create Customer] button on site bar")
    public void openCreateCustomerPage() {
        driver.findElement(openCreateCustomerPageSelector).click();
    }

    @Step ("Click [Menu/ Opportunities] button on side bar")
    public void clickOpportunitiesMenuButton() {
        driver.findElement(opportunitiesMenuButtonSelector).click();
    }

    @Step ("Click [Show All Opportunities] button on side bar")
    public void openShowAllOpportunitiesPage() {
        driver.findElement(openShowAllOpportunitiesPageSelector).click();
    }

    @Step ("Click [Menu/ Orders] button on side bar")
    public void clickOrdersMenuButton() {
        driver.findElement(ordersMenuButtonSelector).click();
    }

    @Step ("Click [Show All Orders] button on side bar")
    public void openShowAllOrdersPage() {
        driver.findElement(openShowAllOrdersPageSelector).click();
    }
}
