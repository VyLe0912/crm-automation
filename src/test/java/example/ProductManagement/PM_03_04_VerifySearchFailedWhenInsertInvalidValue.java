package example.ProductManagement;

import example.TestBase;
import io.qameta.allure.Allure;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import page.Login.LoginPage;
import page.Products.CreateProductPage;
import page.Product.ShowAllProducts;
import page.SideBar.SideBar;


public class PM_03_04_VerifySearchFailedWhenInsertInvalidValue extends TestBase {
    @BeforeMethod
    public void setUp() {
        super.setUp();
        softAssert = new SoftAssert();
        loginPage = new LoginPage(driver);
        sideBar = new SideBar(driver);
        showAllProducts = new ShowAllProducts(driver);
    }

    @Test
    public void VerifySearchFailedWhenInsertInvalidValue() {

        Allure.step("Login function");
        loginPage.loginWithDefaultAccount();

        Allure.step("Verify open Create Product page");
        sideBar.openShowAllProductsPage();

        showAllProducts.enterProductNameColumn("iphone");

        showAllProducts.enterProductPriceColumn("mười");

        showAllProducts.enterProductDiscountColumn("năm");

        softAssert.assertEquals(showAllProducts.getMessageNoFound(), "No records found.", "Error search function");

        softAssert.assertAll();
    }

    SoftAssert softAssert;
    LoginPage loginPage;
    SideBar sideBar;
    CreateProductPage createProduct;
    ShowAllProducts showAllProducts;
}
