package RegisterCases;

import example.TestBase;
import io.qameta.allure.Allure;
import page.Register.RegisterForm;
import page.Register.RegisterUser;
import com.github.javafaker.Faker;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.ConfigReader;

public class VerifyRegisterFailedWithInvalidEmail extends TestBase {
    @BeforeMethod
    public void setUp() {
        super.setUp();
        faker = new Faker();
        softAssert = new SoftAssert();
        registerForm = new RegisterForm(driver);
        driver.manage().window().setSize(new Dimension(1378, 840));
        password = faker.internet().password();
        confPassword = faker.internet().password();
        name = faker.funnyName().name();
        company = faker.address().buildingNumber();
        phone = faker.phoneNumber().phoneNumber();
    }

    @Test
    public void VerifyRegisterFailedWithInvalidEmail() {

        // Dia chi email khong hop le
        Allure.step("Register function with invalid email");
        registerUser = new RegisterUser("bsdkjfhodsfho;dhfk", password, confPassword, name, company, phone);
        registerForm.SignUp(registerUser);

        Allure.step("Check message at Email field");
        softAssert.assertEquals(registerForm.textMessEmail(), "The email is not valid (ex: abc@abc)", "Error email field");

        Allure.step("Delete all text box");
        registerForm.deleteAllTextbox();

        // Dia chi email khong dung
        Allure.step("Register function with invalid email");
        registerUser = new RegisterUser("abc@abc", password, confPassword, name, company, phone);
        registerForm.SignUp(registerUser);
        softAssert.assertEquals(registerForm.textMessEmail(), "The email is incorrect", "Error email field");

        softAssert.assertAll();
    }


    RegisterForm registerForm;
    SoftAssert softAssert;
    RegisterUser registerUser;
    Faker faker;
    String password;
    String confPassword;
    String name;
    String company;
    String phone;
}
