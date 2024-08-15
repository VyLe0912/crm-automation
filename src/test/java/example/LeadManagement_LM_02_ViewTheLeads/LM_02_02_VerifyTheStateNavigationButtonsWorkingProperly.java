package example.LeadManagement_LM_02_ViewTheLeads;

import example.TestBase;
import io.qameta.allure.Allure;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import page.LoginPage;
import page.Customer.ShowAllCustomersPage;

public class LM_02_02_VerifyTheStateNavigationButtonsWorkingProperly extends TestBase {
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
    public void testLM_02_02() {

        Allure.step("Login success");
        loginPage.login("abcTrang@gmail.com", "123123");
        showAllCustomersPage.waitForNewCustomerButtonIsDisplayed();

        // xac minh trang hien tai la 1
        softAssert.assertEquals(showAllCustomersPage.getCurrentPage(), 1);

        // bam nut [Last]
        Allure.step("Open last page");
        showAllCustomersPage.openLastCustomerPage();
        // xac minh trang hien tai la trang cuoi cung
        softAssert.assertEquals(showAllCustomersPage.getCurrentPage(), showAllCustomersPage.getTotalPage());

        // bam nut [First]
        Allure.step("Open last page");
        showAllCustomersPage.openFirstCustomerPage();
        // xac minh quay lai trang dau tien (1)
        softAssert.assertEquals(showAllCustomersPage.getCurrentPage(), 1);

        // bam nut [Next]
        Allure.step("Open next page");
        showAllCustomersPage.openNextCustomerPage();
        // xac minh trang hien thi la trang 2
        softAssert.assertEquals(showAllCustomersPage.getCurrentPage(), 2);

        // bam nut [Previous]
        Allure.step("Open previous page");
        showAllCustomersPage.openPreviousCustomerPage();
        // xac minh trang hien thi la trang 1
        softAssert.assertEquals(showAllCustomersPage.getCurrentPage(), 1);

        softAssert.assertAll();
    }
}
