package example.ProductManagement;

import example.TestBase;
import io.qameta.allure.Allure;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import page.Login.LoginPage;
import page.Product.ShowAllProducts;
import page.SideBar.SideBar;


public class PM_03_01_VerifySearchProductByColumnProductName extends TestBase {
    @BeforeMethod
    public void setUp() {
        super.setUp();
        softAssert = new SoftAssert();
        loginPage = new LoginPage(driver);
        sideBar = new SideBar(driver);
        showAllProducts = new ShowAllProducts(driver);
    }

    @Test
    public void VerifySearchProductByColumnProductName() {

        Allure.step("Login function");
        loginPage.loginWithDefaultAccount();

        Allure.step("Verify open Create Product page");
        sideBar.openShowAllProductsPage();

        showAllProducts.enterProductNameColumn("iPhone");
        softAssert.assertEquals(showAllProducts.allProductsStartWiths("iPhone"), "iPhone X", "Error search function");

        softAssert.assertAll();
    }

    SoftAssert softAssert;
    LoginPage loginPage;
    SideBar sideBar;
    ShowAllProducts showAllProducts;
}
