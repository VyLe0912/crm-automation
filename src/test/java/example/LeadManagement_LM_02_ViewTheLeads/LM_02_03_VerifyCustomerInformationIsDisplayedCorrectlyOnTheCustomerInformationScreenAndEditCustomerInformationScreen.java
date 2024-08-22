package example.LeadManagement_LM_02_ViewTheLeads;

import example.TestBase;
import io.qameta.allure.Allure;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import page.CustomerInformation.CustomerInformationPage;
import page.CustomerInformation.EditCustomerInformationPage;
import page.Login.LoginPage;
import page.Customer.ShowAllCustomersPage;

import java.util.Random;

public class LM_02_03_VerifyCustomerInformationIsDisplayedCorrectlyOnTheCustomerInformationScreenAndEditCustomerInformationScreen extends TestBase {
    ShowAllCustomersPage showAllCustomersPage;
    SoftAssert softAssert;
    LoginPage loginPage;
    Random random;
    CustomerInformationPage customerInformationPage;
    EditCustomerInformationPage editCustomerInformationPage;

    String name;
    String email;
    String phone;
    String address;
    int randomNumber;

    @BeforeMethod
    public void setUp() {
        super.setUp();
        softAssert = new SoftAssert();
        showAllCustomersPage = new ShowAllCustomersPage(driver);
        loginPage = new LoginPage(driver);
        random = new Random();
        customerInformationPage = new CustomerInformationPage(driver);
        editCustomerInformationPage = new EditCustomerInformationPage(driver);
        randomNumber = random.nextInt(10) + 1;
    }

    @Test
    public void testLM_02_03() {

        Allure.step("Login success");
        loginPage.login("abcTrang@gmail.com", "123123");
        showAllCustomersPage.waitForNewCustomerButtonIsDisplayed();

        // bam chon 1 customer bat ki
        name = showAllCustomersPage.getCustomerNameByIndex(randomNumber);
        email = showAllCustomersPage.getCustomerEmailByIndex(randomNumber);
        phone = showAllCustomersPage.getCustomerPhoneByIndex(randomNumber);
        address = showAllCustomersPage.getCustomerAddressByIndex(randomNumber);
        showAllCustomersPage.openCustomerInformationPage(randomNumber);

        // xac minh chuyen den man hinh customer information
        softAssert.assertTrue(customerInformationPage.isCustomerInformationPageDisplayed());

        // xac minh thong tin customer o man hinh customer information
        softAssert.assertEquals(customerInformationPage.getCustomerName(), name, "Name is incorrect");
        softAssert.assertEquals(customerInformationPage.getCustomerEmail(), email, "Email is incorrect");
        softAssert.assertEquals(customerInformationPage.getCustomerPhone(), phone, "Phone is incorrect");
        softAssert.assertEquals(customerInformationPage.getCustomerAddress(), address, "Address is incorrect");

        // bam chon edit
        customerInformationPage.clickEditButton();

        // xac minh chuyen den man hinh edit customer information
        softAssert.assertTrue(editCustomerInformationPage.isEditCustomerInformationPageDisplayed());

        // xac minh thong tin customer o man hinh edit customer information
        softAssert.assertEquals(editCustomerInformationPage.getCustomerName(), name, "Name is incorrect");
        softAssert.assertEquals(editCustomerInformationPage.getCustomerEmail(), email, "Email is incorrect");
        softAssert.assertEquals(editCustomerInformationPage.getCustomerPhone(), phone, "Phone is incorrect");
        softAssert.assertEquals(editCustomerInformationPage.getCustomerAddress(), address, "Address is incorrect");

        softAssert.assertAll();
    }
}
