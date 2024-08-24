package page.Home;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import page.SideBar.SideBar;

import java.time.Duration;

public class HomePage extends SideBar {
    WebDriver driver;

    public HomePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    By incomeLabelSelector = By.xpath("//span[@class='label label-success pull-right']/following-sibling::h5");

    public boolean isIncomeLabelDisplayed() {
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(d->d.findElement(incomeLabelSelector).isDisplayed());
    }
}
