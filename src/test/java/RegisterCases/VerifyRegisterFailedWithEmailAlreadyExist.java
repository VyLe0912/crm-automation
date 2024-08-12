package RegisterCases;

import page.Register.RegisterForm;
import page.Register.RegisterUser;
import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import page.utils.ConfigReader;

public class VerifyRegisterFailedWithEmailAlreadyExist {
    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        configReader = new ConfigReader();
        softAssert = new SoftAssert();
        registerForm = new RegisterForm(driver);
        registerUser = new RegisterUser("abc@gmail.com", "VyLe123!", "VyLe123!", "Vy", "iviettech", "0896208700");
    }

    @Test
    public void VerifyRegisterFailedWithEmailAlreadyExist() {
        driver.get(configReader.getUrl());
        registerForm.SignUp(registerUser);
        softAssert.assertEquals(registerForm.getMessageEmailExist(), "Email already exists!", "Error data email");
        softAssert.assertAll();
    }

    @AfterMethod(alwaysRun = true)
    public void cleanUp() {
        driver.quit();
    }

    WebDriver driver;
    RegisterForm registerForm;
    ConfigReader configReader;
    SoftAssert softAssert;
    RegisterUser registerUser;
}
