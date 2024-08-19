package example.ProductManagement;

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

public class VerifyUserFailedAddProductWhenOneFieldIsBlank extends TestBase {
    @BeforeMethod
    public void setUp() {
        super.setUp();
        softAssert = new SoftAssert();
        loginPage = new LoginPage(driver);
        sideBar = new SideBar(driver);
        createProduct = new CreateProduct(driver);
        faker = new Faker();
        name = faker.commerce().productName();
        price = faker.commerce().price();
        discount = faker.commerce().price();
    }

    @Test
    public void VerifyUserFailedAddProductWhenOneFieldIsBlank() {

        Allure.step("Login function");
        loginPage.loginWithDefaultAccount();

        Allure.step("Open create product page");
        sideBar.clickProductMenuButton();
        sideBar.openCreateProductPage();

        Allure.step("Verify add product failed when product name field is blank");
        productForm = new ProductForm("", price, discount);
        createProduct.createProduct(productForm);
        softAssert.assertEquals(createProduct.getMessProName(), "Please enter product name", "Error ProName");

        createProduct.deleteAllTextBox();
        productForm = new ProductForm(name, "", discount);
        Allure.step("Verify add product failed when product price field is blank");
        createProduct.createProduct(productForm);
        softAssert.assertEquals(createProduct.getMessProPrice(), "Please enter price", "Error ProPrice");

        createProduct.deleteAllTextBox();

        Allure.step("Verify add product failed when product discount field is blank");
        productForm = new ProductForm(name, price, "");
        createProduct.createProduct(productForm);
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
