package web.steps;

import static org.junit.Assert.assertEquals;

import io.cucumber.java.After;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import web.page.ProductPage;
import web.page.LoginPage;

import java.time.Duration;

public class ProductSteps {

    WebDriver driver;
    ProductPage product;
    LoginPage login;

    @Before("@inventory")
    public void setup(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new"); // headless mode (wajib di CI)
        options.addArguments("--no-sandbox"); // bypass OS security model
        options.addArguments("--disable-dev-shm-usage"); // avoid limited resource in /dev/shm
        options.addArguments("--disable-gpu");
        options.addArguments("--disable-notifications");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--user-data-dir=/tmp/chrome-profile-" + System.currentTimeMillis());

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        product = new ProductPage(driver);
        login = new LoginPage(driver);
    }

    @Before("@inventory")
    public void setLogin() {
        driver.get("https://www.saucedemo.com/");
        login.inputUsername("standard_user");
        login.inputPassword("secret_sauce");
        login.clickLoginButton();

        new WebDriverWait(driver, Duration.ofSeconds(10))
        .until(ExpectedConditions.urlContains("/inventory.html"));
    }

    @Given("the user is on the inventory page")
    public void userOnTheInventoryPage() {
        driver.get("https://www.saucedemo.com/inventory.html");
    }

    @When("the user click the Add to Cart button for {string}")
    public void userClickAddToCartButton(String productName) {
        product.clickAddToCartButton(productName);
    }

    @Then("the item should be added to the cart, which the cart badge should show {string}")
    public void itemShouldBeAddedToTheCart(String itemCount) {
        String actual = product.getCartText();
        assertEquals(itemCount, actual);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();

        }
    }
}
