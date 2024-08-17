package example.ProductCases;

import example.TestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import page.Login.LoginPage;
import page.Products.CreateProductPage;
import page.SideBar.SideBar;

public class VerifyOpenCreateProductModal extends TestBase {
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
        loginPage.loginFunction();
        sideBar.clickProductMenuButton();
        sideBar.openCreateProductPage();
        softAssert.assertTrue(createProduct.createProductPageIsDisplay(), "failed to open Create Product page");
        softAssert.assertAll();
    }

    SoftAssert softAssert;
    LoginPage loginPage;
    SideBar sideBar;
    CreateProductPage createProduct;
}
