package example.UserProfileManagement;

import example.TestBase;
import io.qameta.allure.Allure;

import example.TestBase;
import io.qameta.allure.Allure;
import models.RegisterForm;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class UPM_04_01_VerifyUserOpenRegisterModal extends TestBase {
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
