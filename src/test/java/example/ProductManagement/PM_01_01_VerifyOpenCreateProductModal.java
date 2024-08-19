package example.ProductManagement;

import example.TestBase;
import io.qameta.allure.Allure;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import page.Login.LoginPage;
import page.Products.CreateProductPage;
import page.SideBar.SideBar;

public class PM_01_01_VerifyOpenCreateProductModal extends TestBase {
    @BeforeMethod
    public void setUp() {
        super.setUp();
        softAssert = new SoftAssert();
        loginPage = new LoginPage(driver);
        sideBar = new SideBar(driver);
        createProduct = new CreateProductPage(driver);
    }

    @Test
    public void VerifyOpenCreateProductModal() {

        Allure.step("Login function");
        loginPage.loginWithDefaultAccount();

        Allure.step("Verify open Create Product page");
        sideBar.clickProductMenuButton();
        sideBar.openCreateProductPage();
        softAssert.assertEquals(createProduct.getLabelCreateProductPage(), "Add Product", "failed to open Create Product page");
        softAssert.assertAll();
    }

    SoftAssert softAssert;
    LoginPage loginPage;
    SideBar sideBar;
    CreateProductPage createProduct;
}
