package example.LeadManagement_LM_01_10_AddNewLead;

import com.github.javafaker.Faker;
import example.TestBase;
import io.qameta.allure.Allure;
import models.CustomerInFormationForm;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import page.Customer.CreateCustomerPage;
import page.Login.LoginPage;
import page.Customer.ShowAllCustomersPage;

public class LM_01_10_VerifyNoNewCustomerCreatedAfterClickingCancelButton extends TestBase {

    LoginPage loginPage;
    ShowAllCustomersPage showAllCustomersPage;
    CreateCustomerPage createCustomerPage;
    Faker faker;
    SoftAssert softAssert;
    CustomerInFormationForm customerInfo;

    String name;
    String email;
    String phone;
    String address;

    @BeforeMethod
    public void setUp() {

        super.setUp();
        loginPage = new LoginPage(driver);
        showAllCustomersPage = new ShowAllCustomersPage(driver);
        createCustomerPage = new CreateCustomerPage(driver);
        faker = new Faker();
        softAssert = new SoftAssert();

        name = faker.name().name();
        email = faker.internet().emailAddress();
        phone = RandomStringUtils.randomNumeric(10);
        address = faker.address().fullAddress();
        customerInfo = new CustomerInFormationForm(name, email, phone, address);
    }

    @Test
    public void testLM_01_10() {

        Allure.step("Login success");
        loginPage.login("abcTrang@gmail.com", "123123");
//        showAllCustomersPage.waitForShowAllCustomersPageIsDisplayed();

        softAssert.assertTrue(showAllCustomersPage.isNewCustomerButtonDisplayed(), "[Show all customers] page is not displayed");
        Allure.step("Open [Create Customer] page");
        showAllCustomersPage.openCreateNewCustomerPage();

        softAssert.assertTrue(createCustomerPage.isCreateCustomerPageDisplayed(), "[Create customer] page is not displayed");
        Allure.step("Input valid data for all fields");
        createCustomerPage.enterCustomerInformation(customerInfo);

        Allure.step("Click [Cancel] button");
        createCustomerPage.clickCancelButton();

        softAssert.assertTrue(showAllCustomersPage.isNewCustomerButtonDisplayed());

        Allure.step("Search with name of customer just created");
        showAllCustomersPage.searchCustomer(name);

//        softAssert.assertEquals(showAllCustomersPage.getMessageWhenSearch(), "No records found.", "Success create customer when clicking [Cancel] button");

        softAssert.assertTrue(showAllCustomersPage.isNoRecordFoundIsDisplayed());

        softAssert.assertAll();
    }

}
