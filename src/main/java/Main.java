import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.net.URL;
import java.nio.file.Paths;

public class Main {
    public static void main(String [] argv) throws Exception {
        URL resource = Main.class.getResource("chromedriver");
        String webDriverPath = Paths.get(resource.toURI()).toFile().getAbsolutePath();
        System.setProperty("webdriver.chrome.driver", webDriverPath);
        WebDriver webDriver = new ChromeDriver();
        executeTest(webDriver);
    }
    public static void executeTest(WebDriver webDriver) {
        webDriver.get("http://localhost:9601/");
        Tests.testUploadingFile(webDriver,"test.pgn");
        webDriver.quit();
    }
}
