package web.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import web.page.cartPage;
import web.page.checkoutPage;
import web.page.loginPage;
import web.page.productPage;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class checkoutSteps {

    WebDriver driver;
    cartPage cart;
    loginPage login;
    productPage product;
    checkoutPage checkout;

    @Before("@checkout")
    public void setup(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new"); // headless mode (wajib di CI)
        options.addArguments("--incognito"); // 🕶️ Incognito mode
        options.addArguments("--no-sandbox"); // bypass OS security model
        options.addArguments("--disable-dev-shm-usage"); // avoid limited resource in /dev/shm
        options.addArguments("--disable-gpu");
        options.addArguments("--disable-notifications");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-search-engine-choice-screen");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--user-data-dir=/tmp/chrome-profile-" + System.currentTimeMillis());

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();

    }

    @Before("@checkout")
    public void setLoginCheckout() {
        cart = new cartPage(driver);
        login = new loginPage(driver);
        product = new productPage(driver);

        driver.get("https://www.saucedemo.com/");
        login.inputUsername("standard_user");
        login.inputPassword("secret_sauce");
        login.clickLoginButton();

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains("/inventory.html"));

        product.clickAddToCartButton("Sauce Labs Backpack");

        driver.get("https://www.saucedemo.com/cart.html");

        cart.clickCheckoutButton();

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("first-name")));

        checkout = new checkoutPage(driver);
    }

    @Given("the user is on the Checkout page")
    public void user_is_on_checkout_page() {
    }

    @When("the user enter the details, first name {string}, last name {string} and the zip code {int}")
    public void user_enter_details(String firstName, String lastName, int zipCode) {
        checkout.inputFirstName(firstName);
        checkout.inputLastName(lastName);
        checkout.inputZipCode(zipCode);
    }

    @And("the user click the Continue button")
    public void use_click_continue_button() {
        checkout.clickContinueButton();
    }

    @Then("then the item has been successfully purchased, which the user will be redirected to the Checkout overview page")
    public void checkout_overview_page() {
        assertTrue(driver.getCurrentUrl().contains("checkout-step-two.html"));
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();

        }
    }
}
