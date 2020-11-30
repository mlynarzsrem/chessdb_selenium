package screens;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class DashboardScreen extends BaseScreen {
    private static final String DATABASE_ROW_XPATH = "//div[contains(text(),'%s')]/ancestor::div[@class='database-div']";
    private static final String DATABASE_INFO_XPATH = "//div[@class='db-info-display selected-db']/ancestor::div[@class='database-div']";
    private static final String ADD_DB_BUTTON_XPATH = "//button[contains(text(),'Add new')]";
    private static final String NEW_DB_NAME_XPATH = "//input[@placeholder='Enter new db name...']";
    private static final String CONFIRM_BUTTON_XPATH = "//button[@class='confirm']";
    private static final String PAGE_FORWARD_BUTTON_XPATH = "//button[contains(text(),'Page forward')]";

    @FindBy(xpath ="//button[contains(text(),'Logout')]")
    private WebElement logoutButton;

    public DashboardScreen(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(this.webDriver, this);
    }

    public DashboardScreen createDb(String dbName) {
        waitForElement(ADD_DB_BUTTON_XPATH);
        if (webDriver.findElements(By.xpath(getDatabaseRowXpath(dbName))).isEmpty()) {
            waitAndClick(ADD_DB_BUTTON_XPATH);
            waitForElement(NEW_DB_NAME_XPATH);
            webDriver.findElement(By.xpath(NEW_DB_NAME_XPATH)).sendKeys(dbName);
            waitAndClick(CONFIRM_BUTTON_XPATH);
        }
        return this;
    }
    public ChessDbSettingsScreen goToDatabaseSettings(String dbName){
        getDatabaseSettingsButton(dbName).click();
        return new ChessDbSettingsScreen(this.webDriver);
    }

    public void logOut() {
        logoutButton.click();
    }

    public void goToListOfGames(String dbName) {
        waitAndClick(getDatabaseRowXpath(dbName));
        waitAndClick(DATABASE_INFO_XPATH);
        waitForElement(PAGE_FORWARD_BUTTON_XPATH);
    }

    public void goToGameScreen() {
        waitAndClick(DATABASE_INFO_XPATH);
        waitAndClick("//span[contains(text(),'Mackenzie, George Henry')]");
        waitForElement("//button[contains(text(),'Flip board')]");
    }

    private WebElement getDatabaseSettingsButton(String dbName) {
        final String dbRowXpath = getDatabaseRowXpath(dbName);
        waitForElement(dbRowXpath);
        return webDriver
                .findElement(By.xpath(dbRowXpath+"//button[@class='db-panel-button edit-button']"));
    }

    private String getDatabaseRowXpath(String dbName) {
        return String.format(DATABASE_ROW_XPATH, dbName);
    }
}
