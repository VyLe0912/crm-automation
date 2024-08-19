package example.ProductManagement;

import example.TestBase;
import io.qameta.allure.Allure;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import page.LoginPage;
import page.Product.CreateProduct;
import page.Product.EditProduct;
import page.Product.ProductForm;
import page.Product.ShowAllProducts;
import page.SideBar;

public class PM_04_02_VerifyUserFailedEditProductInfoWhenAllFieldsAreBlank extends TestBase {
    @BeforeMethod
    public void setUp() {
        super.setUp();
        softAssert = new SoftAssert();
        loginPage = new LoginPage(driver);
        sideBar = new SideBar(driver);
        showAllProducts = new ShowAllProducts(driver);
        createProduct = new CreateProduct(driver);
        editProduct = new EditProduct(driver);
    }

    @Test
    public void VerifyUserFailedEditProductInfoWhenAllFieldsAreBlank() {

        Allure.step("Login function");
        loginPage.loginWithDefaultAccount();

        Allure.step("Verify open Show All Products page");
        sideBar.openShowAllProductsPage();

        showAllProducts.clickBtnEditProduct();

        createProduct.deleteAllTextBox();

        editProduct.clickBtnSaveEditProduct();

        softAssert.assertEquals(createProduct.getMessProName(), "Please enter product name", "Error name");

        softAssert.assertEquals(createProduct.getMessProPrice(), "Please enter price", "Error price");

        softAssert.assertEquals(createProduct.getMessProDiscount(), "Please enter discount", "Error discount");

        softAssert.assertAll();
    }

    SoftAssert softAssert;
    LoginPage loginPage;
    SideBar sideBar;
    ShowAllProducts showAllProducts;
    CreateProduct createProduct;
    ProductForm productForm;
    EditProduct editProduct;
}
