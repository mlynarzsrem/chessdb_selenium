package screens;

import lombok.AllArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@AllArgsConstructor
public abstract class BaseScreen {
    protected WebDriver webDriver;

    protected void waitForElement(String xpath) {
        new WebDriverWait(this.webDriver,3).until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
    }

    protected void waitAndClick(String xpath) {
        new WebDriverWait(this.webDriver,3).until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
        WebElement button = webDriver.findElement(By.xpath(xpath));
        button.click();
    }

    protected void waitAndSendKeys(String xpath, String value) {
        new WebDriverWait(this.webDriver,3).until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
        webDriver.findElement(By.xpath(xpath)).sendKeys(value);
    }
}
