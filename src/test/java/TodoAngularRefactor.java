import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import static org.junit.jupiter.api.Assertions.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;

public class TodoAngularRefactor {
    private static ChromeDriver driver;

    @BeforeAll
    static void launchBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://todomvc.com/examples/angular/dist/browser/#/all");
    }
    @Test
    public void addToList() {
        TodoAngularModel modelPage = new TodoAngularModel(driver);
        modelPage.addTodo("buy milk");
        Assertions.assertEquals("buy milk", modelPage.getFirstTodo());
    }

    @Test
    public void modifyItems() {
        TodoAngularModel modelPage = new TodoAngularModel(driver);
        modelPage.addTodo("buy milk");
        modelPage.modify("buy bread");
        Assertions.assertEquals("buy bread", modelPage.getFirstTodo());
    }

    @Test
    public void cancelModify() {
        TodoAngularModel modelPage = new TodoAngularModel(driver);
        modelPage.addTodo("buy milk");
        modelPage.cancelModify();
        Assertions.assertEquals("buy milk", modelPage.getFirstTodo());
    }

    @Test
    public void markTodoAsCompletedTest() {
        TodoAngularModel modelPage = new TodoAngularModel(driver);
        modelPage.addTodo("buy milk");
        modelPage.tickBoxFirst();
        Assertions.assertTrue(modelPage.isCompleted());
    }

    @Test
    public void markAsIncompleteTest() {
        TodoAngularModel modelPage = new TodoAngularModel(driver);
        modelPage.addTodo("buy milk");
        modelPage.tickBoxFirst();
        modelPage.tickBoxFirst();
        Assertions.assertTrue(modelPage.isNotComplete());
    }

    @Test
    public void clearTodoByXTest() {
        TodoAngularModel modelPage = new TodoAngularModel(driver);
        modelPage.addTodo("buy milk");
        modelPage.clearItemByX();
        Assertions.assertEquals("", modelPage.getTodoList());
    }
    @Test
    public void todoCountTest () {
        TodoAngularModel modelPage = new TodoAngularModel(driver);
        modelPage.addTodo("buy milk");
        Assertions.assertEquals("1 item left", modelPage.getTodoCount());
        modelPage.tickBoxFirst();
        Assertions.assertEquals("0 items left", modelPage.getTodoCount());
        modelPage.tickBoxFirst();
        modelPage.addTodo("buy bread");
        Assertions.assertEquals("2 items left", modelPage.getTodoCount());
    }
    @Test
    public void hiddenStatusBar() {
        TodoAngularModel modelPage = new TodoAngularModel(driver);
        modelPage.addTodo("buy milk");
        modelPage.clearItemByX();
        Assertions.assertFalse(modelPage.statusBar());
    }

    @Test
    public void toggleStatusBarTest () {
        TodoAngularModel modelPage = new TodoAngularModel(driver);
        modelPage.addTodo("buy milk");
        modelPage.addTodo("buy bread");
        modelPage.tickBoxFirst();
        modelPage.statusAll();
        Assertions.assertTrue(modelPage.firstTodoDisplayed());
        Assertions.assertTrue(modelPage.secondTodoDisplayed());
        modelPage.statusActive();
        Assertions.assertTrue(modelPage.firstTodoDisplayed());
        modelPage.statusCompleted();
        Assertions.assertTrue(modelPage.firstTodoDisplayed());
    }
    @Test
    public void displayClearCompletedTest () {
        TodoAngularModel modelPage = new TodoAngularModel(driver);
        modelPage.addTodo("buy milk");
        modelPage.tickBoxFirst();
        modelPage.clearButton();
        Assertions.assertTrue(modelPage.isCompleted());
    }
    @Test
    public void clearCompletedTest() {
        TodoAngularModel modelPage = new TodoAngularModel(driver);
        modelPage.addTodo("buy milk");
        modelPage.tickBoxFirst();
        modelPage.clearButton();
        Assertions.assertEquals("",);

    }


    @AfterAll
    static void closeBrowser() {
        driver.quit();
    }
}
