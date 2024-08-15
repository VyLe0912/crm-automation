package LoginTestCases;

import example.TestBase;
import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import page.LoginPage;

public class VerifyUserOpenLoginModal extends TestBase {
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
