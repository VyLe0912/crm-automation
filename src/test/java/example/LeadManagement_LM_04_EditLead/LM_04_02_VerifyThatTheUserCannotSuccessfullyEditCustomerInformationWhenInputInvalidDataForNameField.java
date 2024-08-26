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

public class LM_04_02_VerifyThatTheUserCannotSuccessfullyEditCustomerInformationWhenInputInvalidDataForNameField extends TestBase {
    LoginPage loginPage;
    ShowAllCustomersPage showAllCustomersPage;
    CreateCustomerPage createCustomerPage;
    Faker faker;
    SoftAssert softAssert;
    Random random;
    CustomerInformationPage customerInformationPage;
    EditCustomerInformationPage editCustomerInformationPage;

    String name51;
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
        name51 = RandomStringUtils.randomAlphabetic(51);
    }

    @Test
    public void testLM_04_02() {

        Allure.step("Login success");
        loginPage.login("abcTrang@gmail.com", "123123");

        Allure.step("Open [Customer Information] page");
        showAllCustomersPage.openCustomerInformationPage(randomCustomer);

        Allure.step("Open [Edit Customer Information] page");
        customerInformationPage.clickEditButton();

        //bo trong truong name
        Allure.step("Clear value of [Name] field");
        editCustomerInformationPage.clearName();
        editCustomerInformationPage.clickSaveButton();

        softAssert.assertTrue(editCustomerInformationPage.isEditCustomerInformationPageDisplayed());
        softAssert.assertEquals(editCustomerInformationPage.getErrorForNameField(), "Please enter your name");

        //Nhap 51 ky tu cho truong name
        Allure.step("Input 51 characters for [Name] field");
        editCustomerInformationPage.inputName(name51);
        editCustomerInformationPage.clickSaveButton();

        softAssert.assertTrue(editCustomerInformationPage.isEditCustomerInformationPageDisplayed());
        softAssert.assertEquals(editCustomerInformationPage.getErrorForNameField(), "size must be between 0 and 50");

        softAssert.assertAll();
    }
}
