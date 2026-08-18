package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ConfigReader;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class LoginPage {

    private final WebDriver driver;
    private final By USERNAME_FIELD = By.name("username");
    private final By PASSWORD_FIELD = By.name("password");
    private final By LOGIN_BUTTON = By.cssSelector("[type= 'submit']");
    private final By ERROR_MESSAGE = By.cssSelector("p[class = 'oxd-text oxd-text--p oxd-alert-content-text']");
    private final By REQUIRED_MESSAGES = By.cssSelector("[class='oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message']");

    public LoginPage(WebDriver driver){
        this.driver = driver;
    }

    public void enterUserName(String userName){
        waitFor(USERNAME_FIELD).sendKeys(userName);
    }

    public void enterPassword(String password){
        driver.findElement(PASSWORD_FIELD).sendKeys(password);
    }

    public DashBoardPage clickLogin(){
        driver.findElement(LOGIN_BUTTON).click();
        return new DashBoardPage(driver);
    }

    public String getErrorMessage(){
        return waitFor(ERROR_MESSAGE).getText();
    }

    /**
     * This method stores the web elements that holds the validation messages in a list then converting them to a string
     * @return the text of all "Required" messages appears
     */

    public List<String> getRequiredMessages(){
        List<WebElement> elements = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.getExplicitWait()))
                .until(ExpectedConditions.numberOfElementsToBeMoreThan(REQUIRED_MESSAGES, 0));

        return elements.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
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
