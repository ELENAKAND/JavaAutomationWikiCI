package main.java.lib.ui;

import io.appium.java_client.AppiumDriver;
import main.java.lib.Platform;
import main.java.lib.ui.MainPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class NavigationUI extends MainPageObject {
    protected static  String
            MY_LISTS_LINK,
     //       SAVE_BUTTON,
            SAVE_BUTTON_WITH_ARTICLE,
            CLOSE_OVERLAY_BUTTON,
            CLOSE_IOS_OVERLAY_BUTTON,
             SAVE_IOS_BUTTON_WITH_ARTICLE,
            OPEN_NAVIGATION;


    public NavigationUI(RemoteWebDriver driver){
        super(driver);
    }
    public void openNavigation(){
        if (Platform.getInstance().isMW()){
            this.waitForElementAndClick(
                    OPEN_NAVIGATION,
                    "Cannot find and click open navigation button",
                    5
            );
        } else {
            System.out.println("This method do nothing for the platform "+Platform.getInstance().getPlatformVar());
        }
    }
    public void clickMySavedLists(){
        this.waitForElementAndClick(
                SAVE_BUTTON_WITH_ARTICLE,
                "Cannot find SAVED button on the main page",
                5
        );
        this.waitForElementAndClick(
                CLOSE_OVERLAY_BUTTON,
                "Cannot find NOT NOW button",
                5
        );
    }
    public void clickIOSSavedLists(){
        this.waitForElementAndClick(
                SAVE_IOS_BUTTON_WITH_ARTICLE,
                "Cannot find iOS SAVE button",
                5
        );
        this.waitForElementAndClick(
                CLOSE_IOS_OVERLAY_BUTTON,
                "Cannot find NOT NOW button",
                5
        );
    }
    public void clickMWSavedList(){
        this.tryClickElementsWithFewAttempts(
                MY_LISTS_LINK,
                "Cannot find navigation button to My list",
                5
        );
    }
}
