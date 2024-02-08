import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class TodoAngularModel {
    protected WebDriver driver;
    private By inputBoxBy = By.cssSelector(".new-todo");
    private By firstTodoBy = By.cssSelector("app-todo-item:nth-child(1) label");
    private By secondTodoBy = By.cssSelector("app-todo-item:nth-child(2) label");
    private By modifyTodoBy = By.id("edit-todo-input");
    private By pageBy = By.cssSelector("body");
    private By tickBoxFirstTodoBy = By.cssSelector("app-todo-item:nth-child(1) .toggle");
    private By tickBoxSecondTodoBy = By.cssSelector("app-todo-item:nth-child(1) .toggle");
    private By statusAllBy = By.linkText("All");
    private By statusActiveBy = By.linkText("Active");
    private By statusCompletedBy = By.linkText("Completed");
    private By xButtonBy = By.cssSelector(".destroy");
    private By todoListBy = By.tagName("app-todo-list");
    private By todoCountBy = By.className("todo-count");
    private By statusBarBy = By.tagName("app-todo-footer");
    private By clearCompletedButtonBy = By.cssSelector(".clear-completed");

    public TodoAngularModel(ChromeDriver driver) {
        this.driver = driver;
    }


    public void addTodo(String todo) {
        WebElement inputBox = driver.findElement(inputBoxBy);
        inputBox.click();
        inputBox.sendKeys(todo + Keys.ENTER);
    }

    public String getFirstTodo() {
        WebElement firstTodo = driver.findElement(firstTodoBy);
        return firstTodo.getText();
    }
    public String getSecondTodo() {
        WebElement secondTodo = driver.findElement(secondTodoBy);
        return secondTodo.getText();
    }

    public void modify(String todo) {
        WebElement firstTodo = driver.findElement(firstTodoBy);
        Actions builder = new Actions(driver);
        builder.doubleClick(firstTodo).perform();
        WebElement modifyTodo = driver.findElement(modifyTodoBy);
        for (int i = 0; i < 100; i++) {
            modifyTodo.sendKeys(Keys.BACK_SPACE);
        }
        modifyTodo.sendKeys(todo + Keys.ENTER);
    }

    public void cancelModify() {
        WebElement firstTodo = driver.findElement(firstTodoBy);
        Actions builder = new Actions(driver);
        builder.doubleClick(firstTodo).perform();
        WebElement page = driver.findElement(pageBy);
        page.click();
    }
}
