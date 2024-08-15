package example.LeadManagement_LM_02_ViewTheLeads;

import example.TestBase;
import io.qameta.allure.Allure;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import page.LoginPage;
import page.Customer.ShowAllCustomersPage;

public class LM_02_01_VierifyFirstPageOfShowAllCustomersScreenDisplayMaximum20Customer extends TestBase {
    ShowAllCustomersPage showAllCustomersPage;
    SoftAssert softAssert;
    LoginPage loginPage;

    @BeforeMethod
    public void setUp() {
        super.setUp();
        softAssert = new SoftAssert();
        showAllCustomersPage = new ShowAllCustomersPage(driver);
        loginPage = new LoginPage(driver);
    }

    @Test
    public void testLM_02_01() {

        Allure.step("Login success");
        loginPage.login("abcTrang@gmail.com", "123123");
        showAllCustomersPage.waitForNewCustomerButtonIsDisplayed();

        softAssert.assertEquals(showAllCustomersPage.countRowInCurrentPage(), 20, "The number of customers on the first page is not 20.");

        softAssert.assertAll();
    }
}
