package example.ProductManagement;

import com.github.javafaker.Faker;
import example.TestBase;
import io.qameta.allure.Allure;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import page.Login.LoginPage;
import page.Products.CreateProductPage;
import models.ProductForm;
import page.SideBar.SideBar;


public class PM_01_06_VerifyUserFailedAddProductWhenInsertInvalidValue extends TestBase {
    @BeforeMethod
    public void setUp() {
        super.setUp();
        softAssert = new SoftAssert();
        loginPage = new LoginPage(driver);
        sideBar = new SideBar(driver);
        createProduct = new CreateProductPage(driver);
        faker = new Faker();
        name = faker.commerce().productName();

        productForm = new ProductForm(name, "vbdmskbdjfdlncdjn", "fkfkoasfanfsoano");
    }

    @Test
    public void VerifyUserFailedAddProductWhenInsertInvalidValue() {

        Allure.step("Login function");
        loginPage.loginWithDefaultAccount();

        Allure.step("Open create product page");
        sideBar.clickProductMenuButton();
        sideBar.openCreateProductPage();

        Allure.step("Verify add product failed when insert invalid value in price and discount");
        createProduct.createProduct(productForm);
        softAssert.assertEquals(createProduct.getMessProPrice(), "Please enter numbers only", "Error price");
        softAssert.assertEquals(createProduct.getMessProDiscount(), "Please enter numbers only", "Error discount");

        softAssert.assertAll();
    }

    SoftAssert softAssert;
    LoginPage loginPage;
    SideBar sideBar;
    CreateProductPage createProduct;
    ProductForm productForm;
    Faker faker;
    String name;
}
