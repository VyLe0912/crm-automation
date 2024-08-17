package example.LoginTestCases;

import example.TestBase;
import io.qameta.allure.Allure;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import page.Login.LoginPage;
import models.User;
import page.SideBar.SideBar;

public class VerifyLoginFunctionSuccess extends TestBase {
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
        loginPage.loginFunc(user);
        softAssert.assertEquals(sideBar.headerNameAccount(), "Thy", "Login failed");
        softAssert.assertAll();
    }

    LoginPage loginPage;
    SideBar sideBar;
    User user;
    SoftAssert softAssert;
}
