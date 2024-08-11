package page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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

    By productsMenuButtonSelector;
    By openShowAllProductsPageSelector;
    By openCreateProductPageSelector;

    By opportunitiesMenuButtonSelector = By.xpath("//span[text() = 'Opportunities']");
    By showAllOpportunitiesButtonSelector = By.xpath("//a[text() = 'Show All Opportunitys']");

    By ordersMenuButtonSelector = By.xpath("//span[text() = 'Orders']");
    By showAllOrdersButtonSelector = By.xpath("//a[text() = 'Show All Orders']");

    public SideBar(WebDriver driver) {
        this.driver = driver;
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
    public void openCreateCustomerPage() {
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
}
