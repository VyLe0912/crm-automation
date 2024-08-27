package example.UserProfileManagement;
import example.TestBase;
import io.qameta.allure.Allure;
import models.Objects.User;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import page.Login.LoginPage;

public class UPM_01_04_VerifyLoginFailedWhenInsertInvalidValue extends TestBase {
    @BeforeMethod
    public void setUp() {
        super.setUp();
        loginPage = new LoginPage(driver);
        softAssert = new SoftAssert();
    }

    @Test
    public void VerifyLoginFailedWhenInsertInvalidValue() {

        //Kiem tra dia chi email khong hop le
        Allure.step("Test invalid email");
        user = new User("abcmskldmgmail.com", "VyLe123!");
        loginPage.login(user);
        softAssert.assertEquals(loginPage.emailTextMessage(), "The email is not valid (ex: abc@abc)", "Error");
        loginPage.deleteTxbEmail();

        //Kiem tra dia chi email chua dang ky
        Allure.step("Check email unregistered");
        user = new User("abc@abcgmail.com", "VyLe123!");
        loginPage.login(user);
        softAssert.assertEquals(loginPage.noticeInvalid(), "The email or password is incorrect!", "Error message 1");
        loginPage.deleteTxbEmail();

        //Kiem tra dung dia chi email sai mat khau
        Allure.step("Check with valid email wrong password");
        user = new User("abc@gmail.com", "VyLe");
        loginPage.login(user);
        softAssert.assertEquals(loginPage.noticeInvalid(), "The email or password is incorrect!", "Error mess 2");

        Allure.step("Check with invalid email");
        user = new User("abc@abc", "VyLe123!");
        loginPage.login(user);
        softAssert.assertEquals(loginPage.emailTextMessage(), "The email is not valid", "Error mess 3");
        softAssert.assertAll();
    }

    LoginPage loginPage;
    SoftAssert softAssert;
    User user;
}
