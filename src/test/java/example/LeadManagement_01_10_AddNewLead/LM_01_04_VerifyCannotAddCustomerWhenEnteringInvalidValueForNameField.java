package example.LeadManagement_01_10_AddNewLead;

import com.github.javafaker.Faker;
import example.TestBase;
import io.qameta.allure.Allure;
import models.CustomerInFormationForm;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import page.Customer.CreateCustomerPage;
import page.LoginPage;
import page.Customer.ShowAllCustomersPage;

public class LM_01_04_VerifyCannotAddCustomerWhenEnteringInvalidValueForNameField extends TestBase {

    LoginPage loginPage;
    ShowAllCustomersPage showAllCustomersPage;
    CreateCustomerPage createCustomerPage;
    SoftAssert softAssert;
    Faker faker;
    CustomerInFormationForm customerInfo;

    String name;
    String email;
    String phone;
    String address;

    @BeforeMethod
    public void setUp() {
        super.setUp();
        softAssert = new SoftAssert();
        loginPage = new LoginPage(driver);
        showAllCustomersPage = new ShowAllCustomersPage(driver);
        createCustomerPage = new CreateCustomerPage(driver);
        faker = new Faker();

        name = RandomStringUtils.randomAlphabetic(51);
        email = faker.internet().emailAddress();
        phone = RandomStringUtils.randomNumeric(10);
        address = faker.address().fullAddress();
        customerInfo = new CustomerInFormationForm(name, email, phone, address);

    }

    @Test
    public void testLM_01_04() {

        Allure.step("Login success");
        loginPage.login("abcTrang@gmail.com", "123123");
//        showAllCustomersPage.waitForShowAllCustomersPageIsDisplayed();

        Allure.step("Open [Create Customer] page");
        showAllCustomersPage.openCreateCustomerPage();

        Allure.step("Input more than 50 characters for [Name] field");
        Allure.step("Input valid data for [Email], [Phone], [Address] field");
        createCustomerPage.createCustomerInformation(customerInfo);

        //kiem tra thong bao tren cac truong
        softAssert.assertTrue(createCustomerPage.isCreateCustomerPageDisplayed(), "Create success");
        softAssert.assertEquals(createCustomerPage.getErrorForNameField(), "size must be between 0 and 50", "No message in name field");

        softAssert.assertAll();
    }

}
