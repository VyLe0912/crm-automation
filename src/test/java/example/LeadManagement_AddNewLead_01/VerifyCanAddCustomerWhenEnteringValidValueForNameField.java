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

public class VerifyCanAddCustomerWhenEnteringValidValueForNameField {
    WebDriver driver;
    ConfigReader configReader;
    LoginPage loginPage;
    ShowAllCustomersPage showAllCustomersPage;
    CreateCustomerPage createCustomerPage;
    SoftAssert softAssert;
    Faker faker;

    String name1, name49, name50;
    String email;
    String phone;
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

        name1 = RandomStringUtils.randomAlphabetic(1);
        name49 = RandomStringUtils.randomAlphabetic(49);
        name50 = RandomStringUtils.randomAlphabetic(50);
        email = faker.internet().emailAddress();
        phone = RandomStringUtils.randomNumeric(10);
        address = faker.address().fullAddress();
    }

    @Test
    public void testLM_01_05() {

        Allure.step("Open CRM website");
        driver.get(configReader.getUrl());

        Allure.step("Login success");
        loginPage.login("abcTrang@gmail.com", "123123");
        showAllCustomersPage.waitForShowAllCustomersPageIsDisplayed();


        //1 ki tu cho truong [name]
        Allure.step("Click [New customer] button");
        showAllCustomersPage.clickNewCustomerButton();
        showAllCustomersPage.waitForCreateCustomerPageIsDisplayed();

        Allure.step("Input 1 character for [Name] field");
        createCustomerPage.nameInput(name1);

        Allure.step("Input valid data for [Email], [Phone], [Address] field");
        createCustomerPage.emailInput(email);
        createCustomerPage.phoneInput(phone);
        createCustomerPage.addressInput(address);

        Allure.step("Click [Create a customer] button");
        createCustomerPage.clickCreateACustomerButton();

        softAssert.assertTrue(showAllCustomersPage.isShowAllCustomerPageDisplayed(), "[Show all customers] page is not displayed");

        //49 ki tu cho truong [name]
        Allure.step("Click [New customer] button");
        showAllCustomersPage.clickNewCustomerButton();
        showAllCustomersPage.waitForCreateCustomerPageIsDisplayed();

        Allure.step("Input 49 character for [Name] field");
        createCustomerPage.nameInput(name49);

        Allure.step("Input valid data for [Email], [Phone], [Address] field");
        createCustomerPage.emailInput(email);
        createCustomerPage.phoneInput(phone);
        createCustomerPage.addressInput(address);

        Allure.step("Click [Create a customer] button");
        createCustomerPage.clickCreateACustomerButton();

        softAssert.assertTrue(showAllCustomersPage.isShowAllCustomerPageDisplayed(), "[Show all customers] page is not displayed");

        //50 ki tu cho truong [name]
        Allure.step("Click [New customer] button");
        showAllCustomersPage.clickNewCustomerButton();
        showAllCustomersPage.waitForCreateCustomerPageIsDisplayed();

        Allure.step("Input 50 character for [Name] field");
        createCustomerPage.nameInput(name50);

        Allure.step("Input valid data for [Email], [Phone], [Address] field");
        createCustomerPage.emailInput(email);
        createCustomerPage.phoneInput(phone);
        createCustomerPage.addressInput(address);

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
