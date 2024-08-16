package example.LeadManagement_01_10_AddNewLead;

import com.github.javafaker.Faker;
import example.TestBase;
import io.qameta.allure.Allure;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import page.Customer.CreateCustomerPage;
import page.LoginPage;
import page.Customer.ShowAllCustomersPage;

public class LM_01_03_VerifyCannotAddCustomerWhen1Of4FieldsIsLeftBlank extends TestBase {

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
        super.setUp();
        softAssert = new SoftAssert();
        loginPage = new LoginPage(driver);
        showAllCustomersPage = new ShowAllCustomersPage(driver);
        createCustomerPage = new CreateCustomerPage(driver);
        faker = new Faker();

        name = faker.name().name();
        email = faker.internet().emailAddress();
        phone = RandomStringUtils.randomNumeric(10);
        address = faker.address().fullAddress();
    }

    @Test
    public void testLM_01_03() {

        Allure.step("Login success");
        loginPage.login("abcTrang@gmail.com", "123123");
//        showAllCustomersPage.waitForShowAllCustomersPageIsDisplayed();

        Allure.step("Open [Create Customer] page");
        showAllCustomersPage.openCreateCustomerPage();

        // 1. Bo trong truong [Name]
        Allure.step("Leave [Name] field blank");
        createCustomerPage.inputEmail(email);
        createCustomerPage.inputPhone(phone);
        createCustomerPage.inputAddress(address);

        Allure.step("Click [Create a customer] button");
        createCustomerPage.clickCreateACustomerButton();

        //kiem tra thong bao tren cac truong
        softAssert.assertTrue(createCustomerPage.isCreateCustomerPageDisplayed(), "Create success");
        softAssert.assertEquals(createCustomerPage.getErrorForNameField(), "Please enter your name", "No message in name field");


        // 2. Bo trong truong [Email]
        Allure.step("Leave [Email] field blank");
        createCustomerPage.inputName(name);
        createCustomerPage.clearEmail();

        Allure.step("Click [Create a customer] button");
        createCustomerPage.clickCreateACustomerButton();

        //kiem tra thong bao tren cac truong
        softAssert.assertTrue(createCustomerPage.isCreateCustomerPageDisplayed(), "Create success");
        softAssert.assertEquals(createCustomerPage.getErrorForEmailField(), "Please enter your email", "No message in email field");


        // 3. Bo trong truong [Phone]
        Allure.step("Leave [Phone] field blank");
        createCustomerPage.inputEmail(email);
        createCustomerPage.clearPhone();

        Allure.step("Click [Create a customer] button");
        createCustomerPage.clickCreateACustomerButton();

        //kiem tra thong bao tren cac truong
        softAssert.assertTrue(createCustomerPage.isCreateCustomerPageDisplayed(), "Create success");
        softAssert.assertEquals(createCustomerPage.getErrorForPhoneField(), "Please enter your phone", "No message in phone field");


        // 4. Bo trong truong [Address]
        Allure.step("Leave [Email] field blank");
        createCustomerPage.inputPhone(phone);
        createCustomerPage.clearAddress();

        Allure.step("Click [Create a customer] button");
        createCustomerPage.clickCreateACustomerButton();

        //kiem tra thong bao tren cac truong
        softAssert.assertTrue(createCustomerPage.isCreateCustomerPageDisplayed(), "Create success");
        softAssert.assertEquals(createCustomerPage.getErrorForAddressField(), "Please enter your address", "No message in address field");

        softAssert.assertAll();
    }
}
