package RegisterCases;

import io.qameta.allure.Allure;
import page.Register.RegisterForm;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import page.utils.ConfigReader;

public class VerifyUserOpenRegisterModal {
    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        configReader = new ConfigReader();
        registerForm = new RegisterForm(driver);
        softAssert = new SoftAssert();
    }

    @Test
    public void verifyUserOpenRegisterModal() {
        Allure.step("Open CRM Website");
        driver.get(configReader.getUrl());

        Allure.step("Verify Open Register Modal");
        softAssert.assertEquals(registerForm.getLabelRegisterForm(), "Register", "Open register form failed");
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
