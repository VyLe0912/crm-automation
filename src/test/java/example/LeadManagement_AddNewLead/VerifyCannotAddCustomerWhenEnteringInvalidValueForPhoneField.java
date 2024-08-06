package example.LeadManagement_AddNewLead;

import com.github.javafaker.Faker;
import io.qameta.allure.Allure;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import page.CreateCustomerPage;
import page.LoginPage;
import page.ShowAllCustomersPage;
import utils.ConfigReader;

import java.time.Duration;

public class VerifyCannotAddCustomerWhenEnteringInvalidValueForPhoneField {
    WebDriver driver;
    ConfigReader configReader;
    LoginPage loginPage;
    ShowAllCustomersPage showAllCustomersPage;
    CreateCustomerPage createCustomerPage;
    SoftAssert softAssert;
    Faker faker;

    String name;
    String email;
    String phoneAbc, phoneAbc123;
    String address;

    @BeforeMethod
    public void setUp() {
        softAssert = new SoftAssert();
        driver = new ChromeDriver();
        configReader = new ConfigReader();
        loginPage = new LoginPage(driver);
        showAllCustomersPage = new ShowAllCustomersPage(driver);
        createCustomerPage = new CreateCustomerPage(driver);
        faker = new Faker();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        name = RandomStringUtils.randomAlphabetic(1, 50);
        email = faker.internet().emailAddress();
        phoneAbc = RandomStringUtils.randomAlphabetic(10);
        phoneAbc123 = RandomStringUtils.randomAlphanumeric(10);
        address = faker.address().fullAddress();
    }

    @Test
    public void test() {

        Allure.step("Open CRM website");
        driver.get(configReader.getUrl());

        Allure.step("Login success");
        loginPage.login("abcTrang@gmail.com", "123123");
        showAllCustomersPage.waitForShowAllCustomersPageIsDisplayed();

        Allure.step("Click [New customer] button");
        showAllCustomersPage.clickNewCustomerButton();
        showAllCustomersPage.waitForCreateCustomerPageIsDisplayed();

        // nhap ki tu cho truong [Phone]
        Allure.step("Input character for [Phone] field");
        createCustomerPage.phoneInput(phoneAbc);

        Allure.step("Input valid data for [Email], [Phone], [Address] field");
        createCustomerPage.nameInput(name);
        createCustomerPage.emailInput(email);
        createCustomerPage.addressInput(address);

        Allure.step("Click [Create a customer] button");
        createCustomerPage.clickCreateACustomerButton();

        //kiem tra thong bao tren cac truong
        softAssert.assertTrue(createCustomerPage.isCreateCustomerPageDisplayed(), "Create success");
        softAssert.assertEquals(createCustomerPage.getErrorForPhoneField(), "Only numbers 0-9", "No message in name field");


        // nhap ki tu va so cho truong [Phone]
        Allure.step("Input character for [Phone] field");
        createCustomerPage.phoneInput(phoneAbc123);

        Allure.step("Input valid data for [Email], [Phone], [Address] field");
        createCustomerPage.nameInput(name);
        createCustomerPage.emailInput(email);
        createCustomerPage.addressInput(address);

        Allure.step("Click [Create a customer] button");
        createCustomerPage.clickCreateACustomerButton();

        //kiem tra thong bao tren cac truong
        softAssert.assertTrue(createCustomerPage.isCreateCustomerPageDisplayed(), "Create success");
        softAssert.assertEquals(createCustomerPage.getErrorForPhoneField(), "Only numbers 0-9", "No message in name field");

        softAssert.assertAll();
    }

    @AfterMethod(alwaysRun = true)
    public void cleanUp() {
        driver.quit();
    }
}
