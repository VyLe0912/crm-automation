package example.LeadManagement_01_20_AddNewLead;

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
import page.SideBar;

public class LM_01_15_VerifyCanAddCustomerWhenEnteringValidValueForNameField extends TestBase {
    LoginPage loginPage;
    ShowAllCustomersPage showAllCustomersPage;
    CreateCustomerPage createCustomerPage;
    SoftAssert softAssert;
    Faker faker;
    SideBar sideBar;
    CustomerInFormationForm customerInFor;

    String name1, name49, name50;
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

        name1 = RandomStringUtils.randomAlphabetic(1);
        name49 = RandomStringUtils.randomAlphabetic(49);
        name50 = RandomStringUtils.randomAlphabetic(50);
        email = faker.internet().emailAddress();
        phone = RandomStringUtils.randomNumeric(10);
        address = faker.address().fullAddress();

        customerInFor = new CustomerInFormationForm(name1, email, phone, address);
    }

    @Test
    public void testLM_01_15() {

        Allure.step("Login success");
        loginPage.login("abcTrang@gmail.com", "123123");
//        showAllCustomersPage.waitForShowAllCustomersPageIsDisplayed();

        //1 ki tu cho truong [name]
        Allure.step("Open [Create Customer] page");
        sideBar.openCreateCustomerPage();

        Allure.step("Input 1 character for [Name] field");
        Allure.step("Input valid data for [Email], [Phone], [Address] field");
        createCustomerPage.createCustomerInformation(customerInFor);

        softAssert.assertTrue(showAllCustomersPage.isNewCustomerButtonDisplayed(), "[Show all customers] page is not displayed");

        //49 ki tu cho truong [name]
        Allure.step("Open [Create Customer] page");
        sideBar.openCreateCustomerPage();

        Allure.step("Input 49 character for [Name] field");
        Allure.step("Input valid data for [Email], [Phone], [Address] field");
        customerInFor.setName(name49);
        createCustomerPage.createCustomerInformation(customerInFor);

        softAssert.assertTrue(showAllCustomersPage.isNewCustomerButtonDisplayed(), "[Show all customers] page is not displayed");

        //50 ki tu cho truong [name]
        Allure.step("Open [Create Customer] page");
        sideBar.openCreateCustomerPage();

        Allure.step("Input 50 character for [Name] field");
        Allure.step("Input valid data for [Email], [Phone], [Address] field");
        customerInFor.setName(name50);
        createCustomerPage.createCustomerInformation(customerInFor);

        softAssert.assertTrue(showAllCustomersPage.isNewCustomerButtonDisplayed(), "[Show all customers] page is not displayed");

        softAssert.assertAll();
    }

}
