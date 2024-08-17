package example.LeadManagement_LM_04_EditLead;

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
import page.CustomerInformation.CustomerInformationPage;
import page.CustomerInformation.EditCustomerInformationPage;
import page.Login.LoginPage;

import java.util.Random;

public class LM_04_01_VerifyThatTheUserCanSuccessfullyEditCustomerInformationOnTheCustomerInformationScreenWithValidValues extends TestBase {
    LoginPage loginPage;
    ShowAllCustomersPage showAllCustomersPage;
    CreateCustomerPage createCustomerPage;
    Faker faker;
    SoftAssert softAssert;
    CustomerInFormationForm customerInfor;
    Random random;
    CustomerInformationPage customerInformationPage;
    EditCustomerInformationPage editCustomerInformationPage;

    String name;
    String email;
    String phone;
    String address;
    int randomCustomer;
    String customerNameInShowAllCustomerPage;
    String customerNameInCustomerInformationPage;

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
        name = faker.name().name();
        email = faker.internet().emailAddress("Trang");
        phone = RandomStringUtils.randomNumeric(10);
        address = faker.address().fullAddress();
        customerInfor = new CustomerInFormationForm(name,email, phone, address);
    }

    @Test
    public void testLM_04_01() {

        Allure.step("Login success");
        loginPage.login("abcTrang@gmail.com", "123123");

        Allure.step("Open [Customer Information] page");
        customerNameInShowAllCustomerPage = showAllCustomersPage.getCustomerNameByIndex(randomCustomer);
        showAllCustomersPage.openCustomerInformationPage(randomCustomer);
//        showAllCustomersPage.openCustomerInformationPage(1);
        customerNameInCustomerInformationPage = customerInformationPage.getCustomerName();
        softAssert.assertEquals(customerNameInCustomerInformationPage, customerNameInShowAllCustomerPage);

        Allure.step("Open [Edit Customer Information] page");
        customerInformationPage.clickEditButton();

        Allure.step("Edit name, email, phone, address");
        editCustomerInformationPage.editCustomerInformation(customerInfor);
        softAssert.assertTrue(customerInformationPage.isCustomerInformationPageDisplayed());
        softAssert.assertEquals(customerInformationPage.getCustomerName(), name);
        softAssert.assertEquals(customerInformationPage.getCustomerEmail(), email);
        softAssert.assertEquals(customerInformationPage.getCustomerPhone(), phone);
        softAssert.assertEquals(customerInformationPage.getCustomerAddress(), address);

        Allure.step("Open [Show All Customer] page");
        customerInformationPage.openShowAllCustomersPage();

        Allure.step("Search with name after edited");
        showAllCustomersPage.searchCustomer(name);
        softAssert.assertTrue(showAllCustomersPage.allNamesAre(name));

        Allure.step("Search with name before edited");
        showAllCustomersPage.searchCustomer(customerNameInCustomerInformationPage);
        softAssert.assertTrue(showAllCustomersPage.isNoRecordFoundIsDisplayed());

        softAssert.assertAll();
    }
}
