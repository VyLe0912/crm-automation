package example.ProductManagement;

import com.github.javafaker.Faker;
import example.TestBase;
import io.qameta.allure.Allure;
import models.ProductForm;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import page.Login.LoginPage;
import page.Product.EditProduct;
import page.Product.ShowAllProducts;
import page.Products.CreateProductPage;
import page.SideBar.SideBar;

public class PM_04_04_VerifyUserFailedEditProductInfoWhenTwoFieldsAreBlank extends TestBase {
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
    public void VerifyUserFailedEditProductInfoWhenTwoFieldsAreBlank() {

        Allure.step("Login function");
        loginPage.loginWithDefaultAccount();

        Allure.step("Verify open Show All Products page");
        sideBar.openShowAllProductsPage();

        showAllProducts.clickBtnEditProduct();

        createProduct.deleteAllTextBox();

        Allure.step("Name and price fields are blank");
        productForm = new ProductForm("", "", discount);
        editProduct.editProduct(productForm);

        softAssert.assertEquals(createProduct.getMessProName(), "Please enter product name", "Error name");
        softAssert.assertEquals(createProduct.getMessProPrice(), "Please enter price", "Error price");

        createProduct.deleteAllTextBox();

        Allure.step("Price and discount fields are blank");
        productForm = new ProductForm(name, "", "");
        editProduct.editProduct(productForm);

        softAssert.assertEquals(createProduct.getMessProPrice(), "Please enter price", "Error price");
        softAssert.assertEquals(createProduct.getMessProDiscount(), "Please enter discount", "Error discount");

        createProduct.deleteAllTextBox();

        Allure.step("Name and discount fields are blank");
        productForm = new ProductForm("", price, "");
        editProduct.editProduct(productForm);

        softAssert.assertEquals(createProduct.getMessProName(), "Please enter product name", "Error name");
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
