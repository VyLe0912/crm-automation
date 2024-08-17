<<<<<<<< HEAD:src/test/java/example/UserProfileManagement/UPM_01_01_VerifyUserOpenLoginModal.java
package example.UserProfileManagement;
========
package example.LoginTestCases;
>>>>>>>> 54ace25 (lm04):src/test/java/example/LoginTestCases/VerifyUserOpenLoginModal.java

import example.TestBase;
import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import page.Login.LoginPage;

public class UPM_01_01_VerifyUserOpenLoginModal extends TestBase {
    By labelLoginSelector = By.xpath("(//div[@class = 'ibox-title'])[1]");

    @BeforeMethod
    public void setUp() {
        super.setUp();
        loginPage = new LoginPage(driver);
    }

    @Test
    public void verifyLoginFunction() {
        Allure.step("Open Login modal");
        String labelLogin = driver.findElement(labelLoginSelector).getText();
        SoftAssert softAssert = new SoftAssert();

        //Kiểm tra hiển thị trang Đăng Nhập
        softAssert.assertEquals(labelLogin, "Login", "Access web failed");
        softAssert.assertAll();
    }


    LoginPage loginPage;
}
