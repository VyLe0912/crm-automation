package RegisterCases;

import Register.RegisterForm;
import Register.RegisterUser;
import com.github.javafaker.Faker;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.ConfigReader;

import java.time.Duration;

public class VerifyRegisterFailedWithInvalidEmail {
    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        faker = new Faker();
        configReader = new ConfigReader();
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
        driver.get(configReader.getUrl());

        // Dia chi email khong hop le
        registerUser = new RegisterUser("bsdkjfhodsfho;dhfk", password, confPassword, name, company, phone);
        registerForm.SignUp(registerUser);
        softAssert.assertEquals(registerForm.textMessEmail(), "The email is not valid (ex: abc@abc)", "Error email field");

        // Dia chi email khong dung
        registerForm.deleteAllTextbox();
        registerUser = new RegisterUser("abc@abc", password, confPassword, name, company, phone);
        registerForm.SignUp(registerUser);

        softAssert.assertAll();
    }


    @AfterMethod(alwaysRun = true)
    public void cleanUp() {
        driver.quit();
    }

    WebDriver driver;
    RegisterForm registerForm;
    ConfigReader configReader;
    SoftAssert softAssert;
    RegisterUser registerUser;
    Faker faker;
    String password;
    String confPassword;
    String name;
    String company;
    String phone;
}
