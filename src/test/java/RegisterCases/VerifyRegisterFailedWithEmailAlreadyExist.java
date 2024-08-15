package RegisterCases;

import example.TestBase;
import io.qameta.allure.Allure;
import page.Register.RegisterForm;
import page.Register.RegisterUser;
import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.ConfigReader;

public class VerifyRegisterFailedWithEmailAlreadyExist extends TestBase {
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
