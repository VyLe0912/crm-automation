package example.RegisterCases;

import example.TestBase;
import io.qameta.allure.Allure;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import page.Login.LoginPage;
import models.RegisterForm;
import models.RegisterUser;
import page.SideBar.SideBar;

public class VerifyUserRegisterSuccess extends TestBase{

    @BeforeMethod
    public void setUp() {
        super.setUp();
        registerForm = new RegisterForm(driver);
        softAssert = new SoftAssert();
        loginPage = new LoginPage(driver);
        sideBar = new SideBar(driver);
        testBase = new TestBase();
        registerUser = new RegisterUser("vl12@gmail.com", "abc123", "abc123", "nguyen van a", "due", "0896208700");
    }

    @Test
    public void VerifyUserRegisterSuccess() {

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
        softAssert.assertEquals(loginPage.getValueTxbEmail(), "vl12@gmail.com", "doesn't have account");

        //Kiem tra hien thi ten cua tai khoan sau khi Dang nhap
        Allure.step("Verify login function");
        loginPage.login("", "abc123");
        Allure.step("Verify account's name");
        softAssert.assertEquals(sideBar.headerNameAccount(), "nguyen van a", "login failed");
        softAssert.assertAll();
    }


    RegisterForm registerForm;
    SoftAssert softAssert;
    RegisterUser registerUser;
    LoginPage loginPage;
    SideBar sideBar;
    TestBase testBase;
}
