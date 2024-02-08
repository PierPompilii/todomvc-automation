
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import javax.swing.*;
import java.util.concurrent.ExecutionException;

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
        //assert list contains "buy milk"
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
        for (int i = 0; i < 100; i++) {
            modifyItem.sendKeys(Keys.BACK_SPACE);
        }
        modifyItem.sendKeys("buy bread");
        modifyItem.sendKeys(Keys.ENTER);
        Thread.sleep(2000);
        // assert list contains "buy bread"
    }

    @Test
    void cancelModify() throws Exception {
        driver.get("https://todomvc.com/examples/angular/dist/browser/#/all");
        WebElement inputBox = driver.findElement(By.cssSelector(".new-todo"));
        inputBox.click();
        inputBox.sendKeys("buy milk");
        inputBox.sendKeys(Keys.ENTER);
        Thread.sleep(2000);
        WebElement changeItem = driver.findElement(By.cssSelector(".view > label"));
        Actions builder = new Actions(driver);
        builder.doubleClick(changeItem).perform();
        Thread.sleep(2000);
        WebElement page = driver.findElement(By.cssSelector("html"));
        page.click();
        Thread.sleep(2000);
        // assert list contains "buy milk"
    }

    @Test
    void tickOff() throws Exception{
        driver.get("https://todomvc.com/examples/angular/dist/browser/#/all");
        WebElement inputBox = driver.findElement(By.cssSelector(".new-todo"));
        inputBox.click();
        inputBox.sendKeys("buy milk");
        inputBox.sendKeys(Keys.ENTER);
        Thread.sleep(2000);
        WebElement tickBox = driver.findElement(By.cssSelector(".toggle"));
        tickBox.click();
        Thread.sleep(2000);
        // assert "buy milk" is in completed status
    }

    @Test
    void incomplete() throws Exception {
        driver.get("https://todomvc.com/examples/angular/dist/browser/#/all");
        WebElement inputBox = driver.findElement(By.cssSelector(".new-todo"));
        inputBox.click();
        inputBox.sendKeys("buy milk");
        inputBox.sendKeys(Keys.ENTER);
        Thread.sleep(2000);
        WebElement tickBox = driver.findElement(By.cssSelector(".toggle"));
        tickBox.click();
        tickBox.click();
        Thread.sleep(2000);
        // assert "buy milk" is in active status
    }

    @Test
    void clearItemByX() throws Exception {
        driver.get("https://todomvc.com/examples/angular/dist/browser/#/all");
        WebElement inputBox = driver.findElement(By.cssSelector(".new-todo"));
        inputBox.click();
        inputBox.sendKeys("buy milk");
        inputBox.sendKeys(Keys.ENTER);
        Thread.sleep(2000);
        WebElement changeItem = driver.findElement(By.cssSelector(".view > label"));
        Actions hover = new Actions(driver);
        hover.moveToElement(changeItem).perform();
        WebElement buttonX = driver.findElement(By.cssSelector(".destroy"));
        buttonX.click();
        Thread.sleep(2000);
        // assert list is empty
    }

    @Test
    void todoCount() throws Exception {
        driver.get("https://todomvc.com/examples/angular/dist/browser/#/all");
        WebElement inputBox = driver.findElement(By.cssSelector(".new-todo"));
        inputBox.click();
        inputBox.sendKeys("buy milk");
        inputBox.sendKeys(Keys.ENTER);
        Thread.sleep(2000);
    //    assert "1 item is left"
        WebElement tickBox = driver.findElement(By.cssSelector(".toggle"));
        tickBox.click();
    //  assert "0 items is left"
        tickBox.click();
        inputBox.click();
        inputBox.sendKeys("buy bread");
        inputBox.sendKeys(Keys.ENTER);
        Thread.sleep(2000);
    //    assert "2 items are left"
    }

    @Test
    void hiddenStatusBar() throws Exception {
        driver.get("https://todomvc.com/examples/angular/dist/browser/#/all");
        WebElement inputBox = driver.findElement(By.cssSelector(".new-todo"));
        inputBox.click();
        inputBox.sendKeys("buy milk");
        inputBox.sendKeys(Keys.ENTER);
        Thread.sleep(2000);
        WebElement changeItem = driver.findElement(By.cssSelector(".view > label"));
        Actions hover = new Actions(driver);
        hover.moveToElement(changeItem).perform();
        WebElement buttonX = driver.findElement(By.cssSelector(".destroy"));
        buttonX.click();
        Thread.sleep(2000);
        // assert the status bar is hidden, if we can't find a solution, take screenshot
    }

    @Test
    void toggleStatus() throws Exception {
        driver.get("https://todomvc.com/examples/angular/dist/browser/#/all");
        WebElement inputBox = driver.findElement(By.cssSelector(".new-todo"));
        inputBox.click();
        inputBox.sendKeys("buy milk");
        inputBox.sendKeys(Keys.ENTER);
        inputBox.click();
        inputBox.sendKeys("buy bread");
        inputBox.sendKeys(Keys.ENTER);
        WebElement firstTodo = driver.findElement(By.cssSelector("app-todo-item:nth-child(1) .toggle"));
        firstTodo.click();
        Thread.sleep(2000);
        //assert buy milk and buy bread is in all status
        //assert buy bread is in the active status
        //assert buy milk is in the completed status
    }

    @Test
    void displayClearCompleted() throws Exception {
        driver.get("https://todomvc.com/examples/angular/dist/browser/#/all");
        WebElement inputBox = driver.findElement(By.cssSelector(".new-todo"));
        inputBox.click();
        inputBox.sendKeys("buy milk");
        inputBox.sendKeys(Keys.ENTER);
        Thread.sleep(2000);
        //assert "clear completed" text is shown in the status bar
    }

    @Test
    void testClearCompleted() throws Exception {
        driver.get("https://todomvc.com/examples/angular/dist/browser/#/all");
        WebElement inputBox = driver.findElement(By.cssSelector(".new-todo"));
        inputBox.click();
        inputBox.sendKeys("buy milk");
        inputBox.sendKeys(Keys.ENTER);
        Thread.sleep(2000);
        WebElement tickBox = driver.findElement(By.cssSelector(".toggle"));
        tickBox.click();
        WebElement clearCompleted = driver.findElement(By.cssSelector(".clear-completed"));
        clearCompleted.click();
        Thread.sleep(2000);
        // assert list is empty
    }

    @Test
    void todoBleedOver() throws Exception {
        driver.get("https://todomvc.com/examples/angular/dist/browser/#/all");
        WebElement inputBox = driver.findElement(By.cssSelector(".new-todo"));
        inputBox.click();
        inputBox.sendKeys("buy milk");
        inputBox.sendKeys(Keys.ENTER);
        Thread.sleep(2000);
        driver.get("https://todomvc.com/examples/vue/dist/#/");
        Thread.sleep(2000);
        //assert list is empty
    }







    @AfterAll
    static void closeBrowser() {
        driver.quit();
    }
}
