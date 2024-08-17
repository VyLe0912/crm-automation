package example.LeadManagement_LM_02_ViewTheLeads;

import example.TestBase;
import io.qameta.allure.Allure;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import page.Orders.CreateOrderPage;
import page.Customer.ShowAllCustomersPage;
import page.Products.ShowAllProductsPage;
import page.CustomerInformation.CustomerInformationPage;
import page.Login.LoginPage;
import page.SideBar.SideBar;

public class LM_02_05_VerifyThatTheNumberOfProductsDisplayedOnTheAddOrderScreenIsEqualToTheNumberOfProductsOnTheShowAllProductsScreen extends TestBase {
    ShowAllCustomersPage showAllCustomersPage;
    SoftAssert softAssert;
    LoginPage loginPage;
    SideBar sideBar;
    CustomerInformationPage customerInformationPage;
    CreateOrderPage createOrderPage;
    ShowAllProductsPage showAllProductsPage;
    int totalProduct;

    @BeforeMethod
    public void setUp() {
        super.setUp();
        softAssert = new SoftAssert();
        loginPage = new LoginPage(driver);
        customerInformationPage = new CustomerInformationPage(driver);
        sideBar = new SideBar(driver);
        createOrderPage = new CreateOrderPage(driver);
        showAllCustomersPage = new ShowAllCustomersPage(driver);
        showAllProductsPage = new ShowAllProductsPage(driver);
    }

    @Test
    public void testLM_02_05() {

        Allure.step("Login success");
        loginPage.login("abcTrang@gmail.com", "123123");

        Allure.step("Open [Show All Products] page");
        sideBar.openShowAllProductsPage();
//        showAllProductsPage.waitForLastPageButtonIsDisplayed();
        showAllProductsPage.openLastProductPage();

        //dem so luong product
        totalProduct = showAllProductsPage.getTotalProduct();

        Allure.step("Open [Show All Customer] page");
        sideBar.openShowAllCustomersPage();

        Allure.step("Open [Customer Information] page");
        showAllCustomersPage.openCustomerInformationPage(1);

        Allure.step("Click [Add Order] button");
        customerInformationPage.clickAddOrderButton();

        softAssert.assertEquals(createOrderPage.countRowProduct(), totalProduct, "Unequal number of products");

        softAssert.assertAll();
    }

}
