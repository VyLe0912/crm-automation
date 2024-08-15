package ProductCases;

import example.TestBase;
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
        loginPage.loginFunction();
        sideBar.clickProductMenuButton();
        sideBar.openCreateProductPage();
        softAssert.assertTrue(createProduct.createProductPageIsDisplay(), "failed to open Create Product page");
        softAssert.assertAll();
    }

    SoftAssert softAssert;
    LoginPage loginPage;
    SideBar sideBar;
    CreateProduct createProduct;
}
