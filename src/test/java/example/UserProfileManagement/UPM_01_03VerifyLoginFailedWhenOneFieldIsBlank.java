<<<<<<<< HEAD:src/test/java/example/UserProfileManagement/UPM_01_03VerifyLoginFailedWhenOneFieldIsBlank.java
package example.UserProfileManagement;
========
package example.LoginTestCases;
>>>>>>>> 54ace25 (lm04):src/test/java/example/LoginTestCases/VerifyLoginFailedWhenOneFieldIsBlank.java

import example.TestBase;
import io.qameta.allure.Allure;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
<<<<<<<< HEAD:src/test/java/example/UserProfileManagement/UPM_01_03VerifyLoginFailedWhenOneFieldIsBlank.java
import page.LoginPage;
========
import page.Login.LoginPage;
>>>>>>>> 54ace25 (lm04):src/test/java/example/LoginTestCases/VerifyLoginFailedWhenOneFieldIsBlank.java

public class UPM_01_03VerifyLoginFailedWhenOneFieldIsBlank extends TestBase {
    @BeforeMethod
    public void setUp() {
        super.setUp();
        loginPage = new LoginPage(driver);
        softAssert = new SoftAssert();
    }

    @Test
    public void VerifyLoginFailedWhenOneFieldIsBlank() {

        Allure.step("Login function with no email");
        loginPage.login("", "VyLe123!");

        //Kiểm tra thông báo trường Email hiển thị
        Allure.step("Check message at Email field");
        softAssert.assertEquals(loginPage.emailTextMessage(), "Please enter your email", "Email field error");

        Allure.step("Delete password text box");
        loginPage.deleteTxbPass();

        Allure.step("Login function with no password");
        loginPage.login("abc@gmail.com","");

        //Kiểm tra thông báo trường password hiển thị
        Allure.step("Check message at Password field");
        softAssert.assertEquals(loginPage.passTextMessage(), "Please enter your password", "Password field error");

        softAssert.assertAll();
    }

    LoginPage loginPage;
    SoftAssert softAssert;
}
