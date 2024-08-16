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
        price = faker.number().digit();
        discount = faker.number().digit();

        productForm1 = new ProductForm("", price, discount);
        productForm2 = new ProductForm(name, "", discount);
        productForm3 = new ProductForm(name, price, "");
    }

    @Test
    public void VerifyUserFailedAddProductWhenOneFieldIsBlank() {

        Allure.step("Login function");
        loginPage.loginFunction();

        Allure.step("Open create product page");
        sideBar.clickProductMenuButton();
        sideBar.openCreateProductPage();

        Allure.step("Verify add product failed when product name field is blank");
        createProduct.createProduct(productForm1);
        softAssert.assertEquals(createProduct.getMessProName(), "Please enter product name", "Error ProName");

        createProduct.deleteAllTextBox();

        Allure.step("Verify add product failed when product price field is blank");
        createProduct.createProduct(productForm2);
        softAssert.assertEquals(createProduct.getMessProPrice(), "Please enter price", "Error ProPrice");

        createProduct.deleteAllTextBox();

        Allure.step("Verify add product failed when product discount field is blank");
        createProduct.createProduct(productForm3);
        softAssert.assertEquals(createProduct.getMessProDiscount(), "Please enter discount", "Error ProDiscount");

        softAssert.assertAll();
    }

    SoftAssert softAssert;
    LoginPage loginPage;
    SideBar sideBar;
    CreateProduct createProduct;
    ProductForm productForm1;
    ProductForm productForm2;
    ProductForm productForm3;
    Faker faker;
    String name;
    String price;
    String discount;
}
