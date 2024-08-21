package example.ProductManagement;

import example.TestBase;
import io.qameta.allure.Allure;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import page.Login.LoginPage;
import page.Product.EditProduct;
import page.Product.ShowAllProducts;
import page.SideBar.SideBar;

public class PM_04_05_VerifyUserFailedEditProductInfoWhenKeepOldValue extends TestBase {
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
    public void VerifyUserFailedEditProductInfoWhenKeepOldValue() {

        Allure.step("Login function");
        loginPage.loginWithDefaultAccount();

        Allure.step("Verify open Show All Products page");
        sideBar.openShowAllProductsPage();

        showAllProducts.clickBtnEditProduct();

        editProduct.clickBtnSaveEditProduct();

        softAssert.assertEquals(editProduct.getMessProName(), "Please enter new product name", "Error name field");

        softAssert.assertEquals(editProduct.getMessProPrice(), "Please enter new price", "Error price field");

        softAssert.assertEquals(editProduct.getMessProDiscount(), "Please enter new discount", "Error discount field");

        softAssert.assertAll();
    }

    SoftAssert softAssert;
    LoginPage loginPage;
    SideBar sideBar;
    ShowAllProducts showAllProducts;
    EditProduct editProduct;
}
