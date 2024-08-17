package example.LoginTestCases;

import example.TestBase;
import io.qameta.allure.Allure;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import page.Login.LoginPage;

public class VerifyLoginFailedWhenOneFieldIsBlank extends TestBase {
    @BeforeMethod
    public void setUp() {
        super.setUp();
        loginPage = new LoginPage(driver);
        softAssert = new SoftAssert();
    }

    @Test
    public void VerifyLoginFailedWhenOneFieldIsBlank() {

        Allure.step("Login function with no email");
        loginPage.login("", "VyLe123!");

        //Kiểm tra thông báo trường Email hiển thị
        Allure.step("Check message at Email field");
        softAssert.assertEquals(loginPage.emailTextMessage(), "Please enter your email", "Email field error");

        Allure.step("Delete password text box");
        loginPage.deleteTxbPass();

        Allure.step("Login function with no password");
        loginPage.login("abc@gmail.com","");

        //Kiểm tra thông báo trường password hiển thị
        Allure.step("Check message at Password field");
        softAssert.assertEquals(loginPage.passTextMessage(), "Please enter your password", "Password field error");

        softAssert.assertAll();
    }

    LoginPage loginPage;
    SoftAssert softAssert;
}
