package example.LeadManagement_AddNewLead_01_10;

import com.github.javafaker.Faker;
import io.qameta.allure.Allure;
import models.CustomerInFormationForm;
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
import page.utils.ConfigReader;

import java.time.Duration;

public class LM_01_08_VerifyCannotAddCustomerWhenEnteringInvalidValueForAddressField {
    WebDriver driver;
    ConfigReader configReader;
    LoginPage loginPage;
    ShowAllCustomersPage showAllCustomersPage;
    CreateCustomerPage createCustomerPage;
    SoftAssert softAssert;
    Faker faker;
    CustomerInFormationForm customerInFor;

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
        address = RandomStringUtils.randomAlphanumeric(101);
        customerInFor = new CustomerInFormationForm(name, email, phone, address);
    }

    @Test
    public void testLM_01_08() {

        Allure.step("Open CRM website");
        driver.get(configReader.getUrl());

        Allure.step("Login success");
        loginPage.login("abcTrang@gmail.com", "123123");
//        showAllCustomersPage.waitForShowAllCustomersPageIsDisplayed();

        Allure.step("Open [Create Customer] page");
        showAllCustomersPage.openCreateCustomerPage();

        Allure.step("Input valid data for [Name], [Email], [Phone] field");
        Allure.step("Input 101 character for [Address] field");
        createCustomerPage.createCustomerInformation(customerInFor);

        //kiem tra thong bao tren cac truong
        softAssert.assertTrue(createCustomerPage.isCreateCustomerPageDisplayed(), "Create success");
        softAssert.assertEquals(createCustomerPage.getErrorForAddressField(), "size must be between 0 and 100", "No message in address field");

        softAssert.assertAll();
    }

    @AfterMethod(alwaysRun = true)
    public void cleanUp() {
        driver.quit();
    }
}
