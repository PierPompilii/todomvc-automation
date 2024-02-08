
import dev.failsafe.internal.util.Assert;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.*;
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
        WebElement firstItem = driver.findElement(By.cssSelector(".view > label"));
        Assertions.assertEquals("buy milk", firstItem.getText());
    }
    @Test
    void modifyItems() throws Exception {
        driver.get("https://todomvc.com/examples/angular/dist/browser/#/all");
        WebElement inputBox = driver.findElement(By.cssSelector(".new-todo"));
        inputBox.click();
        inputBox.sendKeys("buy milk");
        inputBox.sendKeys(Keys.ENTER);
        WebElement firstItem = driver.findElement(By.cssSelector(".view > label"));
        Actions builder = new Actions(driver);
        builder.doubleClick(firstItem).perform();
        WebElement modifyItem = driver.findElement(By.id("edit-todo-input"));
        for (int i = 0; i < 100; i++) {
            modifyItem.sendKeys(Keys.BACK_SPACE);
        }
        modifyItem.sendKeys("buy bread");
        modifyItem.sendKeys(Keys.ENTER);
        Assertions.assertEquals("buy bread", firstItem.getText());
    }

    @Test
    void cancelModify() throws Exception {
        driver.get("https://todomvc.com/examples/angular/dist/browser/#/all");
        WebElement inputBox = driver.findElement(By.cssSelector(".new-todo"));
        inputBox.click();
        inputBox.sendKeys("buy milk");
        inputBox.sendKeys(Keys.ENTER);
        WebElement firstItem = driver.findElement(By.cssSelector(".view > label"));
        Actions builder = new Actions(driver);
        builder.doubleClick(firstItem).perform();
        WebElement page = driver.findElement(By.cssSelector("body"));
        page.click();
        Assertions.assertEquals("buy milk", firstItem.getText());
    }

    @Test
    void tickOff() throws Exception{
        driver.get("https://todomvc.com/examples/angular/dist/browser/#/all");
        WebElement inputBox = driver.findElement(By.cssSelector(".new-todo"));
        inputBox.click();
        inputBox.sendKeys("buy milk");
        inputBox.sendKeys(Keys.ENTER);
        WebElement firstItem = driver.findElement(By.cssSelector(".view > label"));
        WebElement tickBox = driver.findElement(By.cssSelector(".toggle"));
        tickBox.click();
        // assert "buy milk" is in completed status
        WebElement statusCompleted = driver.findElement(By.linkText("Completed"));
        statusCompleted.click();
        Assertions.assertTrue(firstItem.isDisplayed());
    }

    @Test
    void incomplete() throws Exception {
        driver.get("https://todomvc.com/examples/angular/dist/browser/#/all");
        WebElement inputBox = driver.findElement(By.cssSelector(".new-todo"));
        inputBox.click();
        inputBox.sendKeys("buy milk");
        inputBox.sendKeys(Keys.ENTER);
        WebElement firstItem = driver.findElement(By.cssSelector(".view > label"));
        WebElement tickBox = driver.findElement(By.cssSelector(".toggle"));
        tickBox.click();
        tickBox.click();
        WebElement statusActive = driver.findElement(By.linkText("Active"));
        statusActive.click();
        Assertions.assertTrue(firstItem.isDisplayed());
    }

    @Test
    void clearItemByX() throws Exception {
        driver.get("https://todomvc.com/examples/angular/dist/browser/#/all");
        WebElement inputBox = driver.findElement(By.cssSelector(".new-todo"));
        inputBox.click();
        inputBox.sendKeys("buy milk");
        inputBox.sendKeys(Keys.ENTER);
        WebElement firstItem = driver.findElement(By.cssSelector(".view > label"));
        Actions hover = new Actions(driver);
        hover.moveToElement(firstItem).perform();
        WebElement buttonX = driver.findElement(By.cssSelector(".destroy"));
        buttonX.click();
        // assert list is empty
        WebElement todoList = driver.findElement(By.tagName("app-todo-list"));
        Assertions.assertEquals("", todoList.getText());
    }

    @Test
    void todoCount() throws Exception {
        driver.get("https://todomvc.com/examples/angular/dist/browser/#/all");
        WebElement inputBox = driver.findElement(By.cssSelector(".new-todo"));
        inputBox.click();
        inputBox.sendKeys("buy milk");
        inputBox.sendKeys(Keys.ENTER);
        WebElement itemCount = driver.findElement(By.className("todo-count"));
    //    assert "1 item is left"
        Assertions.assertEquals("1 item left", itemCount.getText());
        WebElement tickBox = driver.findElement(By.cssSelector(".toggle"));
        tickBox.click();
    //  assert "0 items is left"
        Assertions.assertEquals("0 items left", itemCount.getText());
        tickBox.click();
        inputBox.click();
        inputBox.sendKeys("buy bread");
        inputBox.sendKeys(Keys.ENTER);
    //    assert "2 items are left"
        Assertions.assertEquals("2 items left", itemCount.getText());
    }

    @Test
    void hiddenStatusBar() throws Exception {
        driver.get("https://todomvc.com/examples/angular/dist/browser/#/all");
        WebElement inputBox = driver.findElement(By.cssSelector(".new-todo"));
        inputBox.click();
        inputBox.sendKeys("buy milk");
        inputBox.sendKeys(Keys.ENTER);
        WebElement changeItem = driver.findElement(By.cssSelector(".view > label"));
        Actions hover = new Actions(driver);
        hover.moveToElement(changeItem).perform();
        WebElement buttonX = driver.findElement(By.cssSelector(".destroy"));
        buttonX.click();
        // assert the status bar is hidden, if we can't find a solution, take screenshot, assert false display
        WebElement statusBar = driver.findElement(By.tagName("app-todo-footer"));
        Assertions.assertFalse(statusBar.isDisplayed());
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
        WebElement firstTodoToggle = driver.findElement(By.cssSelector("app-todo-item:nth-child(1) .toggle"));
        firstTodoToggle.click();
        WebElement firstTodo = driver.findElement(By.cssSelector("app-todo-item:nth-child(1) label"));
        WebElement secondTodo = driver.findElement(By.cssSelector("app-todo-item:nth-child(2) label"));
        //assert buy milk and buy bread is in all status
        WebElement statusAll = driver.findElement(By.linkText("All"));
        statusAll.click();
        Assertions.assertTrue(firstTodo.isDisplayed() && secondTodo.isDisplayed());
        //assert buy bread is in the active status
        WebElement statusActive = driver.findElement(By.linkText("Active"));
        statusActive.click();
        Assertions.assertTrue(secondTodo.isDisplayed());
        //assert buy milk is in the completed status
        WebElement statusCompleted = driver.findElement(By.linkText("Completed"));
        statusCompleted.click();
        firstTodo = driver.findElement(By.cssSelector("app-todo-item:nth-child(1) label"));
        // Only works when the driver is refreshed!!
        Assertions.assertTrue(firstTodo.isDisplayed());
    }

    @Test
    void displayClearCompleted() throws Exception {
        driver.get("https://todomvc.com/examples/angular/dist/browser/#/all");
        WebElement inputBox = driver.findElement(By.cssSelector(".new-todo"));
        inputBox.click();
        inputBox.sendKeys("buy milk");
        inputBox.sendKeys(Keys.ENTER);
        WebElement tickBox = driver.findElement(By.cssSelector(".toggle"));
        tickBox.click();
        //assert "clear completed" text is shown in the status bar
        WebElement clearButton = driver.findElement(By.cssSelector(".clear-completed"));
        Assertions.assertTrue(clearButton.isDisplayed());

    }

    @Test
    void testClearCompleted() throws Exception {
        driver.get("https://todomvc.com/examples/angular/dist/browser/#/all");
        WebElement inputBox = driver.findElement(By.cssSelector(".new-todo"));
        inputBox.click();
        inputBox.sendKeys("buy milk");
        inputBox.sendKeys(Keys.ENTER);
        WebElement tickBox = driver.findElement(By.cssSelector(".toggle"));
        tickBox.click();
        WebElement clearCompleted = driver.findElement(By.cssSelector(".clear-completed"));
        clearCompleted.click();
        // assert list is empty
        WebElement todoList = driver.findElement(By.tagName("app-todo-list"));
        Assertions.assertEquals("", todoList.getText());

    }

    @Test
    void todoBleedOver() throws Exception {
        driver.get("https://todomvc.com/examples/angular/dist/browser/#/all");
        WebElement inputBox = driver.findElement(By.cssSelector(".new-todo"));
        inputBox.click();
        inputBox.sendKeys("buy milk");
        inputBox.sendKeys(Keys.ENTER);
        driver.get("https://todomvc.com/examples/vue/dist/#/");
        //assert list is empty
    }







    @AfterAll
    static void closeBrowser() {
        driver.quit();
    }
}
