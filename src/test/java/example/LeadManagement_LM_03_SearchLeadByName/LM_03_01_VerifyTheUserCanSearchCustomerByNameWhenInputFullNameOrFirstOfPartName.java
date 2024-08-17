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
import page.Login.LoginPage;

public class LM_03_01_VerifyTheUserCanSearchCustomerByNameWhenInputFullNameOrFirstOfPartName extends TestBase {
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
    String firstPartOfName;
    String randomName;
    int lengthOfName;

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
        lengthOfName = name.length();
        firstPartOfName = name.substring(0, lengthOfName - 1);
        randomName = name + "123";
    }

    @Test
    public void testLM_03_01() {

        Allure.step("Login success");
        loginPage.login("abcTrang@gmail.com", "123123");

        Allure.step("Open [Create Customer] page");
        showAllCustomersPage.openCreateCustomerPage();

        Allure.step("Create a customer");
        createCustomerPage.createCustomerInformation(customerInfor);

        //Search toan bo ten
        Allure.step("Search with full name");
        showAllCustomersPage.searchCustomer(name);
        softAssert.assertTrue(showAllCustomersPage.allNamesAre(name));

        //Search ten k hop le
        Allure.step("Search with invalid name");
        showAllCustomersPage.searchCustomer(randomName);
        softAssert.assertTrue(showAllCustomersPage.isNoRecordFoundIsDisplayed());

        //Search 1 phan cua ten
        showAllCustomersPage.searchCustomer(firstPartOfName);//search vs tên bỏ 1 kí tu cuoi
        softAssert.assertTrue(showAllCustomersPage.allNamesAre(name));

        softAssert.assertAll();
    }
}
