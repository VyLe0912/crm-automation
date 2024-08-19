package example.ProductManagement;

import example.TestBase;
import io.qameta.allure.Allure;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import page.LoginPage;
import page.Product.CreateProduct;
import page.Product.ProductForm;
import page.SideBar;

public class PM_01_03_VerifyUserFailedAddProductWhenAllFieldsAreBlank extends TestBase {
    @BeforeMethod
    public void setUp() {
        super.setUp();
        softAssert = new SoftAssert();
        loginPage = new LoginPage(driver);
        sideBar = new SideBar(driver);
        createProduct = new CreateProduct(driver);
        form = new ProductForm("", "", "");
    }

    @Test
    public void VerifyUserFailedAddProductWhenAllFieldsAreBlank() {

        Allure.step("Login function");
        loginPage.loginWithDefaultAccount();

        Allure.step("Open create product page");
        sideBar.clickProductMenuButton();
        sideBar.openCreateProductPage();

        Allure.step("Verify add product failed when all fields are blank");
        createProduct.createProduct(form);

        //KT thong bao truong Product name
        softAssert.assertEquals(createProduct.getMessProName(), "Please enter product name", "Error ProName");

        //KT thong bao truong Product price
        softAssert.assertEquals(createProduct.getMessProPrice(), "Please enter price", "Error ProPrice");

        //KT thong bao truong Product discount
        softAssert.assertEquals(createProduct.getMessProDiscount(), "Please enter discount", "Error ProDiscount");

        softAssert.assertAll();
    }

    SoftAssert softAssert;
    LoginPage loginPage;
    SideBar sideBar;
    CreateProduct createProduct;
    ProductForm form;
}
