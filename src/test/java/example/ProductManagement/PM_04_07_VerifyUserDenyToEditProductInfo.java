package example.ProductManagement;

import example.TestBase;
import io.qameta.allure.Allure;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import page.LoginPage;
import page.Product.EditProduct;
import page.Product.ShowAllProducts;
import page.SideBar;

public class PM_04_07_VerifyUserDenyToEditProductInfo extends TestBase {
    @BeforeMethod
    public void setUp() {
        super.setUp();
        softAssert = new SoftAssert();
        loginPage = new LoginPage(driver);
        sideBar = new SideBar(driver);
        showAllProducts = new ShowAllProducts(driver);
        editProduct = new EditProduct(driver);
    }

    @Test
    public void VerifyUserDenyToEditProductInfo() {

        Allure.step("Login function");
        loginPage.loginWithDefaultAccount();

        Allure.step("Verify open Show All Products page");
        sideBar.openShowAllProductsPage();

        showAllProducts.clickBtnEditProduct();

        editProduct.clickBtnCancelEditProduct();

        softAssert.assertEquals(showAllProducts.getLabelShowALlProductsPage(), "Products List", "Error button cancel");

        softAssert.assertAll();
    }

    SoftAssert softAssert;
    LoginPage loginPage;
    SideBar sideBar;
    ShowAllProducts showAllProducts;
    EditProduct editProduct;
}
