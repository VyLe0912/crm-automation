package example.UserProfileManagement;

import example.TestBase;
import io.qameta.allure.Allure;
import page.Register.RegisterForm;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class UPM_04_02_VerifyRegisterFailedWhenAllFieldsAreBlank extends TestBase {
    @BeforeMethod
    public void setUp() {
        super.setUp();
        registerForm = new RegisterForm(driver);
        softAssert = new SoftAssert();
    }

    @Test
    public void verifyRegisterFailedWhenAllFieldsAreBlank() {

        Allure.step("Check Register function with all fields are blank");
        registerForm.clickBtnRegister();

        // Truong Email
        Allure.step("Check message at Email field");
        softAssert.assertEquals(registerForm.textMessEmail(), "Please enter your email", "Error email field");

        // Truong password
        Allure.step("Check message at Password field");
        softAssert.assertEquals(registerForm.textMessPass(), "Please enter your password", "Error password field");

        // Truong Confirm password
        Allure.step("Check message at Confirm password field");
        softAssert.assertEquals(registerForm.textMessConfPass(), "Please confirm your password", "Error confirm password");

        // Truong Name
        Allure.step("Check message at Name field");
        softAssert.assertEquals(registerForm.textMessName(), "Please enter your name", "Error name field");

        // Truong Company
        Allure.step("Check message at Company field");
        softAssert.assertEquals(registerForm.textMessCompany(), "Please enter your company", "Error company field");

        // Truong Phone
        Allure.step("Check message at Phone field");
        softAssert.assertEquals(registerForm.textMessPhone(), "Please enter your phone", "Error phone field");

        softAssert.assertAll();
    }

    RegisterForm registerForm;
    SoftAssert softAssert;
}
