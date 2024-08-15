package RegisterCases;

import example.TestBase;
import io.qameta.allure.Allure;
import page.Register.RegisterForm;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.ConfigReader;

public class VerifyUserOpenRegisterModal extends TestBase {
    @BeforeMethod
    public void setUp() {
        super.setUp();
        registerForm = new RegisterForm(driver);
        softAssert = new SoftAssert();
    }

    @Test
    public void verifyUserOpenRegisterModal() {
        Allure.step("Verify Open Register Modal");
        softAssert.assertEquals(registerForm.getLabelRegisterForm(), "Register", "Open register form failed");
        softAssert.assertAll();
    }

    RegisterForm registerForm;
    SoftAssert softAssert;
}
