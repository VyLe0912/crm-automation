<<<<<<<< HEAD:src/test/java/example/UserProfileManagement/UPM_01_02_VerifyLoginFailedWhenAllFieldsAreBlank.java
package example.UserProfileManagement;
========
package example.LoginTestCases;
>>>>>>>> 54ace25 (lm04):src/test/java/example/LoginTestCases/VerifyLoginFailedWhenAllFieldsAreBlank.java

import example.TestBase;
import io.qameta.allure.Allure;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
<<<<<<<< HEAD:src/test/java/example/UserProfileManagement/UPM_01_02_VerifyLoginFailedWhenAllFieldsAreBlank.java
import page.LoginPage;
========
import page.Login.LoginPage;
>>>>>>>> 54ace25 (lm04):src/test/java/example/LoginTestCases/VerifyLoginFailedWhenAllFieldsAreBlank.java

public class UPM_01_02_VerifyLoginFailedWhenAllFieldsAreBlank extends TestBase {
    @BeforeMethod
    public void setUp() {
        super.setUp();
        loginPage = new LoginPage(driver);
        softAssert = new SoftAssert();
    }

    @Test
    public void VerifyLoginFailedWhenAllFieldsAreBlank() {
        Allure.step("Leave fields are blank");
        loginPage.login("","");

        Allure.step("Inspect message at email field");
        //Kiểm tra thông báo trường Email hiển thị
        softAssert.assertEquals(loginPage.emailTextMessage(), "Please enter your email", "Email field error");

        Allure.step("Inspect message at password field");
        //Kiểm tra thông báo trường Pass hiển thị
        softAssert.assertEquals(loginPage.passTextMessage(), "Please enter your password", "Password field error");

        softAssert.assertAll();
    }

    LoginPage loginPage;
    SoftAssert softAssert;
}
