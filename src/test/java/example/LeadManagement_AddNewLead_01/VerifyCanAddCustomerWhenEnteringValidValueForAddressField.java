package example.LeadManagement_AddNewLead_01;

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

public class VerifyCanAddCustomerWhenEnteringValidValueForAddressField {
    WebDriver driver;
    ConfigReader configReader;
    LoginPage loginPage;
    ShowAllCustomersPage showAllCustomersPage;
    CreateCustomerPage createCustomerPage;
    SoftAssert softAssert;
    Faker faker;

    String name;
    String email;
    String phone;
    String address1, address99, address100;

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

        name = faker.name().name();
        email = faker.internet().emailAddress();
        phone = RandomStringUtils.randomNumeric(10);
        address1 = RandomStringUtils.randomAlphanumeric(1);
        address99 = RandomStringUtils.randomAlphanumeric(99);
        address100 = RandomStringUtils.randomAlphanumeric(100);
    }

    @Test
    public void testLM_01_09() {

        Allure.step("Open CRM website");
        driver.get(configReader.getUrl());

        Allure.step("Login success");
        loginPage.login("abcTrang@gmail.com", "123123");
        showAllCustomersPage.waitForShowAllCustomersPageIsDisplayed();


        //1 ki tu cho truong [Address]
        Allure.step("Click [New customer] button");
        showAllCustomersPage.clickNewCustomerButton();
        showAllCustomersPage.waitForCreateCustomerPageIsDisplayed();

        Allure.step("Input valid data for [Name], [Email], [Phone] field");
        createCustomerPage.nameInput(name);
        createCustomerPage.emailInput(email);
        createCustomerPage.phoneInput(phone);

        Allure.step("Input 1 character for [Address] field");
        createCustomerPage.addressInput(address1);

        Allure.step("Click [Create a customer] button");
        createCustomerPage.clickCreateACustomerButton();

        softAssert.assertTrue(showAllCustomersPage.isShowAllCustomerPageDisplayed(), "[Show all customers] page is not displayed");

        //99 ki tu cho truong [Address]
        Allure.step("Click [New customer] button");
        showAllCustomersPage.clickNewCustomerButton();
        showAllCustomersPage.waitForCreateCustomerPageIsDisplayed();

        Allure.step("Input valid data for [Name], [Email], [Phone] field");
        createCustomerPage.nameInput(name);
        createCustomerPage.emailInput(email);
        createCustomerPage.phoneInput(phone);

        Allure.step("Input 99 character for [Address] field");
        createCustomerPage.addressInput(address99);

        Allure.step("Click [Create a customer] button");
        createCustomerPage.clickCreateACustomerButton();

        softAssert.assertTrue(showAllCustomersPage.isShowAllCustomerPageDisplayed(), "[Show all customers] page is not displayed");

        //100 ki tu cho truong [Address]
        Allure.step("Click [New customer] button");
        showAllCustomersPage.clickNewCustomerButton();
        showAllCustomersPage.waitForCreateCustomerPageIsDisplayed();

        Allure.step("Input valid data for [Name], [Email], [Phone] field");
        createCustomerPage.nameInput(name);
        createCustomerPage.emailInput(email);
        createCustomerPage.phoneInput(phone);

        Allure.step("Input 100 character for [Address] field");
        createCustomerPage.addressInput(address100);

        Allure.step("Click [Create a customer] button");
        createCustomerPage.clickCreateACustomerButton();

        softAssert.assertTrue(showAllCustomersPage.isShowAllCustomerPageDisplayed(), "[Show all customers] page is not displayed");

        softAssert.assertAll();
    }

    @AfterMethod(alwaysRun = true)
    public void cleanUp() {
        driver.quit();
    }
}
