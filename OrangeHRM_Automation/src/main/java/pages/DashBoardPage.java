package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DashBoardPage {

    private WebDriver driver;

    private final By HEADER = By.cssSelector("h6[class*='oxd-text oxd-text--h6']");

    public DashBoardPage(WebDriver driver){
        this.driver = driver;
    }

    public String getHeaderText(){
        return waitFor(HEADER).getText();
    }

    private WebElement waitFor(By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(d ->
                {
                    WebElement element = d.findElement(locator);
                    return element.isDisplayed() ? element : null;
                });
    }
}
