package main.java.lib.ui;

import io.qameta.allure.Step;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AuthorizationPageObject extends MainPageObject {
    private static final String
    LOGIN_BUTTON = "css:a.mw-ui-button.mw-ui-progressive", //"css:li#page-actions-watch a#ca-watch.mw-ui-icon-wikimedia-star-base20"
    LOGIN_INPUT = "css:#wpName1", //or  input[name='wpName']   the same locator
    PASSWORD_INPUT = "css:#wpPassword1", //or   input[name='wpPassword'] the same locator
    SUBMIT_BUTTON = "css:button.mw-htmlform-submit#wpLoginAttempt"; // or  button#wpLoginAttempt the same locator
    public AuthorizationPageObject(RemoteWebDriver driver){
        super(driver);
    }

    @Step("Click the element by '{LOGIN_BUTTON}'")
    public void clickAuthButton(){
        this.waitForElementPresent(LOGIN_BUTTON, "Cannot find auth button", 5);
        this.waitForElementAndClick(LOGIN_BUTTON, "Cannot find and click auth button", 5);
    }
    @Step("Click the element by '{login}', '{password}'")
    public void enterLoginData(String login, String password){
        this.waitForElementAndSendKeys(LOGIN_INPUT, login, "Cannot put login", 5);
        this.waitForElementAndSendKeys(PASSWORD_INPUT, password, "Cannot put password", 5);
    }
    @Step("Click the element by '{SUBMIT_BUTTON}'")
    public void submitForm(){
        this.waitForElementAndClick(SUBMIT_BUTTON, "Cannot find and click submit button", 5);
    }
}
