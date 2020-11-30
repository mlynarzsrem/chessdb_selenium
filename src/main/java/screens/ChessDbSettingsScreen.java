package screens;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.File;
import java.net.URL;
import java.nio.file.Paths;

public class ChessDbSettingsScreen extends BaseScreen {
    private static final String CONFIRM_BUTTON_XPATH = "//button[contains(text(),'Confirm')]";
    private static final String CLOSE_BUTTON_XPATH = "//button[@class='close-button']";

    @FindBy(xpath = "//input[@class='file-input']")
    private WebElement fileUploadInput;

    @FindBy(xpath ="//button[contains(text(),'Upload')]")
    private WebElement uploadButton;

    @FindBy(xpath ="//button[contains(text(),'Delete this database')]")
    private WebElement deleteDbButton;

    public ChessDbSettingsScreen(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(this.webDriver, this);
    }

    public ChessDbSettingsScreen uploadFile(String filename) {
        try {
            URL res = getClass().getClassLoader().getResource(filename);
            assert res != null;
            File file = Paths.get(res.toURI()).toFile();
            String absolutePath = file.getAbsolutePath();
            fileUploadInput.sendKeys(absolutePath);
            uploadButton.click();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return this;
    }

    public ChessDbSettingsScreen deleteChessDb() {
        deleteDbButton.click();
        waitAndClick(CONFIRM_BUTTON_XPATH);
        return this;
    }
}
