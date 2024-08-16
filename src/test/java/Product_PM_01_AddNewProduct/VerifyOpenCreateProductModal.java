package Product_PM_01_AddNewProduct;

import example.TestBase;
import io.qameta.allure.Allure;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import page.LoginPage;
import page.Product.CreateProduct;
import page.SideBar;

public class VerifyOpenCreateProductModal extends TestBase {
    @BeforeMethod
    public void setUp() {
        super.setUp();
        softAssert = new SoftAssert();
        loginPage = new LoginPage(driver);
        sideBar = new SideBar(driver);
        createProduct = new CreateProduct(driver);
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
    CreateProduct createProduct;
}
