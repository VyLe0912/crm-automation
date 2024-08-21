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

public class LM_01_17_VerifyCannotAddCustomerWhenEnteringInvalidValueForPhoneField extends TestBase {
    LoginPage loginPage;
    ShowAllCustomersPage showAllCustomersPage;
    CreateCustomerPage createCustomerPage;
    SoftAssert softAssert;
    Faker faker;
    SideBar sideBar;
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
        sideBar = new SideBar(driver);
        faker = new Faker();

        name = RandomStringUtils.randomAlphabetic(1, 50);
        email = faker.internet().emailAddress();
        phoneAbc = RandomStringUtils.randomAlphabetic(10);
        phoneAbc123 = RandomStringUtils.randomAlphanumeric(10);
        address = faker.address().fullAddress();
        customerInFor = new CustomerInFormationForm(name, email, phoneAbc, address);
    }

    @Test
    public void testLM_01_17() {

        Allure.step("Login success");
        loginPage.login("abcTrang@gmail.com", "123123");
//        showAllCustomersPage.waitForShowAllCustomersPageIsDisplayed();

        Allure.step("Open [Create Customer] page");
        sideBar.openCreateNewCustomerPage();

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
