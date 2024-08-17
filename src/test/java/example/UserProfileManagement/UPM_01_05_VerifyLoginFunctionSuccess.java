<<<<<<<< HEAD:src/test/java/example/UserProfileManagement/UPM_01_05_VerifyLoginFunctionSuccess.java
package example.UserProfileManagement;
========
package example.LoginTestCases;
>>>>>>>> 54ace25 (lm04):src/test/java/example/LoginTestCases/VerifyLoginFunctionSuccess.java

import example.TestBase;
import io.qameta.allure.Allure;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
<<<<<<<< HEAD:src/test/java/example/UserProfileManagement/UPM_01_05_VerifyLoginFunctionSuccess.java
import page.LoginPage;
import models.Objects.User;
import page.SideBar;
========
import page.Login.LoginPage;
import models.User;
import page.SideBar.SideBar;
>>>>>>>> 54ace25 (lm04):src/test/java/example/LoginTestCases/VerifyLoginFunctionSuccess.java

public class UPM_01_05_VerifyLoginFunctionSuccess extends TestBase {
    @BeforeMethod
    public void setUp() {
        super.setUp();
        loginPage = new LoginPage(driver);
        softAssert = new SoftAssert();
        sideBar = new SideBar(driver);
        user = new User("abc@gmail.com", "VyLe123!");
    }

    @Test
    public void verifyLoginFunction() {
        Allure.step("Verify Login fucntion success");
        loginPage.login(user);
        softAssert.assertEquals(sideBar.headerNameAccount(), "Thy", "Login failed");
        softAssert.assertAll();
    }

    LoginPage loginPage;
    SideBar sideBar;
    User user;
    SoftAssert softAssert;
}
