package example;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import page.utils.ConfigReader;

import java.time.Duration;

public class TestBase {

    @BeforeClass(description = "Set up the test environment")
    public void setUp() {
        // Set up the test environment
        driver = new ChromeDriver();
        driver.get(configReader.getUrl());
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().setSize(new Dimension(1378, 744));
    }


    @AfterClass(description = "Tear down the test environment")
    public void tearDown() {
        // Tear down the test environment
        driver.quit();
    }

    protected WebDriver driver;
    protected ConfigReader configReader;
}
