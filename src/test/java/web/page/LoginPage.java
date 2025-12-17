package web.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    WebDriver driver;

    By usernameInputText = By.xpath("//*[@id='user-name']");
    By passwordInputText = By.xpath("//*[@id=\'password\']");
    By loginButton = By.id("login-button");
    By messageBox = By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]");

    public LoginPage(WebDriver driver){

        this.driver = driver;
    }

    public void inputUsername(String username){

        driver.findElement(usernameInputText).sendKeys(username);
    }

    public void inputPassword(String password){

        driver.findElement(passwordInputText).sendKeys(password);
    }

    public void clickLoginButton(){

        driver.findElement(loginButton).click();
    }

    public String getMessage() {

        return driver.findElement(messageBox).getText();
    }
}
