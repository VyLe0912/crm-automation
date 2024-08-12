package RegisterCases;

import com.github.javafaker.Faker;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import page.Register.RegisterForm;
import page.Register.RegisterUser;
import page.utils.ConfigReader;

public class VerifyRegisterFailedWhenPasswordDontMatch {
    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        faker = new Faker();
        configReader = new ConfigReader();
        softAssert = new SoftAssert();
        registerForm = new RegisterForm(driver);
        email = faker.internet().emailAddress();
        name = faker.funnyName().name();
        company = faker.address().buildingNumber();
        phone = faker.phoneNumber().phoneNumber();
        registerUser = new RegisterUser("abc123@gmail.com", "abc123", "123abc", "vy", "due", "0896208700");
    }

    @Test
    public void VerifyRegisterFailedWhenPasswordDontMatch() {
        driver.get(configReader.getUrl());

        registerForm.SignUp(registerUser);
        softAssert.assertEquals(registerForm.getMessagePasswordDontMatch(), "Password does not match the confirm password!", "Error password and confirm password fields");
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
    String email;
    String name;
    String company;
    String phone;
}
