package Product_PM_01_AddNewProduct;

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

public class VerifyUserFailedAddProductWhenTwoFieldsAreBlank extends TestBase {
    @BeforeMethod
    public void setUp() {
        super.setUp();
        softAssert = new SoftAssert();
        loginPage = new LoginPage(driver);
        sideBar = new SideBar(driver);
        createProduct = new CreateProduct(driver);
        faker = new Faker();
        name = faker.commerce().productName();
        price = faker.number().digit();
        discount = faker.number().digit();
    }

    @Test
    public void VerifyUserFailedAddProductWhenTwoFieldsAreBlank() {

        Allure.step("Login function");
        loginPage.loginWithDefaultAccount();

        Allure.step("Open create product page");
        sideBar.clickProductMenuButton();
        sideBar.openCreateProductPage();

        Allure.step("Verify add product failed when product name and price fields are blank");
        productForm = new ProductForm("", "", discount);
        createProduct.createProduct(productForm);
        softAssert.assertEquals(createProduct.getMessProName(), "Please enter product name", "Error ProName");
        softAssert.assertEquals(createProduct.getMessProPrice(), "Please enter price", "Error ProPrice");

        createProduct.deleteAllTextBox();

        Allure.step("Verify add product failed when product name and discount fields are blank");
        productForm = new ProductForm("", price, "");
        createProduct.createProduct(productForm);
        softAssert.assertEquals(createProduct.getMessProName(), "Please enter product name", "Error ProName");
        softAssert.assertEquals(createProduct.getMessProDiscount(), "Please enter discount", "Error ProDiscount");

        createProduct.deleteAllTextBox();

        Allure.step("Verify add product failed when product price and discount fields are blank");
        productForm = new ProductForm("", price, discount);
        createProduct.createProduct(productForm);
        softAssert.assertEquals(createProduct.getMessProPrice(), "Please enter price", "Error ProPrice");
        softAssert.assertEquals(createProduct.getMessProDiscount(), "Please enter discount", "Error ProDiscount");

        softAssert.assertAll();
    }

    SoftAssert softAssert;
    LoginPage loginPage;
    SideBar sideBar;
    CreateProduct createProduct;
    ProductForm productForm;
    Faker faker;
    String name;
    String price;
    String discount;
}
