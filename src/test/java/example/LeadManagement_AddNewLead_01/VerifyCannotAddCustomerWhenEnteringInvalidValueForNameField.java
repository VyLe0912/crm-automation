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

public class VerifyCannotAddCustomerWhenEnteringInvalidValueForNameField {
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

        name = RandomStringUtils.randomAlphabetic(51);
        email = faker.internet().emailAddress();
        phone = RandomStringUtils.randomNumeric(10);
        address = faker.address().fullAddress();
    }

    @Test
    public void testLM_01_04() {

        Allure.step("Open CRM website");
        driver.get(configReader.getUrl());

        Allure.step("Login success");
        loginPage.login("abcTrang@gmail.com", "123123");
        showAllCustomersPage.waitForShowAllCustomersPageIsDisplayed();

        Allure.step("Click [New customer] button");
        showAllCustomersPage.clickNewCustomerButton();
        showAllCustomersPage.waitForCreateCustomerPageIsDisplayed();


        Allure.step("Input 51 character for [Name] field");
        createCustomerPage.nameInput(name);

        Allure.step("Input valid data for [Email], [Phone], [Address] field");
        createCustomerPage.emailInput(email);
        createCustomerPage.phoneInput(phone);
        createCustomerPage.addressInput(address);

        Allure.step("Click [Create a customer] button");
        createCustomerPage.clickCreateACustomerButton();

        //kiem tra thong bao tren cac truong
        softAssert.assertTrue(createCustomerPage.isCreateCustomerPageDisplayed(), "Create success");
        softAssert.assertEquals(createCustomerPage.getErrorForNameField(), "size must be between 0 and 50", "No message in name field");

        softAssert.assertAll();
    }

    @AfterMethod(alwaysRun = true)
    public void cleanUp() {
        driver.quit();
    }
}
