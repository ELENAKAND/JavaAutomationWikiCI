package main.java.lib.ui;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.remote.RemoteWebDriver;

public class WelcomePageObjectAndroid extends MainPageObject {
    private static final String
    SKIP_BUTTON_ANDROID = "id:org.wikipedia:id/fragment_onboarding_skip_button";
    public WelcomePageObjectAndroid(RemoteWebDriver driver){
        super(driver);
    }
    @Step("Click skip button to dismiss welcome page on Android")
    public void clickSkipButtonAndroid(){
        this.waitForElementAndClick(SKIP_BUTTON_ANDROID, "Cannot find skip button Android", 5);
    }
}
