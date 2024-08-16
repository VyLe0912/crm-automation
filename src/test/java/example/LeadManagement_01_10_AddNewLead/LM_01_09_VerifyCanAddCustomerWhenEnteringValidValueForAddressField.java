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

public class LM_01_09_VerifyCanAddCustomerWhenEnteringValidValueForAddressField extends TestBase {

    LoginPage loginPage;
    ShowAllCustomersPage showAllCustomersPage;
    CreateCustomerPage createCustomerPage;
    SoftAssert softAssert;
    Faker faker;
    CustomerInFormationForm customerInFor;

    String name;
    String email;
    String phone;
    String address1, address99, address100;

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
        address1 = RandomStringUtils.randomAlphanumeric(1);
        address99 = RandomStringUtils.randomAlphanumeric(99);
        address100 = RandomStringUtils.randomAlphanumeric(100);
        customerInFor = new CustomerInFormationForm(name, email, phone, address1);
    }

    @Test
    public void testLM_01_09() {

        Allure.step("Login success");
        loginPage.login("abcTrang@gmail.com", "123123");
//        showAllCustomersPage.waitForShowAllCustomersPageIsDisplayed();

        //1 ki tu cho truong [Address]
        Allure.step("Open [Create Customer] Page");
        showAllCustomersPage.openCreateCustomerPage();

        Allure.step("Input valid data for [Name], [Email], [Phone] field");
        Allure.step("Input 1 character for [Address] field");
        createCustomerPage.createCustomerInformation(customerInFor);

        softAssert.assertTrue(showAllCustomersPage.isNewCustomerButtonDisplayed(), "[Show all customers] page is not displayed");

        //99 ki tu cho truong [Address]
        Allure.step("Open [Create Customer] page");
        showAllCustomersPage.openCreateCustomerPage();

        Allure.step("Input valid data for [Name], [Email], [Phone] field");
        Allure.step("Input 99 character for [Address] field");
        customerInFor.setAddress(address99);
        createCustomerPage.createCustomerInformation(customerInFor);

        softAssert.assertTrue(showAllCustomersPage.isNewCustomerButtonDisplayed(), "[Show all customers] page is not displayed");

        //100 ki tu cho truong [Address]
        Allure.step("Open [Create Customer] page");
        showAllCustomersPage.openCreateCustomerPage();

        Allure.step("Input valid data for [Name], [Email], [Phone] field");
        Allure.step("Input 100 character for [Address] field");
        customerInFor.setAddress(address100);
        createCustomerPage.createCustomerInformation(customerInFor);

        softAssert.assertTrue(showAllCustomersPage.isNewCustomerButtonDisplayed(), "[Show all customers] page is not displayed");

        softAssert.assertAll();
    }
}
