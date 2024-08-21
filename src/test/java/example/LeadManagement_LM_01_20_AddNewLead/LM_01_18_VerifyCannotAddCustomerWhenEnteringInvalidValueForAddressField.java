package example.LeadManagement_LM_01_20_AddNewLead;

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
import page.SideBar.SideBar;

public class LM_01_18_VerifyCannotAddCustomerWhenEnteringInvalidValueForAddressField extends TestBase {
    LoginPage loginPage;
    ShowAllCustomersPage showAllCustomersPage;
    CreateCustomerPage createCustomerPage;
    SoftAssert softAssert;
    Faker faker;
    SideBar sideBar;
    CustomerInFormationForm customerInFor;

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
        sideBar = new SideBar(driver);
        faker = new Faker();

        name = faker.name().name();
        email = faker.internet().emailAddress();
        phone = RandomStringUtils.randomNumeric(10);
        address = RandomStringUtils.randomAlphanumeric(101);
        customerInFor = new CustomerInFormationForm(name, email, phone, address);
    }

    @Test
    public void testLM_01_18() {

        Allure.step("Login success");
        loginPage.login("abcTrang@gmail.com", "123123");
//        showAllCustomersPage.waitForShowAllCustomersPageIsDisplayed();

        Allure.step("Open [Create Customer] page");
        sideBar.openCreateNewCustomerPage();

        Allure.step("Input valid data for [Name], [Email], [Phone] field");
        Allure.step("Input 101 character for [Address] field");
        createCustomerPage.createCustomerInformation(customerInFor);

        //kiem tra thong bao tren cac truong
        softAssert.assertTrue(createCustomerPage.isCreateCustomerPageDisplayed(), "Create success");
        softAssert.assertEquals(createCustomerPage.getErrorForAddressField(), "size must be between 0 and 100", "No message in address field");

        softAssert.assertAll();
    }
}
