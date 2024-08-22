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

public class LM_04_03_VerifyThatTheUserCannotSuccessfullyEditCustomerInformationWhenInputInvalidDataForEmailField extends TestBase {
    LoginPage loginPage;
    ShowAllCustomersPage showAllCustomersPage;
    CreateCustomerPage createCustomerPage;
    Faker faker;
    SoftAssert softAssert;
    Random random;
    CustomerInformationPage customerInformationPage;
    EditCustomerInformationPage editCustomerInformationPage;

    String email1, email2, email3;
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
        email1 = "username";
        email2 = "username.domain";
        email3 = "username@domain";
    }

    @Test
    public void testLM_04_03() {

        Allure.step("Login success");
        loginPage.login("abcTrang@gmail.com", "123123");

        Allure.step("Open [Customer Information] page");
        showAllCustomersPage.openCustomerInformationPage(randomCustomer);

        Allure.step("Open [Edit Customer Information] page");
        customerInformationPage.clickEditButton();

        //Bo trong truong email
        Allure.step("Clear value of [Email] field");
        editCustomerInformationPage.clearEmail();
        editCustomerInformationPage.clickSaveButton();

        softAssert.assertTrue(editCustomerInformationPage.isEditCustomerInformationPageDisplayed());
        softAssert.assertEquals(editCustomerInformationPage.getErrorForEmailField(), "Please enter your email");

        //Nhap "username" cho truong email
        Allure.step("Input 'username' for [Email] field");
        editCustomerInformationPage.inputEmail(email1);
        editCustomerInformationPage.clickSaveButton();

        softAssert.assertTrue(editCustomerInformationPage.isEditCustomerInformationPageDisplayed());
        softAssert.assertEquals(editCustomerInformationPage.getErrorForEmailField(), "The email is not valid (ex: abc@abc)");

        //Nhap "username.domain" cho truong name
        Allure.step("Input 'username.domain' for [Email] field");
        editCustomerInformationPage.inputEmail(email2);
        editCustomerInformationPage.clickSaveButton();

        softAssert.assertTrue(editCustomerInformationPage.isEditCustomerInformationPageDisplayed());
        softAssert.assertEquals(editCustomerInformationPage.getErrorForEmailField(), "The email is not valid (ex: abc@abc)");

        //Nhap "username@domain" cho truong name
        Allure.step("Input 'username@domain' for [Email] field");
        editCustomerInformationPage.inputEmail(email3);
        editCustomerInformationPage.clickSaveButton();

        softAssert.assertTrue(editCustomerInformationPage.isEditCustomerInformationPageDisplayed());
        softAssert.assertEquals(editCustomerInformationPage.getErrorForEmailField(), "The email is not valid (ex: abc@abc)");

        softAssert.assertAll();
    }
}
