package example.LeadManagement_AddNewLead_01_10;

import com.github.javafaker.Faker;
import example.TestBase;
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

public class LM_01_07_VerifyCannotAddCustomerWhenEnteringInvalidValueForPhoneField extends TestBase {

    LoginPage loginPage;
    ShowAllCustomersPage showAllCustomersPage;
    CreateCustomerPage createCustomerPage;
    SoftAssert softAssert;
    Faker faker;
    CustomerInFormationForm customerInFor;

    String name;
    String email;
    String phoneAbc, phoneAbc123;
    String address;

    @BeforeMethod
    public void setUp() {
        super.setUp();
        softAssert = new SoftAssert();
        loginPage = new LoginPage(driver);
        showAllCustomersPage = new ShowAllCustomersPage(driver);
        createCustomerPage = new CreateCustomerPage(driver);
        faker = new Faker();

        name = RandomStringUtils.randomAlphabetic(1, 50);
        email = faker.internet().emailAddress();
        phoneAbc = RandomStringUtils.randomAlphabetic(10);
        phoneAbc123 = RandomStringUtils.randomAlphanumeric(10);
        address = faker.address().fullAddress();
        customerInFor = new CustomerInFormationForm(name, email, phoneAbc, address);
    }

    @Test
    public void testLM_01_07() {

        Allure.step("Login success");
        loginPage.login("abcTrang@gmail.com", "123123");
//        showAllCustomersPage.waitForShowAllCustomersPageIsDisplayed();

        Allure.step("Open [Create Customer] page");
        showAllCustomersPage.openCreateCustomerPage();

        // nhap ki tu cho truong [Phone]
        Allure.step("Input character for [Phone] field");
        Allure.step("Input valid data for [Email], [Phone], [Address] field");
        createCustomerPage.createCustomerInformation(customerInFor);

        //kiem tra thong bao tren cac truong
        softAssert.assertTrue(createCustomerPage.isCreateCustomerPageDisplayed(), "Create success");
        softAssert.assertEquals(createCustomerPage.getErrorForPhoneField(), "Only numbers 0-9", "No message in name field");

        // nhap ki tu va so cho truong [Phone]
        Allure.step("Input character for [Phone] field");
        Allure.step("Input valid data for [Email], [Phone], [Address] field");
        customerInFor.setPhone(phoneAbc123);
        createCustomerPage.createCustomerInformation(customerInFor);

        //kiem tra thong bao tren cac truong
        softAssert.assertTrue(createCustomerPage.isCreateCustomerPageDisplayed(), "Create success");
        softAssert.assertEquals(createCustomerPage.getErrorForPhoneField(), "Only numbers 0-9", "No message in phone field");

        softAssert.assertAll();
    }
}
