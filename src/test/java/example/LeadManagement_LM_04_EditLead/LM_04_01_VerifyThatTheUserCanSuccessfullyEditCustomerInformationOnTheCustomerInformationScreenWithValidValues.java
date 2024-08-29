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
    CustomerInFormationForm newCustomerInfor;
    CustomerInFormationForm customerInFor;
    Random random;
    CustomerInformationPage customerInformationPage;
    EditCustomerInformationPage editCustomerInformationPage;

    String newCustomerName;
    String newCustomerEmail;
    String newCustomerPhone;
    String newCustomerAddress;
    int randomCustomer;
    String customerName, customerEmail, customerPhone, customerAddress;

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
        newCustomerName = faker.name().name();
        newCustomerEmail = faker.internet().emailAddress("Trang");
        newCustomerPhone = RandomStringUtils.randomNumeric(10);
        newCustomerAddress = faker.address().fullAddress();
        newCustomerInfor = new CustomerInFormationForm(newCustomerName, newCustomerEmail, newCustomerPhone, newCustomerAddress);
    }

    @Test
    public void testLM_04_01() {

        Allure.step("Login success");
        loginPage.login("abcTrang@gmail.com", "123123");

        //lay thong tin cua customer bat ki o man hinh [Show All Customers]
        customerInFor = showAllCustomersPage.getCustomerByIndex(randomCustomer);

        Allure.step("Open [Customer Information] page");
        showAllCustomersPage.openCustomerInformationPage(randomCustomer);
        customerInformationPage.waitForCustomerNameIsDisplayed();

        //so sanh thong tin customer o man hinh [Customer Information] va man hinh [Show All Customers]
        softAssert.assertEquals(customerInformationPage.getCustomer(), customerInFor);

        Allure.step("Open [Edit Customer Information] page");
        customerInformationPage.clickEditButton();

        //so sanh thong tin o man hinh [Edit customer information] va man hinh [Show All Customers]
        softAssert.assertEquals(editCustomerInformationPage.customerInFor(), customerInFor);

        Allure.step("Edit name, email, phone, address");
        editCustomerInformationPage.editCustomerInformation(newCustomerInfor);
        softAssert.assertTrue(customerInformationPage.isCustomerInformationPageDisplayed());

        //xac minh thong tin sau khi chinh sua duoc cap nhat o man hinh [Customer Information]

        softAssert.assertEquals(customerInformationPage.getCustomer(), newCustomerInfor);

        Allure.step("Open [Show All Customer] page");
        customerInformationPage.openShowAllCustomersPage();

        //xac minh thong tin sau khi chinh sua duoc cap nhat o man hinh [Show All Customers]
        softAssert.assertEquals(showAllCustomersPage.getCustomerByIndex(randomCustomer), newCustomerInfor);

        Allure.step("Search with name after edited");
        showAllCustomersPage.searchCustomer(newCustomerName);
        softAssert.assertTrue(showAllCustomersPage.allNamesAre(newCustomerName));

        softAssert.assertAll();
    }
}
