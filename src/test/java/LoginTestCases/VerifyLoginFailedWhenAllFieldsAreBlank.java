package LoginTestCases;

import example.TestBase;
import io.qameta.allure.Allure;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import page.LoginPage;
import utils.ConfigReader;

public class VerifyLoginFailedWhenAllFieldsAreBlank extends TestBase {
    @BeforeMethod
    public void setUp() {
        super.setUp();
        loginPage = new LoginPage(driver);
        softAssert = new SoftAssert();
    }

    @Test
    public void VerifyLoginFailedWhenAllFieldsAreBlank() {
        Allure.step("Leave fields are blank");
        loginPage.login("","");

        Allure.step("Inspect message at email field");
        //Kiểm tra thông báo trường Email hiển thị
        softAssert.assertEquals(loginPage.emailTextMessage(), "Please enter your email", "Email field error");

        Allure.step("Inspect message at password field");
        //Kiểm tra thông báo trường Pass hiển thị
        softAssert.assertEquals(loginPage.passTextMessage(), "Please enter your password", "Password field error");

        softAssert.assertAll();
    }

    LoginPage loginPage;
    SoftAssert softAssert;
}
