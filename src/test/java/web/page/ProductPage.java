package web.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductPage {

    WebDriver driver;

    By cartBadge = By.className("shopping_cart_badge");

    public ProductPage(WebDriver driver){
        this.driver = driver;
    }

    public void clickAddToCartButton(String productName) {
        String formattedName = productName.toLowerCase().replace(" ", "-");
        By addToCartButton = By.id("add-to-cart-" + formattedName);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Wait for the actual WebElement, not just clickable
        WebElement addBtn = wait.until(
                ExpectedConditions.presenceOfElementLocated(addToCartButton)
        );

        // Scroll into view (PASS WebElement, NOT wait!)
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView(true);", addBtn);

        // Force click (PASS WebElement, NOT wait!)
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", addBtn);

        // Now wait for shopping cart badge
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".shopping_cart_badge")
        ));

        System.out.println("SUCCESS — shopping cart badge found!");
    }

    public String getCartText(){
        return driver.findElement(cartBadge).getText().trim();
    }

}
