package example.LeadManagement_LM_03_SearchLeadByName;

import com.github.javafaker.Faker;
import example.TestBase;
import io.qameta.allure.Allure;
import models.CustomerInFormationForm;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import page.Customer.CreateCustomerPage;
import page.Customer.ShowAllCustomersPage;
import page.LoginPage;

import java.util.Random;

public class LM_03_01_VerifyTheUserCanSearchCustomerByNameWhenInputFullNameOrFirstOfPartName extends TestBase {
    LoginPage loginPage;
    ShowAllCustomersPage showAllCustomersPage;
    CreateCustomerPage createCustomerPage;
    Faker faker;
    SoftAssert softAssert;
    CustomerInFormationForm customerInfor;
    Random random;

    String name;
    String email;
    String phone;
    String address;
    String firstPartOfName;
    String randomName;

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
        firstPartOfName = showAllCustomersPage.firstPartOfNameSearch(name);
        randomName = name + "123";
    }

    @Test
    public void testLM_01_01() {

        Allure.step("Login success");
        loginPage.login("abcTrang@gmail.com", "123123");

        Allure.step("Open [Create Customer] page");
        showAllCustomersPage.openCreateCustomerPage();

        Allure.step("Create a customer");
        createCustomerPage.createCustomerInformation(customerInfor);

        //Search toan bo ten
        showAllCustomersPage.searchCustomer(name);
        softAssert.assertEquals(showAllCustomersPage.getCustomerNameByIndex(1), name);

        //search ten k hop le
        showAllCustomersPage.searchCustomer(randomName);
        softAssert.assertTrue(showAllCustomersPage.isNoRecordFoundIsDisplayed());

        //Search 1 phan cua ten
        showAllCustomersPage.searchCustomer(firstPartOfName);
        softAssert.assertEquals(showAllCustomersPage.firstPartOfNameResult(showAllCustomersPage.getCustomerNameByIndex(1)), firstPartOfName);

        softAssert.assertAll();
    }
}
