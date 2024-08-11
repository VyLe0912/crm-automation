package page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ShowAllCustomersPage {
    WebDriver driver;

    By newCustomerButtonSelector = By.xpath("//a[text()='New Customer']");
    By nameInputToSearchSelector = By.xpath("//th/span[text()='Name']/following-sibling::input");
    By customerNameButtonSelector;
    By customerQuantityDropdownListSelector;
    By firstPageButtonSelector = By.xpath("//span[@class='ui-icon ui-icon-seek-first']");
    By previousPageButtonSelector = By.xpath("//span[@class='ui-icon ui-icon-seek-prev']");
    By nextPageButtonSelector = By.xpath("//span[@class='ui-icon ui-icon-seek-next']");
    By lastPageButtonSelector = By.xpath("//span[@class='ui-icon ui-icon-seek-end']");
    By openCreateCustomerPageSelector = By.xpath("//h5[text()='Add Customer']");

    By noRecordFoundLabelSelector = By.xpath("//td[text()='No records found.']");
    By tableRowSelector = By.xpath("//tbody/tr");

    By customerNameLabelSelector = By.xpath("//tr/td/a[1]");
    By customerEmailLabelSelector = By.xpath("//tr/td[2]");
    By customerAddressLabelSelector = By.xpath("//tr/td[3]");
    By customerPhoneLabelSelector = By.xpath("//tr/td[4]");

    By pageCurrentLabelSelector = By.xpath("//div[@id='j_idt71:tbl_paginator_top']/span[@class='ui-paginator-current']");

    public ShowAllCustomersPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isShowAllCustomerPageDisplayed() {
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(d->d.findElement(newCustomerButtonSelector).isDisplayed());
    }

    public void waitForShowAllCustomersPageIsDisplayed() {
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(d->d.findElement(newCustomerButtonSelector).isDisplayed());
    }

    public boolean isCreateCustomerPageDisplayed() {
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(d->d.findElement(openCreateCustomerPageSelector).isDisplayed());
    }

    public void waitForCreateCustomerPageIsDisplayed() {
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(d->d.findElement(openCreateCustomerPageSelector).isDisplayed());
    }

    @Step ("Click [New Customer] button")
    public void clickNewCustomerButton() {
        driver.findElement(newCustomerButtonSelector).click();
    }

    @Step("Open [Create Customer] page")
    public void openCreateCustomerPage() {
        clickNewCustomerButton();
        waitForCreateCustomerPageIsDisplayed();
    }

    @Step("Open [Customer Information] page")
    public void openCustomerInformationPage(int i) {
        driver.findElements(customerNameLabelSelector).get(i).click();
    }

    @Step("Get customer name by index")
    public String getCustomerNameByIndex(int i) {
        return driver.findElements(customerNameLabelSelector).get(i-1).getText();
    }

    @Step("Get customer email by index")
    public String getCustomerEmailByIndex(int i) {
        return driver.findElements(customerEmailLabelSelector).get(i-1).getText();
    }

    @Step("Get customer phone by index")
    public String getCustomerPhoneByIndex(int i) {
        return driver.findElements(customerPhoneLabelSelector).get(i-1).getText();
    }

    @Step("Get customer address by index")
    public String getCustomerAddressByIndex(int i) {
        return driver.findElements(customerAddressLabelSelector).get(i-1).getText();
    }

    @Step("Input customer name to search")
    public void inputCustomerNameToSearch(String name) {
        driver.findElement(nameInputToSearchSelector).sendKeys(name);
    }

    public String getMessageWhenSearch() {
        return driver.findElement(noRecordFoundLabelSelector).getText();
    }

    @Step("Get number of row customer in first page")
    public int countRowInCurrentPage() {
        return driver.findElements(tableRowSelector).size();
    }

    @Step("Click first customer page button")
    public void openFirstCustomerPage() {
        driver.findElement(firstPageButtonSelector).click();
    }

    @Step("Click previous customer page button")
    public void openPreviousCustomerPage() {
        driver.findElement(previousPageButtonSelector).click();
    }

    @Step("Click next customer page button")
    public void openNextCustomerPage() {
        driver.findElement(nextPageButtonSelector).click();
    }

    @Step("Click last customer page button")
    public void openLastCustomerPage() {
        driver.findElement(lastPageButtonSelector).click();
    }

    public String getPageCurrentLabel() {
        return driver.findElement(pageCurrentLabelSelector).getText();
    }

//    String str = getPageCurrentLabel();
//    int start1 = str.indexOf('(');
//    int end1 = str.indexOf(' ');
//    String currentPage = str.substring(start1 + 1, end1);
//    int currentPageNumber = Integer.parseInt(currentPage);
//
//    int start2 = str.indexOf(' ');
//    int end2 = str.indexOf(')');
//    String totalPage = str.substring(start2 + 1, end2);
//    int totalPageNumber = Integer.parseInt(totalPage);

    public int getCurrentPage() {
//        String str = getPageCurrentLabel();
        int start1 = getPageCurrentLabel().indexOf('(');
        int end1 = getPageCurrentLabel().indexOf(' ');
        String currentPage = getPageCurrentLabel().substring(start1 + 1, end1);
        return Integer.parseInt(currentPage);
    }

    public int getTotalPage() {
//        String str = getPageCurrentLabel();
        int start2 = getPageCurrentLabel().lastIndexOf(' ');
        int end2 = getPageCurrentLabel().indexOf(')');
        String totalPage = getPageCurrentLabel().substring(start2 + 1, end2);
        return Integer.parseInt(totalPage);
    }
}
