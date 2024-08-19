package example.ProductManagement;

import com.github.javafaker.Faker;
import example.TestBase;
import io.qameta.allure.Allure;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import page.Login.LoginPage;
import page.Products.CreateProductPage;
import page.Product.EditProduct;
import models.ProductForm;
import page.Product.ShowAllProducts;
import page.SideBar.SideBar;


public class PM_04_03_VerifyUserFailedEditProductInfoWhenOneFieldIsBlank extends TestBase {
    @BeforeMethod
    public void setUp() {
        super.setUp();
        softAssert = new SoftAssert();
        loginPage = new LoginPage(driver);
        sideBar = new SideBar(driver);
        showAllProducts = new ShowAllProducts(driver);
        createProduct = new CreateProductPage(driver);
        editProduct = new EditProduct(driver);

        faker = new Faker();
        name = faker.commerce().productName();
        price = faker.commerce().price();
        discount = faker.commerce().price();
    }

    @Test
    public void VerifyUserFailedEditProductInfoWhenOneFieldIsBlank() {

        Allure.step("Login function");
        loginPage.loginWithDefaultAccount();

        Allure.step("Verify open Show All Products page");
        sideBar.openShowAllProductsPage();

        showAllProducts.clickBtnEditProduct();

        createProduct.deleteAllTextBox();

        productForm = new ProductForm("", price, discount);
        editProduct.editProduct(productForm);

        softAssert.assertEquals(createProduct.getMessProName(), "Please enter product name", "Error name");

        createProduct.deleteAllTextBox();

        productForm = new ProductForm(name, "", discount);
        editProduct.editProduct(productForm);

        softAssert.assertEquals(createProduct.getMessProPrice(), "Please enter price", "Error price");

        createProduct.deleteAllTextBox();

        productForm = new ProductForm(name, price, "");
        editProduct.editProduct(productForm);
        softAssert.assertEquals(createProduct.getMessProDiscount(), "Please enter discount", "Error discount");

        softAssert.assertAll();
    }

    SoftAssert softAssert;
    LoginPage loginPage;
    SideBar sideBar;
    ShowAllProducts showAllProducts;
    CreateProductPage createProduct;
    ProductForm productForm;
    EditProduct editProduct;
    Faker faker;
    String name;
    String price;
    String discount;
}
