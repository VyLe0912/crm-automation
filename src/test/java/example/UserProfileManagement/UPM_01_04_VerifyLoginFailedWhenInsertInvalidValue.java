<<<<<<<< HEAD:src/test/java/example/UserProfileManagement/UPM_01_04_VerifyLoginFailedWhenInsertInvalidValue.java
package example.UserProfileManagement;
========
package example.LoginTestCases;
>>>>>>>> 54ace25 (lm04):src/test/java/example/LoginTestCases/VerifyLoginFailedWhenInsertInvalidValue.java

import example.TestBase;
import io.qameta.allure.Allure;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
<<<<<<<< HEAD:src/test/java/example/UserProfileManagement/UPM_01_04_VerifyLoginFailedWhenInsertInvalidValue.java
import page.LoginPage;
========
import page.Login.LoginPage;
>>>>>>>> 54ace25 (lm04):src/test/java/example/LoginTestCases/VerifyLoginFailedWhenInsertInvalidValue.java

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
        loginPage.login("abcmskldmgmail.com", "VyLe123!");
        softAssert.assertEquals(loginPage.emailTextMessage(), "The email is not valid (ex: abc@abc)", "Error");
        loginPage.deleteTxbEmail();

        //Kiem tra dia chi email chua dang ky
        Allure.step("Check email unregistered");
        loginPage.login("abc@abcgmail.com", "VyLe123!");
        softAssert.assertEquals(loginPage.noticeInvalid(), "The email or password is incorrect!", "Error message 1");
        loginPage.deleteTxbEmail();

        //Kiem tra dung dia chi email sai mat khau
        Allure.step("Check with valid email wrong password");
        loginPage.login("abc@gmail.com", "VyLe");
        softAssert.assertEquals(loginPage.noticeInvalid(), "The email or password is incorrect!", "Error mess 2");

        softAssert.assertAll();
    }

    LoginPage loginPage;
    SoftAssert softAssert;
}
