package main.java.lib.ui;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import main.java.lib.ui.MainPageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

public class WelcomePageObject extends MainPageObject {
    private static final String
    STEP_MAIN_PAGE = "xpath://XCUIElementTypeButton[@name='Learn more about Wikipedia']",
    STEP_NEW_WAYS_TO_EXPLORE = "xpath://XCUIElementTypeStaticText[@name='New ways to explore']",
    STEP_SEARCH_IN_LANG = "xpath://XCUIElementTypeStaticText[@name='Search in over 300 languages']",
    STEP_MAKE_APP_BETTER = "xpath://XCUIElementTypeStaticText[@name='Learn more about data collected']",
    STEP_NEXT_BUTTON = "xpath://XCUIElementTypeButton[@name='Next']",
    STEP_GET_STARTED_BUTTON = "xpath://XCUIElementTypeStaticText[@name='Get started']",
    SKIP = "xpath://XCUIElementTypeButton[@name='Skip']";

    public WelcomePageObject(RemoteWebDriver driver) {
        super(driver);
    }
    @Step("Open iOS main page")
    public void waitForMainPage() {
        this.waitForElementPresent(STEP_MAIN_PAGE, "Cannot find welcome page the free encyclopedia", 10);
    }
    @Step("Click 1st iOS welcome page button")
    public void waitForNewWaysToExploreText() {
        this.waitForElementPresent(STEP_NEW_WAYS_TO_EXPLORE, "Cannot find second page The new ways", 10);
    }
    @Step("Click 2nd iOS welcome page button")
    public void waitForSearchInLangText() {
        this.waitForElementPresent(STEP_SEARCH_IN_LANG, "Cannot find third page Search in nearly 300 languages", 10);
    }
    @Step("Click 3d iOS welcome page button")
    public void waitForMakeTheAppBetterText() {
        this.waitForElementPresent(STEP_MAKE_APP_BETTER, "Cannot find 4th page Help make the app better", 10);
    }
    @Step("Click 4th iOS welcome page button")
    public void clickNextButton() {
        this.waitForElementAndClick(STEP_NEXT_BUTTON, "Cannot find and click the Next button", 10);
    }
    @Step("Click 'Get started' welcome page button")
    public void clickGetStartedButton() {
        this.waitForElementAndClick(STEP_GET_STARTED_BUTTON, "Cannot find and click the Get started button", 10);
    }
    @Step("Click skip on iOS welcome page to dismiss")
    public void clickSkip(){
        this.waitForElementAndClick(SKIP, "Cannot find skip button", 5);
    }
}
