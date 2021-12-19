import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;

public class Test {
    private WebDriver driver;
    private WebDriverWait webDriverWait;
    private Actions actions;

    /**
     * Waits then returns an element
     * @param locator
     * @return
     */
    private WebElement waitAndFindElement(By locator) {
        return webDriverWait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    /**
     * Gets the checkbox for an item in the todo list
     * @param itemName
     * @return
     */
    private WebElement getItemCheckbox(String itemName) {
        WebElement checkbox = waitAndFindElement(By.xpath(String.format("//label[text()='%s']/preceding-sibling::input",itemName)));
        return checkbox;

    }

    @BeforeEach()
    public void setup() {
        WebDriverManager.edgedriver().setup();
        this.driver = new EdgeDriver();
        this.webDriverWait = new WebDriverWait(driver, 3);
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(12));
        this.driver.manage().timeouts().scriptTimeout(Duration.ofMinutes(2));
        this.driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(12));
        actions = new Actions(driver);
    }

    @org.junit.jupiter.api.Test()
    public void verifyTodoListCreatedSuccessfully_noparams() throws Exception {
        this.driver.navigate().to("https://todomvc.com/");

        this.openTechnologyApp("Backbone.js");
        this.addNewTodoItem("Meet a Friend");
        this.addNewTodoItem("Buy Milk");
        addNewTodoItem("Clean the Cat");
        getItemCheckbox("Clean the Cat").click();
        assertLeftItems(2);

    }

    /**
     * Opens a technology from the list
     */
    private void openTechnologyApp(String technologyName) {
        WebElement technologyLink = waitAndFindElement(By.linkText(technologyName));
        technologyLink.click();
    }

    /**
     * Adds a new Item
     */
    private void addNewTodoItem(String itemName) {
        WebElement todoInput = waitAndFindElement(By.xpath("//input[@placeholder='What needs to be done?']"));
        todoInput.sendKeys(itemName);
        actions.click(todoInput).sendKeys(Keys.ENTER).perform();
    }

    /**
     * Tests whether the number of left items corresponds to the provided number
     */
    private void assertLeftItems(int numLeftItems) {

        WebElement footer = waitAndFindElement(By.xpath("//footer/*/span | //footer/span"));
        boolean isAssertionTrue ;
        try {
            isAssertionTrue = webDriverWait.until(ExpectedConditions.textToBePresentInElement(footer, String.format("%d items left", numLeftItems)));
        }
        catch(TimeoutException e) {
            isAssertionTrue = false ; // No response is considered as false
        }
        Assertions.assertTrue(isAssertionTrue);
    }

    @AfterEach()
    public void afterTest() {
        driver.quit();
    }
}



