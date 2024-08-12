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

public class VerifyRegisterFailedWithEmailAlreadyExist {
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
        registerUser = new RegisterUser("abc@gmail.com", "VyLe123!", "VyLe123!", "Vy", "iviettech", "0896208700");
    }

    @Test
    public void VerifyRegisterFailedWithEmailAlreadyExist() {
        driver.get(configReader.getUrl());
        registerForm.SignUp(registerUser);
        softAssert.assertEquals(registerForm.messageEmailExist(), "Email already exists!", "Error data email");
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
