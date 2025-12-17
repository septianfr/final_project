package web.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage {

    WebDriver driver;

    By cartCount = By.className("cart_quantity");

    public CartPage(WebDriver driver){
        this.driver = driver;
    }

    public String cartItemCount() {
        return driver.findElement(cartCount).getText().trim();
    }

    public void clickCheckoutButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement checkoutbtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("checkout")));

        // Combine scroll and click in one JS call
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView(true); arguments[0].click();", checkoutbtn);

        wait.until(ExpectedConditions.urlContains("checkout-step-one.html"));
    }
}