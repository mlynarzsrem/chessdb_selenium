package screens;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginScreen extends BaseScreen {
    private static final String USERNAME_INPUT_XPATH = "//input[@name='username']";
    private static final String USERNAME_PASSWORD_XPATH = "//input[@name='password']";
    private static final String LOGIN_BUTTON_XPATH = "//button[contains(text(),'Login')]";
    private static final String REGISTER_BUTTON_XPATH = "//button[contains(text(),'Register')]";
    private static final String LOGGED_IN_MESSAGE_XPATH = "//div[@class='logged-in-as']";
    private static final String CLOSE_BUTTON_XPATH = "//button[@class='close-button']";

    public LoginScreen(WebDriver webDriver) {
        super(webDriver);
    }

    public DashboardScreen register(String username, String password) {
        waitAndClick("//div[contains(text(),'Create account')]");
        waitAndSendKeys(USERNAME_INPUT_XPATH, username);
        waitAndSendKeys(USERNAME_PASSWORD_XPATH, password);
        try {
            waitAndClick(REGISTER_BUTTON_XPATH);
            new WebDriverWait(this.webDriver,3).until(ExpectedConditions.presenceOfElementLocated(By.xpath(LOGGED_IN_MESSAGE_XPATH)));
            return new DashboardScreen(this.webDriver);
        } catch (Exception e){
            waitAndClick(CLOSE_BUTTON_XPATH);
            waitAndClick("//div[@class='link' and contains(text(),'Login')]");
            waitAndClick(LOGIN_BUTTON_XPATH);
            new WebDriverWait(this.webDriver,3).until(ExpectedConditions.presenceOfElementLocated(By.xpath(LOGGED_IN_MESSAGE_XPATH)));
            return new DashboardScreen(this.webDriver);
        }
    }
}
