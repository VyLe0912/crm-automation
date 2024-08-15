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
import utils.ConfigReader;

import java.time.Duration;

public class LM_01_06_VerrifyCannotAddCustomerWhenEnteringInvalidValueForEmailField {
    WebDriver driver;
    ConfigReader configReader;
    LoginPage loginPage;
    ShowAllCustomersPage showAllCustomersPage;
    CreateCustomerPage createCustomerPage;
    SoftAssert softAssert;
    Faker faker;
    CustomerInFormationForm customerInFor;

    String name;
    String email1, email2, email3;
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
        email1 = "username";
        email2 = "username.domain";
        email3 = "username@domain";
        phone = RandomStringUtils.randomNumeric(10);
        address = faker.address().fullAddress();

        customerInFor = new CustomerInFormationForm(name, email1, phone, address);
    }

    @Test
    public void testLM_01_06() {

        Allure.step("Open CRM website");
        driver.get(configReader.getUrl());

        Allure.step("Login success");
        loginPage.login("abcTrang@gmail.com", "123123");
//        showAllCustomersPage.waitForShowAllCustomersPageIsDisplayed();


        //'username' for [Email] field
        Allure.step("Open [Create Customer] page");
        showAllCustomersPage.openCreateCustomerPage();

        Allure.step("Input 'username' for [Email] field");
        Allure.step("Input valid data for [Name], [Phone], [Address] field");
        createCustomerPage.createCustomerInformation(customerInFor);

        softAssert.assertTrue(createCustomerPage.isCreateCustomerPageDisplayed(), "Create success");
        softAssert.assertEquals(createCustomerPage.getErrorForEmailField(), "The email is not valid (ex: abc@abc)", "No message in email field");

        //'username.domain' for [Email] field
        Allure.step("Input 'username.domain' for [Email] field");
        customerInFor.setEmail(email2);
        createCustomerPage.createCustomerInformation(customerInFor);

        softAssert.assertTrue(createCustomerPage.isCreateCustomerPageDisplayed(), "Create success");
        softAssert.assertEquals(createCustomerPage.getErrorForEmailField(), "The email is not valid (ex: abc@abc)", "No message in email field");

        //'username@domain' for [Email] field
        Allure.step("Input 'username@domain' for [Email] field");
        customerInFor.setEmail(email3);
        createCustomerPage.createCustomerInformation(customerInFor);

        softAssert.assertTrue(createCustomerPage.isCreateCustomerPageDisplayed(), "Create success");
        softAssert.assertEquals(createCustomerPage.getErrorForEmailField(), "The email is not valid (ex: abc@abc)", "No message in email field");

        softAssert.assertAll();
    }

    @AfterMethod(alwaysRun = true)
    public void cleanUp() {
        driver.quit();
    }
}
