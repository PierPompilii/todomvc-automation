
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class TodoMVC {
    private static ChromeDriver driver;

    @BeforeAll
    static void launchBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    // Your tests will go here!
    @Test
    void homepage() {
        driver.get("https://todomvc.com/examples/angular/dist/browser/#/all");
    }
    @Test
    void addingItems() throws Exception {
        driver.get("https://todomvc.com/examples/angular/dist/browser/#/all");
        WebElement inputBox = driver.findElement(By.cssSelector(".new-todo"));
        inputBox.click();
        inputBox.sendKeys("buy milk");
        inputBox.sendKeys(Keys.ENTER);
        Thread.sleep(2000);
    }
    @Test
    void modifyItems() throws Exception {
        driver.get("https://todomvc.com/examples/angular/dist/browser/#/all");
        WebElement inputBox = driver.findElement(By.cssSelector(".new-todo"));
        inputBox.click();
        inputBox.sendKeys("buy milk");
        inputBox.sendKeys(Keys.ENTER);
        Thread.sleep(2000);
        WebElement changeItem = driver.findElement(By.cssSelector(".view > label"));
        Actions builder = new Actions(driver);
        builder.doubleClick(changeItem).perform();
        WebElement modifyItem = driver.findElement(By.id("edit-todo-input"));
        modifyItem.sendKeys(Keys.valueOf("BACK_SPACE"));
        modifyItem.sendKeys("buy bread");
        modifyItem.sendKeys(Keys.ENTER);
        Thread.sleep(2000);
    }





    @AfterAll
    static void closeBrowser() {
        driver.quit();
    }
}
