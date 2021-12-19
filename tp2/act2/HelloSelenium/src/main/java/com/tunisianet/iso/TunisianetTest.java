package com.tunisianet.iso;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class TunisianetTest {

    private WebDriver driver;

    /*
     * Finds an element then clicks on it
     */
    private void findThenClick(By selector) {
        WebElement element = driver.findElement(selector);
        element.click();
    }

    /*
     * Finds an input and types the text
     */
    private void findThenSendKeys(By selector, String input) {
        WebElement element= driver.findElement(selector);
        element.sendKeys(input);
    }

    /*
     * Finds an input, types the text and submits
     */
    private void findThenSendKeys(By selector, String input, boolean submit) {
        WebElement element= driver.findElement(selector);
        element.sendKeys(input);
        if (submit)
            element.submit();
    }

    /**
     * Select an option from a list of options
     * @param optionSelector Identifies the row containing the option
     * @param checkboxSelector identifies the option
     * @param n the position of the element ( 1-indexed )
     * @throws NotFoundException
     */
    private void findNthOptionAndCheck(By optionSelector, By checkboxSelector,  int n) throws NotFoundException {
        ArrayList<WebElement> elements = (ArrayList<WebElement>) driver.findElements(optionSelector);
        if (elements.size() < n)
            throw new NotFoundException("Element not Found");
        WebElement element = elements.get(n-1);
        element.findElement(checkboxSelector).click();
    }

    /**
     * -------------------------------- Start Test --------------------------------------------
     */

    /**
     * initializes the driver
     */
    public void init() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(12));
        driver.manage().timeouts().scriptTimeout(Duration.ofMinutes(2));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(12));
    }

    /**
     * Login to Tunisianet account
     * @param email
     * @param password
     */
    private void login(String email, String password) {
        try {
            Thread.sleep(2000);
            this.findThenClick(By.cssSelector(".user-info"));
            this.findThenClick(By.cssSelector("a[title='Identifiez-vous']"));
            this.findThenSendKeys(By.name("email"), email);
            this.findThenSendKeys(By.name("password"), password);
            this.findThenClick(By.id("submit-login"));
        }
        catch(InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Search for an article
     */
    private void search(String name) {
        this.findThenSendKeys(By.id("search_query_top"), name, true);
    }

    /**
     * Starts the checkout process after selecting the first article
     */
    private void startCheckout() {
        findThenClick(By.cssSelector(".product-title a")); // select the first article
        findThenClick(By.cssSelector(".btn.btn-primary.add-to-cart")); // add it to cart
        findThenClick(By.linkText("Commander")); // starting the checkout
        findThenClick(By.cssSelector(".checkout.cart-detailed-actions.card-block"));
    }

    /**
     * Fills up addresses (if none already exist)
     * @param company
     * @param vat
     * @param address1
     * @param address2
     * @param postalCode
     * @param city
     * @param phone
     */
    private void fillAddresses(String company, int vat, String address1,
                                String address2, String postalCode, String city, int phone) {
        try {
            this.findThenSendKeys(By.name("company"), company);
            this.findThenSendKeys(By.name("vat_number"), String.valueOf(vat));
            this.findThenSendKeys(By.name("address1"), address1);
            this.findThenSendKeys(By.name("address2"), address2);
            this.findThenSendKeys(By.name("postcode"), postalCode);
            this.findThenSendKeys(By.name("city"), city);
            this.findThenSendKeys(By.name("phone"), String.valueOf(phone));
        }
        catch(NoSuchElementException e) {
            System.out.println("Address already exists, continuing...");
        }
        this.findThenClick(By.name("confirm-addresses")); // continue
    }

    /**
     * Fills up delivery
     * @param deliveryOption
     * @param message
     */
    private void fillDelivery(int deliveryOption, String message) {
        try {
            this.findNthOptionAndCheck(By.cssSelector(".row.delivery-option"), By.tagName("input"), deliveryOption); // select a delivery option
            if (message != null) {
                this.findThenSendKeys(By.id("delivery_message"), message); // input message
            }
            findThenClick(By.name("confirmDeliveryOption")); // continue
        }
        catch(NotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Fills up payment
     */
    private void fillPayment(int paymentMethod) {
        try {
            this.findNthOptionAndCheck(By.cssSelector(".payment-option"), By.tagName("input"), paymentMethod); // select payment method
            findThenClick(By.cssSelector("#conditions-to-approve input")); // accept conditions
            findThenClick(By.cssSelector("#payment-confirmation button")); // finish checkout
        }
        catch(NotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shuts the Driver down
     */
    private void shutdown() {
        try {
            Thread.sleep(3000); // wait a little before shutting down
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.quit();
    }

    /**
     * Starts the Test
     */
    public void start() {
        init();
        driver.get("https://www.tunisianet.com.tn/");
        login("zouari.rami@yahoo.com","rami0000");
        search("PC portable MacBook M1 13.3");
        startCheckout();
        fillAddresses("INSAT", 15, "Address1", "Address2", "1150", "Ariana", 11223355);
        fillDelivery(2, "Some Message");
        fillPayment(2);
        shutdown();
    }
    public static void main(String[] args) {

        TunisianetTest test = new TunisianetTest();
        test.start();
    }
}
