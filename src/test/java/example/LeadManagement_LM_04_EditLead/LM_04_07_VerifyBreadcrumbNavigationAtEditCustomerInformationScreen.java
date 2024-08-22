package example.LeadManagement_LM_04_EditLead;
import example.TestBase;
import io.qameta.allure.Allure;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import page.Customer.ShowAllCustomersPage;
import page.CustomerInformation.CustomerInformationPage;
import page.CustomerInformation.EditCustomerInformationPage;
import page.Login.LoginPage;

import java.util.Random;

public class LM_04_07_VerifyBreadcrumbNavigationAtEditCustomerInformationScreen extends TestBase {
    LoginPage loginPage;
    ShowAllCustomersPage showAllCustomersPage;
    SoftAssert softAssert;
    Random random;
    CustomerInformationPage customerInformationPage;
    EditCustomerInformationPage editCustomerInformationPage;

    int randomCustomer;

    @BeforeMethod
    public void setUp() {
        super.setUp();
        loginPage = new LoginPage(driver);
        showAllCustomersPage = new ShowAllCustomersPage(driver);
        softAssert = new SoftAssert();
        customerInformationPage = new CustomerInformationPage(driver);
        editCustomerInformationPage = new EditCustomerInformationPage(driver);
        random = new Random();

        randomCustomer = random.nextInt(10) + 1;
    }

    @Test
    public void testLM_04_01() {

        Allure.step("Login success");
        loginPage.login("abcTrang@gmail.com", "123123");

        Allure.step("Open [Customer Information] page");
        showAllCustomersPage.openCustomerInformationPage(randomCustomer);

        Allure.step("Open [Edit Customer Information] page");
        customerInformationPage.clickEditButton();

        Allure.step("Click Information BreadCrumb");
        editCustomerInformationPage.clickInformationBreadcrumbButton();
        softAssert.assertTrue(customerInformationPage.isCustomerInformationPageDisplayed());

        Allure.step("Click Show All Customers BreadCrumb");
        editCustomerInformationPage.clickShowAllCustomersBreadcrumbButton();
        softAssert.assertTrue(showAllCustomersPage.isShowAllCustomersPageDisplayed());

        //conf casi home

        softAssert.assertAll();
    }
}