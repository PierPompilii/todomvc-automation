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

    //@Test
    //public void markComplete() {
    //    TodoAngularModel modelPage = new TodoAngularModel(driver);
    //    modelPage.addTodo("buy milk");
    //    modelPage.markComplete();
    //    modelPage
    //    Assertions.assertTrue(modelPage.isDisplayed());
    }






    @AfterAll
    static void closeBrowser() {
        driver.quit();
    }
}
