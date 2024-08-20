package example.LeadManagement_LM_04_EditLead;

import com.github.javafaker.Faker;
import example.TestBase;
import io.qameta.allure.Allure;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import page.Customer.CreateCustomerPage;
import page.Customer.ShowAllCustomersPage;
import page.CustomerInformation.CustomerInformationPage;
import page.CustomerInformation.EditCustomerInformationPage;
import page.Login.LoginPage;

import java.util.Random;

public class LM_04_05_VerifyThatTheUserCannotSuccessfullyEditCustomerInformationWhenInputInvalidDataForAddressField extends TestBase {
    LoginPage loginPage;
    ShowAllCustomersPage showAllCustomersPage;
    CreateCustomerPage createCustomerPage;
    Faker faker;
    SoftAssert softAssert;
    Random random;
    CustomerInformationPage customerInformationPage;
    EditCustomerInformationPage editCustomerInformationPage;

    String address;
    int randomCustomer;

    @BeforeMethod
    public void setUp() {
        super.setUp();
        loginPage = new LoginPage(driver);
        showAllCustomersPage = new ShowAllCustomersPage(driver);
        createCustomerPage = new CreateCustomerPage(driver);
        faker = new Faker();
        softAssert = new SoftAssert();
        customerInformationPage = new CustomerInformationPage(driver);
        editCustomerInformationPage = new EditCustomerInformationPage(driver);
        random = new Random();

        randomCustomer = random.nextInt(10) + 1;
        address = RandomStringUtils.randomAlphabetic(101);
    }

    @Test
    public void testLM_04_05() {

        Allure.step("Login success");
        loginPage.login("abcTrang@gmail.com", "123123");

        Allure.step("Open [Customer Information] page");
        showAllCustomersPage.openCustomerInformationPage(randomCustomer);

        Allure.step("Open [Edit Customer Information] page");
        customerInformationPage.clickEditButton();

        //bo trong truong address
        Allure.step("Clear value of [Address] field");
        editCustomerInformationPage.clearAddress();
        editCustomerInformationPage.clickSaveButton();

        softAssert.assertTrue(editCustomerInformationPage.isEditCustomerInformationPageDisplayed());
        softAssert.assertEquals(editCustomerInformationPage.getErrorForAddressField(), "Please enter your address");

        //Nhap 101 ky tu cho truong address
        Allure.step("Input 101 characters for [Address] field");
        editCustomerInformationPage.inputAddress(address);
        editCustomerInformationPage.clickSaveButton();

        softAssert.assertTrue(editCustomerInformationPage.isEditCustomerInformationPageDisplayed());
        softAssert.assertEquals(editCustomerInformationPage.getErrorForAddressField(), "size must be between 0 and 100");

        softAssert.assertAll();
    }
}
