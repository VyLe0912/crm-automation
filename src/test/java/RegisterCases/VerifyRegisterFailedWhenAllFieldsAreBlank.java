package RegisterCases;

import page.Register.RegisterForm;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import page.utils.ConfigReader;

import java.time.Duration;

public class VerifyRegisterFailedWhenAllFieldsAreBlank {
    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        configReader = new ConfigReader();
        registerForm = new RegisterForm(driver);
        softAssert = new SoftAssert();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void verifyRegisterFailedWhenAllFieldsAreBlank() {
        driver.get(configReader.getUrl());
        driver.manage().window().setSize(new Dimension(1378, 744));
        registerForm.clickBtnRegister();

        // Truong Email
        softAssert.assertEquals(registerForm.textMessEmail(), "Please enter your email", "Error email field");
        // ruong password
        softAssert.assertEquals(registerForm.textMessPass(), "Please enter your password", "Error password field");
        // Truong Confirm password
        softAssert.assertEquals(registerForm.textMessConfPass(), "Please confirm your password", "Error confirm password");
        // Truong Name
        softAssert.assertEquals(registerForm.textMessName(), "Please enter your name", "Error name field");
        // Truong Company
        softAssert.assertEquals(registerForm.textMessCompany(), "Please enter your company", "Error company field");
        // Truong Phone
        softAssert.assertEquals(registerForm.textMessPhone(), "Please enter your phone", "Error phone field");

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
}
