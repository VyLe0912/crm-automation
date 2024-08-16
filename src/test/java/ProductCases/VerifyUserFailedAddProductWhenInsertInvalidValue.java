package ProductCases;

import com.github.javafaker.Faker;
import example.TestBase;
import io.qameta.allure.Allure;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import page.LoginPage;
import page.Product.CreateProduct;
import page.Product.ProductForm;
import page.SideBar;

public class VerifyUserFailedAddProductWhenInsertInvalidValue extends TestBase {
    @BeforeMethod
    public void setUp() {
        super.setUp();
        softAssert = new SoftAssert();
        loginPage = new LoginPage(driver);
        sideBar = new SideBar(driver);
        createProduct = new CreateProduct(driver);
        faker = new Faker();
        name = faker.commerce().productName();

        productForm = new ProductForm(name, "vbdmskbdjfdlncdjn", "fkfkoasfanfsoano");
    }

    @Test
    public void VerifyUserFailedAddProductWhenInsertInvalidValue() {

        Allure.step("Login function");
        loginPage.loginFunction();

        Allure.step("Open create product page");
        sideBar.clickProductMenuButton();
        sideBar.openCreateProductPage();

        Allure.step("Verify add product failed when insert invalid value in price and discount");
        createProduct.createProduct(productForm);
        softAssert.assertEquals(createProduct.getMessProPrice(), "bookForm:pp: 'vbdmskbdjfdlncdjn' is not a number pattern. Example: #,##0.0#", "Error price");
        softAssert.assertEquals(createProduct.getMessProDiscount(), "bookForm:pd: 'fkfkoasfanfsoano' is not a number pattern. Example: #,##0.0#", "Error discount");

        softAssert.assertAll();
    }

    SoftAssert softAssert;
    LoginPage loginPage;
    SideBar sideBar;
    CreateProduct createProduct;
    ProductForm productForm;
    Faker faker;
    String name;
}
