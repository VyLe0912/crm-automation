package example.UserProfileManagement;

import com.github.javafaker.Faker;
import example.TestBase;
import io.qameta.allure.Allure;
import models.RegisterForm;
import models.RegisterUser;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import page.Login.LoginPage;
import page.SideBar.SideBar;


public class UPM_04_07_VerifyUserRegisterSuccess extends TestBase{

    @BeforeMethod
    public void setUp() {
        super.setUp();
        registerForm = new RegisterForm(driver);
        softAssert = new SoftAssert();
        loginPage = new LoginPage(driver);
        sideBar = new SideBar(driver);
        testBase = new TestBase();
        faker = new Faker();
        email = faker.internet().emailAddress("ive456");
        registerUser = new RegisterUser(email, "abc123", "abc123", "nguyen van a", "due", "0896208700");
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
        softAssert.assertEquals(loginPage.getValueTxbEmail(), email, "doesn't have account");

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
    Faker faker;
    String email;
}
