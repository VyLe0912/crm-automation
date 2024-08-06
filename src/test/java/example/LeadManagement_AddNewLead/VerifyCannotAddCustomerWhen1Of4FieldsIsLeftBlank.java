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

public class VerifyCannotAddCustomerWhen1Of4FieldsIsLeftBlank {
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

        name = faker.name().name();
        email = faker.internet().emailAddress();
        phone = RandomStringUtils.randomNumeric(10);
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

        // 1. Bo trong truong [Name]
        Allure.step("Leave [Name] field blank");
        createCustomerPage.emailInput(email);
        createCustomerPage.phoneInput(phone);
        createCustomerPage.addressInput(address);

        Allure.step("Click [Create a customer] button");
        createCustomerPage.clickCreateACustomerButton();

        //kiem tra thong bao tren cac truong
        softAssert.assertTrue(createCustomerPage.isCreateCustomerPageDisplayed(), "Create success");
        softAssert.assertEquals(createCustomerPage.getErrorForNameField(), "Please enter your name", "No message in name field");


        // 2. Bo trong truong [Email]
        Allure.step("Leave [Email] field blank");
        createCustomerPage.nameInput(name);
        createCustomerPage.clearEmail();

        Allure.step("Click [Create a customer] button");
        createCustomerPage.clickCreateACustomerButton();

        //kiem tra thong bao tren cac truong
        softAssert.assertTrue(createCustomerPage.isCreateCustomerPageDisplayed(), "Create success");
        softAssert.assertEquals(createCustomerPage.getErrorForEmailField(), "Please enter your email", "No message in name field");


        // 3. Bo trong truong [Phone]
        Allure.step("Leave [Phone] field blank");
        createCustomerPage.emailInput(email);
        createCustomerPage.clearPhone();

        Allure.step("Click [Create a customer] button");
        createCustomerPage.clickCreateACustomerButton();

        //kiem tra thong bao tren cac truong
        softAssert.assertTrue(createCustomerPage.isCreateCustomerPageDisplayed(), "Create success");
        softAssert.assertEquals(createCustomerPage.getErrorForPhoneField(), "Please enter your phone", "No message in name field");


        // 4. Bo trong truong [Address]
        Allure.step("Leave [Email] field blank");
        createCustomerPage.phoneInput(phone);
        createCustomerPage.clearAddress();

        Allure.step("Click [Create a customer] button");
        createCustomerPage.clickCreateACustomerButton();

        //kiem tra thong bao tren cac truong
        softAssert.assertTrue(createCustomerPage.isCreateCustomerPageDisplayed(), "Create success");
        softAssert.assertEquals(createCustomerPage.getErrorForAddressField(), "Please enter your address", "No message in name field");

        softAssert.assertAll();
    }

    @AfterMethod(alwaysRun = true)
    public void cleanUp() {
        driver.quit();
    }
}
