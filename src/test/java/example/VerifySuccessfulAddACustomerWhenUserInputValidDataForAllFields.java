package example;

import com.github.javafaker.Faker;
import io.qameta.allure.Allure;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import page.CreateCustomerPage;
import page.LoginPage;
import page.ShowAllCustomersPage;
import page.utils.ConfigReader;

import java.time.Duration;

public class VerifySuccessfulAddACustomerWhenUserInputValidDataForAllFields {
    WebDriver driver;
    ConfigReader configReader;
    LoginPage loginPage;
    ShowAllCustomersPage showAllCustomersPage;
    CreateCustomerPage createCustomerPage;
    Faker faker;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        configReader = new ConfigReader();
        loginPage = new LoginPage(driver);
        showAllCustomersPage = new ShowAllCustomersPage(driver);
        createCustomerPage = new CreateCustomerPage(driver);
        faker = new Faker();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void test() {

        SoftAssert softAssert = new SoftAssert();

        String name = faker.name().name();
        String email = faker.internet().emailAddress();
        String phone = faker.phoneNumber().phoneNumber();
        String address = faker.address().fullAddress();

        Allure.step("Open CRM website");
        driver.get(configReader.getUrl());

        Allure.step("Login success");
        loginPage.login("abcTrang@gmail.com", "123123");
        showAllCustomersPage.verifyShowAllCustomersPageIsDisplayed();

        softAssert.assertTrue(showAllCustomersPage.showAllCustomerPageIsDisplayed(), "[Show all customers] page is not displayed");
        Allure.step("Click [New customer] button");
        showAllCustomersPage.clickNewCustomerButton();
        showAllCustomersPage.verifyCreateCustomerPageIsDisplayed();

        softAssert.assertTrue(createCustomerPage.createCustomerPageIsDisplayed(), "[Create customer] page is not displayed");
        Allure.step("Input valid data for all fields");
        createCustomerPage.createACustomer(name, email, phone, address);

        Allure.step("Click [Create a customer] button");
        createCustomerPage.clickCreateACustomerButton();
        createCustomerPage.waitCloseCreateCustomerForm();

        softAssert.assertTrue(showAllCustomersPage.showAllCustomerPageIsDisplayed(), "[Show all customers] page is not displayed");

        //softAssert cho xác minh ng dùng đc theem vào thanh công

        softAssert.assertAll();

    }

    @AfterMethod(alwaysRun = true)
    public void cleanUp() {
        driver.quit();
    }
}
