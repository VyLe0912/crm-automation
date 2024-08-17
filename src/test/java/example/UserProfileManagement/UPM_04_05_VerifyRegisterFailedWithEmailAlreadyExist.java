<<<<<<<< HEAD:src/test/java/example/UserProfileManagement/UPM_04_05_VerifyRegisterFailedWithEmailAlreadyExist.java
package example.UserProfileManagement;

import example.TestBase;
import io.qameta.allure.Allure;
import page.Register.RegisterForm;
import page.Register.RegisterUser;
========
package example.RegisterCases;

import example.TestBase;
import io.qameta.allure.Allure;
import models.RegisterForm;
import models.RegisterUser;
>>>>>>>> 54ace25 (lm04):src/test/java/example/RegisterCases/VerifyRegisterFailedWithEmailAlreadyExist.java
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class UPM_04_05_VerifyRegisterFailedWithEmailAlreadyExist extends TestBase {
    @BeforeMethod
    public void setUp() {
        super.setUp();
        softAssert = new SoftAssert();
        registerForm = new RegisterForm(driver);
        registerUser = new RegisterUser("abc@gmail.com", "VyLe123!", "VyLe123!", "Vy", "iviettech", "0896208700");
    }

    @Test
    public void VerifyRegisterFailedWithEmailAlreadyExist() {

        Allure.step("Register function");
        registerForm.SignUp(registerUser);

        Allure.step("Check email is already exist");
        softAssert.assertEquals(registerForm.getMessageEmailExist(), "Email already exists!", "Error data email");
        softAssert.assertAll();
    }


    RegisterForm registerForm;
    SoftAssert softAssert;
    RegisterUser registerUser;
}
