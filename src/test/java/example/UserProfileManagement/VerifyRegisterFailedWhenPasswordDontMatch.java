package example.UserProfileManagement;

import example.TestBase;
import io.qameta.allure.Allure;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import page.Register.RegisterForm;
import page.Register.RegisterUser;

public class VerifyRegisterFailedWhenPasswordDontMatch extends TestBase {
    @BeforeMethod
    public void setUp() {
        super.setUp();
        softAssert = new SoftAssert();
        registerForm = new RegisterForm(driver);
        registerUser = new RegisterUser("vyth@gmail.com", "abc123", "123abc", "vy", "due", "0896208700");
    }

    @Test
    public void VerifyRegisterFailedWhenPasswordDontMatch() {

        Allure.step("Register function");
        registerForm.SignUp(registerUser);

        Allure.step("Compare password and Confirm password");
        softAssert.assertEquals(registerForm.getMessagePasswordDontMatch(), "Password does not match the confirm password!", "Error password and confirm password fields");
        softAssert.assertAll();
    }

    RegisterForm registerForm;
    SoftAssert softAssert;
    RegisterUser registerUser;
}
