package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ConfigReader;

import java.time.Duration;

public class DashBoardPage {

    private WebDriver driver;

    private final By HEADER = By.cssSelector("h6[class*='oxd-text oxd-text--h6']");
    private final By PIM = By.xpath("(//ul /li)[2]");

    public DashBoardPage(WebDriver driver){
        this.driver = driver;
    }

    public String getHeaderText(){
        return waitFor(HEADER).getText();
    }

    public PIMPage clickPIM(){
        waitFor(PIM).click();
        return new PIMPage(driver);
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
