package example.LeadManagement_LM_04_EditLead;

import com.github.javafaker.Faker;
import example.TestBase;
import io.qameta.allure.Allure;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import page.Customer.CreateCustomerPage;
import page.Customer.ShowAllCustomersPage;
import page.CustomerInformation.CustomerInformationPage;
import page.CustomerInformation.EditCustomerInformationPage;
import page.Login.LoginPage;

import java.util.Random;

public class LM_04_04_VerifyThatTheUserCannotSuccessfullyEditCustomerInformationWhenInputInvalidDataForPhoneField extends TestBase {
    LoginPage loginPage;
    ShowAllCustomersPage showAllCustomersPage;
    CreateCustomerPage createCustomerPage;
    Faker faker;
    SoftAssert softAssert;
    Random random;
    CustomerInformationPage customerInformationPage;
    EditCustomerInformationPage editCustomerInformationPage;

    String phone;
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
        phone = "abc";
    }

    @Test
    public void testLM_04_04() {

        Allure.step("Login success");
        loginPage.login("abcTrang@gmail.com", "123123");

        Allure.step("Open [Customer Information] page");
        showAllCustomersPage.openCustomerInformationPage(randomCustomer);

        Allure.step("Open [Edit Customer Information] page");
        customerInformationPage.clickEditButton();

        //Bo trong truong phone
        Allure.step("Clear value of [Phone] field");
        editCustomerInformationPage.clearPhone();
        editCustomerInformationPage.clickSaveButton();

        softAssert.assertTrue(editCustomerInformationPage.isEditCustomerInformationPageDisplayed());
        softAssert.assertEquals(editCustomerInformationPage.getErrorForPhoneField(), "Please enter your phone");

        //Nhap "abc" cho truong phone
        Allure.step("Input 'abc' for [Phone] field");
        editCustomerInformationPage.inputPhone(phone);
        editCustomerInformationPage.clickSaveButton();

        softAssert.assertTrue(editCustomerInformationPage.isEditCustomerInformationPageDisplayed());
        softAssert.assertEquals(editCustomerInformationPage.getErrorForPhoneField(), "Only numbers 0-9");

        softAssert.assertAll();
    }
}
