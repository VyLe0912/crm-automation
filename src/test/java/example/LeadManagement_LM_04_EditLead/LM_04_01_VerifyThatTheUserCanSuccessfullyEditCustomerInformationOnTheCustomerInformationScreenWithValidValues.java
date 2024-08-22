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
    String customerNameInShowAllCustomerPageBefore, customerEmailInShowAllCustomerPageBefore, customerPhoneInShowAllCustomerPageBefore, customerAddressInShowAllCustomerPageBefore;
    String customerNameInCustomerInformationPageBefore, customerEmailInCustomerInformationPageBefore, customerPhoneInCustomerInformationPageBefore, customerAddressInCustomerInformationPageBefore;

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

        //lay thong tin cua customer bat ki o man hinh [Show All Customers]
        customerNameInShowAllCustomerPageBefore = showAllCustomersPage.getCustomerNameByIndex(randomCustomer);
        customerEmailInShowAllCustomerPageBefore = showAllCustomersPage.getCustomerEmailByIndex(randomCustomer);
        customerPhoneInShowAllCustomerPageBefore = showAllCustomersPage.getCustomerPhoneByIndex(randomCustomer);
        customerAddressInShowAllCustomerPageBefore = showAllCustomersPage.getCustomerAddressByIndex(randomCustomer);

        Allure.step("Open [Customer Information] page");
        showAllCustomersPage.openCustomerInformationPage(randomCustomer);


        //lay thong tin cua customer o man hinh [Customer Information]
        customerNameInCustomerInformationPageBefore = customerInformationPage.getCustomerName();
        customerEmailInCustomerInformationPageBefore = customerInformationPage.getCustomerEmail();
        customerPhoneInCustomerInformationPageBefore = customerInformationPage.getCustomerPhone();
        customerAddressInCustomerInformationPageBefore = customerInformationPage.getCustomerAddress();

        //so sanh thong tin customer o man hinh [Customer Information] va man hinh [Show All Customers]
        softAssert.assertEquals(customerNameInCustomerInformationPageBefore, customerNameInShowAllCustomerPageBefore);
        softAssert.assertEquals(customerEmailInCustomerInformationPageBefore, customerEmailInShowAllCustomerPageBefore);
        softAssert.assertEquals(customerPhoneInCustomerInformationPageBefore, customerPhoneInShowAllCustomerPageBefore);
        softAssert.assertEquals(customerAddressInCustomerInformationPageBefore, customerAddressInShowAllCustomerPageBefore);


        Allure.step("Open [Edit Customer Information] page");
        customerInformationPage.clickEditButton();

        //so sanh thong tin customer o man hinh [Edit customer information] va man hinh [Customer information]
        softAssert.assertEquals(editCustomerInformationPage.getCustomerName(), customerNameInCustomerInformationPageBefore);
        softAssert.assertEquals(editCustomerInformationPage.getCustomerEmail(), customerEmailInCustomerInformationPageBefore);
        softAssert.assertEquals(editCustomerInformationPage.getCustomerPhone(), customerPhoneInCustomerInformationPageBefore);
        softAssert.assertEquals(editCustomerInformationPage.getCustomerAddress(), customerAddressInCustomerInformationPageBefore);

        Allure.step("Edit name, email, phone, address");
        editCustomerInformationPage.editCustomerInformation(customerInfor);
        softAssert.assertTrue(customerInformationPage.isCustomerInformationPageDisplayed());

        //xac minh thong tin sau khi chinh sua o man hinh
        softAssert.assertEquals(customerInformationPage.getCustomerName(), name);
        softAssert.assertEquals(customerInformationPage.getCustomerEmail(), email);
        softAssert.assertEquals(customerInformationPage.getCustomerPhone(), phone);
        softAssert.assertEquals(customerInformationPage.getCustomerAddress(), address);

        Allure.step("Open [Show All Customer] page");
        customerInformationPage.openShowAllCustomersPage();

        //so sanh thong tin customer o man hinh [Show All Customers] sau chinh sua va thong tin chinh sua
        softAssert.assertEquals(showAllCustomersPage.getCustomerNameByIndex(randomCustomer), name);
        softAssert.assertEquals(showAllCustomersPage.getCustomerEmailByIndex(randomCustomer), email);
        softAssert.assertEquals(showAllCustomersPage.getCustomerPhoneByIndex(randomCustomer), phone);
        softAssert.assertEquals(showAllCustomersPage.getCustomerAddressByIndex(randomCustomer), address);

        Allure.step("Search with name after edited");
        showAllCustomersPage.searchCustomer(name);
        softAssert.assertTrue(showAllCustomersPage.allNamesAre(name));

        Allure.step("Search with name before edited");
        showAllCustomersPage.searchCustomer(customerNameInCustomerInformationPageBefore);
        softAssert.assertTrue(showAllCustomersPage.isNoRecordFoundIsDisplayed());

        //NHI

        softAssert.assertAll();
    }
}
