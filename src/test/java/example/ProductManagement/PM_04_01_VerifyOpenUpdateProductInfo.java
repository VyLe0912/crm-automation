package example.ProductManagement;

import example.TestBase;
import io.qameta.allure.Allure;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import page.LoginPage;
import page.Product.CreateProduct;
import page.Product.ShowAllProducts;
import page.SideBar;

public class PM_04_01_VerifyOpenUpdateProductInfo extends TestBase {
    @BeforeMethod
    public void setUp() {
        super.setUp();
        softAssert = new SoftAssert();
        loginPage = new LoginPage(driver);
        sideBar = new SideBar(driver);
        showAllProducts = new ShowAllProducts(driver);
    }

    @Test
    public void VerifyOpenUpdateProductInfo() {

        Allure.step("Login function");
        loginPage.loginWithDefaultAccount();

        Allure.step("Verify open Create Product page");
        sideBar.openShowAllProductsPage();

        showAllProducts.clickBtnEditProduct();
        softAssert.assertEquals(showAllProducts.getLabelEditProductPage(), "Edit Product", "Failed to open Edit page");

        softAssert.assertAll();
    }

    SoftAssert softAssert;
    LoginPage loginPage;
    SideBar sideBar;
    ShowAllProducts showAllProducts;
}
