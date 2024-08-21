package example.LeadManagement_LM_02_ViewTheLeads;

import example.TestBase;
import io.qameta.allure.Allure;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import page.Login.LoginPage;
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

        // bam nut [Last] phia tren
        Allure.step("Open last page");
        showAllCustomersPage.openLastCustomerPageTop();
        // xac minh trang hien tai la trang cuoi cung
        softAssert.assertEquals(showAllCustomersPage.getCurrentPage(), showAllCustomersPage.getTotalPage());

        // bam nut [First] phia tren
        Allure.step("Open first page");
        showAllCustomersPage.openFirstCustomerPageTop();
        // xac minh quay lai trang dau tien (1)
        softAssert.assertEquals(showAllCustomersPage.getCurrentPage(), 1);

        // bam nut [Next] phia tren
        Allure.step("Open next page");
        showAllCustomersPage.openNextCustomerPageTop();
        // xac minh trang hien thi la trang 2
        softAssert.assertEquals(showAllCustomersPage.getCurrentPage(), 2);

        // bam nut [Previous] phia tren
        Allure.step("Open previous page");
        showAllCustomersPage.openPreviousCustomerPageTop();
        // xac minh trang hien thi la trang 1
        softAssert.assertEquals(showAllCustomersPage.getCurrentPage(), 1);

        // bam nut [Last] phia duoi
        Allure.step("Open last page");
        showAllCustomersPage.openLastCustomerPageBottom();
        // xac minh trang hien tai la trang cuoi cung
        softAssert.assertEquals(showAllCustomersPage.getCurrentPage(), showAllCustomersPage.getTotalPage());

        // bam nut [First] phia duoi
        Allure.step("Open first page");
        showAllCustomersPage.openFirstCustomerPageBottom();
        // xac minh quay lai trang dau tien (1)
        softAssert.assertEquals(showAllCustomersPage.getCurrentPage(), 1);

        // bam nut [Next] phia duoi
        Allure.step("Open next page");
        showAllCustomersPage.openNextCustomerPageBottom();
        // xac minh trang hien thi la trang 2
        softAssert.assertEquals(showAllCustomersPage.getCurrentPage(), 2);

        // bam nut [Previous] phia duoi
        Allure.step("Open previous page");
        showAllCustomersPage.openPreviousCustomerPageBottom();
        // xac minh trang hien thi la trang 1
        softAssert.assertEquals(showAllCustomersPage.getCurrentPage(), 1);

        softAssert.assertAll();
    }
}
