package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ConfigReader;

import java.time.Duration;

public class PIMPage {
    private final WebDriver driver;
    private final By EMPLOYEE_SEARCH = By.xpath("(//input[@placeholder = 'Type for hints...'])[1]");
    private final By SEARCH_BUTTON = By.cssSelector("[type = 'submit']");
    private final By SEARCHED_EMPLOYEE_NAME = By.xpath("(//div[@class = 'oxd-table-cell oxd-padding-cell'])[3] / div");
    private final By NO_RECORDS_MESSAGE = By.cssSelector("[class = 'oxd-text oxd-text--p oxd-text--toast-message oxd-toast-content-text']");

    public PIMPage(WebDriver driver){
        this.driver = driver;
    }

    public void searchForEmployee(String employeeName){
        waitFor(EMPLOYEE_SEARCH).sendKeys(employeeName);
    }

    public void clickSearch(){
        driver.findElement(SEARCH_BUTTON).click();
    }

    public String getSearchedEmployeeName(){
        return waitFor(SEARCHED_EMPLOYEE_NAME).getText();
    }

    public String getNoRecordsMessage(){
        return waitFor(NO_RECORDS_MESSAGE).getText();
    }

    private WebElement waitFor(By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.getExplicitWait()))
                .until(d ->
                {
                    WebElement element = d.findElement(locator);
                    return element.isDisplayed() ? element : null;
                });
    }
}
