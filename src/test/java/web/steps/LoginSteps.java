package web.steps;

import static org.junit.Assert.assertTrue;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import web.page.LoginPage;

public class LoginSteps {

    WebDriver driver;
    LoginPage login;

    @Before
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
        login = new LoginPage(driver);
    }

    @Given("the user is on the login page")
    public void userOnLoginPage() {
        driver.get("https://www.saucedemo.com/");
    }

    @When("the user input valid username {string} and password {string}")
    @When("the user input invalid username {string} and password {string}")
    public void inputUsernamePassword(String username, String password) {
        login.inputUsername(username);
        login.inputPassword(password);
    }

    @When("the user leave the username empty and password {string}")
    public void leaveUsernameEmpty(String password) {
        login.inputUsername("");
        login.inputPassword(password);
    }

    @And("the user click the login button")
    public void userClickButton(){
        login.clickLoginButton();
    }

    @Then("the user successfully login to the inventory page")
    public void userSuccessfullyLoggedIn() {
        assertTrue(driver.getCurrentUrl().contains("inventory.html"));
    }

    @Then("the user will see an error message {string}")
    public void userSeesErrorMessage(String expectedMessage) {
        String actualMessage = login.getMessage();
        assertTrue(actualMessage.toLowerCase().contains(expectedMessage.toLowerCase()));
    }
}
