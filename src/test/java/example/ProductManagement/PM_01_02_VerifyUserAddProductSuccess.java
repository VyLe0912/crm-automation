package example.ProductManagement;

import com.github.javafaker.Faker;
import example.TestBase;
import io.qameta.allure.Allure;
import models.ProductForm;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import page.Login.LoginPage;
import page.Products.CreateProductPage;
import models.ProductForm;
import page.Product.ShowAllProducts;
import page.SideBar.SideBar;


public class PM_01_02_VerifyUserAddProductSuccess extends TestBase {
    @BeforeMethod
    public void setUp() {
        super.setUp();
        softAssert = new SoftAssert();
        loginPage = new LoginPage(driver);
        sideBar = new SideBar(driver);
        createProduct = new CreateProductPage(driver);
        faker = new Faker();
        name = faker.commerce().productName();
        price = faker.commerce().price();
        discount = faker.commerce().price();
        showAllProducts = new ShowAllProducts(driver);

        productForm = new ProductForm(name, price,discount);
    }

    @Test
    public void VerifyUserAddProductSuccess() {

        Allure.step("Login function");
        loginPage.loginWithDefaultAccount();

        Allure.step("Open create product page");
        sideBar.clickProductMenuButton();
        sideBar.openCreateProductPage();

        Allure.step("Create product with valid values");
        createProduct.createProduct(productForm);

        Allure.step("Open Show all products page");
        sideBar.openShowAllProductsPage();
        softAssert.assertTrue(showAllProducts.showAllProductsPageIsDisplayed(), "Show all products page is not displayed");

        softAssert.assertEquals(showAllProducts.getProductNameByIndex(1), name, "New product is not at the top of the list");
        softAssert.assertEquals(showAllProducts.getProductPriceByIndex(1), price, "New product is not at the top of the list");
        softAssert.assertEquals(showAllProducts.getProductDiscountByIndex(1), discount, "New product is not at the top of the list");

        softAssert.assertAll();
    }

    SoftAssert softAssert;
    LoginPage loginPage;
    SideBar sideBar;
    CreateProductPage createProduct;
    ShowAllProducts showAllProducts;
    ProductForm productForm;
    Faker faker;
    String name;
    String price;
    String discount;
}
