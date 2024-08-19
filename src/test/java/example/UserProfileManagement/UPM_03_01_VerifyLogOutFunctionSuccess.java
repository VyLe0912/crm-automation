package example.UserProfileManagement;

import example.TestBase;
import io.qameta.allure.Allure;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import page.LoginPage;
import page.SideBar;

public class UPM_03_01_VerifyLogOutFunctionSuccess extends TestBase {
    @BeforeMethod
    public void setUp() {
        super.setUp();
        sideBar = new SideBar(driver);
        loginPage = new LoginPage(driver);
        softAssert = new SoftAssert();
    }

    @Test
    public void VerifyOpenLogOutModal() {

        Allure.step("Login funciton");
        loginPage.loginWithDefaultAccount();
        sideBar.clickBtnLogOut();// doi action log out

        Allure.step("Verify Log out function");
        softAssert.assertEquals(loginPage.getLabelWebsite(), "CRM DEMO", "log out failed" );
        softAssert.assertAll();
    }

    SideBar sideBar;
    LoginPage loginPage;
    SoftAssert softAssert;
}
