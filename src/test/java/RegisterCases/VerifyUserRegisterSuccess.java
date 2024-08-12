package RegisterCases;

import example.TestBase;
import io.qameta.allure.Allure;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import page.HomePage;
import page.LoginPage;
import page.Register.RegisterForm;
import page.Register.RegisterUser;
import page.utils.ConfigReader;

import java.time.Duration;

public class VerifyUserRegisterSuccess {

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        configReader = new ConfigReader();
        registerForm = new RegisterForm(driver);
        softAssert = new SoftAssert();
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        testBase = new TestBase();
        driver.manage().window().setSize(new Dimension(1910, 744));
        registerUser = new RegisterUser("vyle12@gmail.com", "abc123", "abc123", "nguyen van a", "due", "0896208700");
    }

    @Test
    public void VerifyUserRegisterSuccess() {
        Allure.step("Open CRM Website");
        driver.get(configReader.getUrl());

        //Thuc hien Dang ky tai khoan
        Allure.step("Sign up");
        registerForm.SignUp(registerUser);
        registerForm.waitForRegisterLoading();

        //Kiem tra chuc nang Dang ky
        Allure.step("Verify register message");
        softAssert.assertEquals(registerForm.getMessageRegisterSuccess(), "Register success!", "register failed");

        //Kiem tra hien thi dia chi email moi
        Allure.step("Verify email text box");
        loginPage.waitToDownloadPage();
        softAssert.assertEquals(loginPage.getValueTxbEmail(), "vyle12@gmail.com", "doesn't have account");

        //Kiem tra hien thi ten cua tai khoan sau khi Dang nhap
        Allure.step("Verify login function");
        loginPage.login("", "abc123");
        Allure.step("Verify account's name");
        softAssert.assertEquals(homePage.headerNameAccount(), "nguyen van a", "login failed");
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
    LoginPage loginPage;
    HomePage homePage;
    TestBase testBase;
}
