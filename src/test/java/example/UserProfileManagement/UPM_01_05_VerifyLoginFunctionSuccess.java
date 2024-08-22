package example.UserProfileManagement;

import example.TestBase;
import io.qameta.allure.Allure;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import page.Login.LoginPage;
import models.Objects.User;
import page.SideBar.SideBar;

public class UPM_01_05_VerifyLoginFunctionSuccess extends TestBase {
    @BeforeMethod
    public void setUp() {
        super.setUp();
        loginPage = new LoginPage(driver);
        softAssert = new SoftAssert();
        sideBar = new SideBar(driver);
        user = new User("abc@gmail.com", "VyLe123!");
    }

    @Test
    public void verifyLoginFunction() {
        Allure.step("Verify Login fucntion success");
        loginPage.login(user);
        softAssert.assertEquals(sideBar.headerNameAccount(), "Vy", "Login failed");
        softAssert.assertAll();
    }

    LoginPage loginPage;
    SideBar sideBar;
    User user;
    SoftAssert softAssert;
}
