package Product_PM_03_SearchProductByName;

import example.TestBase;
import io.qameta.allure.Allure;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import page.LoginPage;
import page.Product.CreateProduct;
import page.SideBar;

public class VerifySearchProductByColumnProductName extends TestBase {
    @BeforeMethod
    public void setUp() {
        super.setUp();
        softAssert = new SoftAssert();
        loginPage = new LoginPage(driver);
        sideBar = new SideBar(driver);
        createProduct = new CreateProduct(driver);
    }

    @Test
    public void VerifySearchProductByColumnProductName() {

        Allure.step("Login function");
        loginPage.loginWithDefaultAccount();

        Allure.step("Verify open Create Product page");
        sideBar.clickProductMenuButton();
        sideBar.openShowAllProductsPage();



    }

    SoftAssert softAssert;
    LoginPage loginPage;
    SideBar sideBar;
    CreateProduct createProduct;
}
