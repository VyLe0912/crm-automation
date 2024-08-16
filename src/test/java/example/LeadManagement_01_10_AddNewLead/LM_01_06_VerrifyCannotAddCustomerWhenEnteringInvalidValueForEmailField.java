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

public class LM_01_06_VerrifyCannotAddCustomerWhenEnteringInvalidValueForEmailField extends TestBase {

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
        super.setUp();
        softAssert = new SoftAssert();
        loginPage = new LoginPage(driver);
        showAllCustomersPage = new ShowAllCustomersPage(driver);
        createCustomerPage = new CreateCustomerPage(driver);
        faker = new Faker();

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

}
