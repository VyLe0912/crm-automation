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

public class LM_04_06_VerifyThatTheUserCannotSuccessfullyEditCustomerInformationWhenClickCancelButton extends TestBase {
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
    String customerNameInShowAllCustomerPage, customerEmailInShowAllCustomerPage, customerPhoneInShowAllCustomerPage, customerAddressInShowAllCustomerPage;
    String customerNameInCustomerInformationPageBefore, customerEmailInCustomerInformationPageBefore, customerPhoneInCustomerInformationPageBefore, customerAddressInCustomerInformationPageBefore;
    String customerNameInCustomerInformationPageAfter, customerEmailInCustomerInformationPageAfter, customerPhoneInCustomerInformationPageAfter, customerAddressInCustomerInformationPageAfter;

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
    public void testLM_04_06() {

        Allure.step("Login success");
        loginPage.login("abcTrang@gmail.com", "123123");

        Allure.step("Open [Customer Information] page");
        showAllCustomersPage.openCustomerInformationPage(randomCustomer);

        //lay thong tin cua customer truoc khi chinh sua o man hinh [Customer Information]
        customerNameInCustomerInformationPageBefore = customerInformationPage.getCustomerName();
        customerEmailInCustomerInformationPageBefore = customerInformationPage.getCustomerEmail();
        customerPhoneInCustomerInformationPageBefore = customerInformationPage.getCustomerPhone();
        customerAddressInCustomerInformationPageBefore = customerInformationPage.getCustomerAddress();

        Allure.step("Open [Edit Customer Information] page");
        customerInformationPage.clickEditButton();

        Allure.step("Edit name, email, phone, address");
        editCustomerInformationPage.enterCustomerInformation(customerInfor);
        editCustomerInformationPage.clickCancelButton();

        softAssert.assertTrue(customerInformationPage.isCustomerInformationPageDisplayed());

        //lay thong tin cua customer sau khi chinh sua o man hinh [Customer Information]
        customerNameInCustomerInformationPageAfter = customerInformationPage.getCustomerName();
        customerEmailInCustomerInformationPageAfter = customerInformationPage.getCustomerEmail();
        customerPhoneInCustomerInformationPageAfter =  customerInformationPage.getCustomerPhone();
        customerAddressInCustomerInformationPageAfter =  customerInformationPage.getCustomerAddress();

        //so sanh thong tin cua customer trc va sau khi chinh sua
        softAssert.assertEquals(customerNameInCustomerInformationPageAfter, customerNameInCustomerInformationPageBefore);
        softAssert.assertEquals(customerEmailInCustomerInformationPageAfter, customerEmailInCustomerInformationPageBefore);
        softAssert.assertEquals(customerPhoneInCustomerInformationPageAfter, customerPhoneInCustomerInformationPageBefore);
        softAssert.assertEquals(customerAddressInCustomerInformationPageAfter, customerAddressInCustomerInformationPageBefore);

        Allure.step("Open [Show All Customer] page");
        customerInformationPage.openShowAllCustomersPage();

        Allure.step("Check customer name");

        //lay thong tin cua customer o man hinh [Show All Customers]
        customerNameInShowAllCustomerPage = showAllCustomersPage.getCustomerNameByIndex(randomCustomer);
        customerEmailInShowAllCustomerPage = showAllCustomersPage.getCustomerEmailByIndex(randomCustomer);
        customerPhoneInShowAllCustomerPage = showAllCustomersPage.getCustomerPhoneByIndex(randomCustomer);
        customerAddressInShowAllCustomerPage = showAllCustomersPage.getCustomerAddressByIndex(randomCustomer);

        //kiem tra thong tin cua customer
        softAssert.assertEquals(customerNameInShowAllCustomerPage, customerNameInCustomerInformationPageBefore);
        softAssert.assertEquals(customerEmailInShowAllCustomerPage, customerEmailInCustomerInformationPageBefore);
        softAssert.assertEquals(customerPhoneInShowAllCustomerPage, customerPhoneInCustomerInformationPageBefore);
        softAssert.assertEquals(customerAddressInShowAllCustomerPage, customerAddressInCustomerInformationPageBefore);

        softAssert.assertAll();
    }
}
