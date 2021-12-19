import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TodoTest {
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
        WebDriverManager.firefoxdriver().setup();
        this.driver = new FirefoxDriver();
        this.webDriverWait = new WebDriverWait(driver, 3);
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(12));
        this.driver.manage().timeouts().scriptTimeout(Duration.ofMinutes(2));
        this.driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(12));
        actions = new Actions(driver);
    }

/*    @Test()
    public void verifyTodoListCreatedSuccessfully_noparams() throws Exception {
        this.driver.navigate().to("https://todomvc.com/");

        this.openTechnologyApp("Backbone.js");
        this.addNewTodoItem("Meet a Friend");
        this.addNewTodoItem("Buy Milk");
        addNewTodoItem("Clean the Cat");
        getItemCheckbox("Clean the Cat").click();
        assertLeftItems(2);
    }*/


    @ParameterizedTest()
    @ValueSource(strings = {
            "Backbone.js",
            "AngularJS",
            "Ember.js",
            "KnockoutJS",
            "Vue.js",
            "React",
            "Dart"
    })
    public void verifyTodoListCreatedSuccessfully_with_params(String technologyName) throws Exception {
        this.driver.navigate().to("https://todomvc.com/");

        this.openTechnologyApp(technologyName);
        this.addNewTodoItem("Meet a Friend");
        this.addNewTodoItem("Buy Milk");
        addNewTodoItem("Clean the Cat");
        getItemCheckbox("Clean the Cat").click();
        assertLeftItems(2);
    }

    /**
     * Opens a technology from the list
     * @param technologyName
     */
    private void openTechnologyApp(String technologyName) {
        WebElement technologyLink = waitAndFindElement(By.linkText(technologyName));
        technologyLink.click();
    }

    /**
     * Adds a new Item
     * @param itemName
     */
    private void addNewTodoItem(String itemName) {
        WebElement todoInput = waitAndFindElement(By.xpath("//input[@placeholder='What needs to be done?']"));
        todoInput.sendKeys(itemName);
        actions.click(todoInput).sendKeys(Keys.ENTER).perform();
    }

    /**
     * Tests whether the number of left items corresponds to the provided number
     * @param numLeftItems
     */
    private void assertLeftItems(int numLeftItems) {

        WebElement footer = waitAndFindElement(By.xpath("//footer/*/span | //footer/span"));
        boolean isAssertionTrue ;
        try {
            isAssertionTrue = webDriverWait.until(ExpectedConditions.textToBePresentInElement(footer, String.format("%d items left", numLeftItems)));
        }
        catch(TimeoutException e) {
            isAssertionTrue = false ; // text not found -> not the right number of elements left
        }
        Assertions.assertTrue(isAssertionTrue);
    }

    @AfterEach()
    public void afterTest() {
        driver.quit();
    }
}



