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

public class LM_01_01_VerifySuccessfulAddACustomerWhenUserInputValidDataForAllFields extends TestBase {
    LoginPage loginPage;
    ShowAllCustomersPage showAllCustomersPage;
    CreateCustomerPage createCustomerPage;
    Faker faker;
    SoftAssert softAssert;
    CustomerInFormationForm customerInfor;

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
        customerInfor = new CustomerInFormationForm(name,email, phone, address);
    }

    @Test
    public void testLM_01_01() {

        Allure.step("Login success");
        loginPage.login("abcTrang@gmail.com", "123123");

        Allure.step("Open [Create Customer] page");
        showAllCustomersPage.openCreateNewCustomerPage();

        softAssert.assertTrue(createCustomerPage.isCreateCustomerPageDisplayed(), "[Create customer] page is not displayed");

        Allure.step("Input valid data for all fields");
        createCustomerPage.createCustomerInformation(customerInfor);

        softAssert.assertTrue(showAllCustomersPage.isNewCustomerButtonDisplayed(), "[Show all customers] page is not displayed");

        //xác minh Customer vừa được thêm vào hiển thị ở đầu danh sách customer
        softAssert.assertEquals(showAllCustomersPage.getCustomerNameByIndex(1), name, "The newly created customer is not at the top of the list.");
        softAssert.assertEquals(showAllCustomersPage.getCustomerEmailByIndex(1), email, "The newly created customer is not at the top of the list.");
        softAssert.assertEquals(showAllCustomersPage.getCustomerPhoneByIndex(1), phone, "The newly created customer is not at the top of the list.");
        softAssert.assertEquals(showAllCustomersPage.getCustomerAddressByIndex(1), address, "The newly created customer is not at the top of the list.");

        softAssert.assertAll();
    }
}
