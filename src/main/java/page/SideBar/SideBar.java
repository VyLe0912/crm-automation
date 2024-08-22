package page.SideBar;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SideBar {
    WebDriver driver;

    By customerMenuButtonSelector = By.xpath("//span[text() = 'Customer']");
    By showAllCustomersButtonSelector = By.xpath("//a[text() = 'Show All Customers']");
    By createCustomerButtonSelector = By.xpath("//a[text() = 'Create Customer']");

    By reminderMenuButtonSelector;
    By openShowAllRemindersPageSelector;

    By campaignsMenuButtonSelector;
    By openShowAllCampaignsPageSelector;
    By openCreateCampaignPageSelector;
    By openShowAllCampaignTypesPageSelector;
    By openCreateCampaignTypePageSelector;

    By productsMenuButtonSelector = By.xpath("(//span[@class='nav-label'])[4]");
    By showAllProductsButtonSelector = By.xpath("(//ul[@class='nav nav-second-level collapse in']/li/a)[1]");
    By createProductButtonSelector = By.xpath("(//ul[@class='nav nav-second-level collapse in']/li/a)[2]");

    By opportunitiesMenuButtonSelector = By.xpath("//span[text() = 'Opportunities']");
    By showAllOpportunitiesButtonSelector = By.xpath("//a[text() = 'Show All Opportunitys']");

    By ordersMenuButtonSelector = By.xpath("//span[text() = 'Orders']");
    By labelHomePageSelector = By.xpath("//span[@class='m-r-sm text-muted welcome-message']");

    By nameAccountHeaderSelector = By.xpath("(//strong[@class='font-bold'])[2]");
    By btnLogOutInputSelector = By.xpath("//form[@id='j_idt63']");
    By showAllOrdersButtonSelector = By.xpath("//a[text() = 'Show All Orders']");

    public SideBar(WebDriver driver) {
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
    public void expandCustomerMenu() {
        driver.findElement(customerMenuButtonSelector).click();
    }

    @Step ("Click [Show All Customers] button on side bar")
    public void clickShowAllCustomersButton() {
        driver.findElement(showAllCustomersButtonSelector).click();
    }

    @Step("Open [Show All Customers] page")
    public void openShowAllCustomersPage() {
        expandCustomerMenu();
        clickShowAllCustomersButton();
    }

    @Step ("Click [Create Customer] button on site bar")
    public void clickCreateCustomerButton() {
        driver.findElement(createCustomerButtonSelector).click();
    }

    @Step("Open [Create Customer] page")
    public void openCreateNewCustomerPage() {
        expandCustomerMenu();
        clickCreateCustomerButton();
    }

    @Step ("Click [Menu/ Opportunities] button on side bar")
    public void expandOpportunitiesMenu() {
        driver.findElement(opportunitiesMenuButtonSelector).click();
    }

    @Step ("Click [Show All Opportunities] button on side bar")
    public void clickShowAllOpportunitiesButton() {
        driver.findElement(showAllOpportunitiesButtonSelector).click();
    }

    @Step("Open [Show All Opportunities] page")
    public void openShowAllOpportunitiesPage() {
        expandOpportunitiesMenu();
        clickShowAllOpportunitiesButton();
    }

    @Step ("Click [Menu/ Orders] button on side bar")
    public void expandOrdersMenu() {
        driver.findElement(ordersMenuButtonSelector).click();
    }

    @Step ("Click [Show All Orders] button on side bar")
    public void clickShowAllOrdersButton() {
        driver.findElement(showAllOrdersButtonSelector).click();
    }

    @Step("Open [Show All Orders] page")
    public void openShowALlOrdersPage() {
        expandOrdersMenu();
        clickShowAllOrdersButton();
    }

    @Step("Click [Menu/ Products] button on side bar")
    public void clickProductMenuButton() {
        driver.findElement(productsMenuButtonSelector).click();
    }

    public void clickShowAllProductsButton() {
        driver.findElement(showAllProductsButtonSelector).click();
    }

    @Step("Open [Show All Product] page")
    public void openShowAllProductsPage() {
        clickProductMenuButton();
        clickShowAllProductsButton();
    }


    public void openCreateProductPage() {
        driver.findElement(createProductButtonSelector).click();
    }

}
