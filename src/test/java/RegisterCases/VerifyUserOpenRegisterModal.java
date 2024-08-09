package RegisterCases;

import Register.RegisterForm;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.ConfigReader;

import java.time.Duration;

public class VerifyUserOpenRegisterModal {
    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        configReader = new ConfigReader();
        registerForm = new RegisterForm(driver);
        softAssert = new SoftAssert();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void verifyUserOpenRegisterModal() {
        driver.get(configReader.getUrl());
        driver.manage().window().setSize(new Dimension(1378, 744));
        softAssert.assertEquals(registerForm.labelRegisterForm(), "Register", "Open register form failed");
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
