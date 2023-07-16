package main.java.lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import main.java.lib.ui.NavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IOSNavigationUI extends NavigationUI {
    static {
        MY_LISTS_LINK = "xpath://XCUIElementTypeButton[@name='Saved']"; //Save icon from iOS main page
        CLOSE_IOS_OVERLAY_BUTTON = "xpath://XCUIElementTypeButton[@name='Close']";
      //  SAVE_BUTTON = "xpath://XCUIElementTypeButton[@name='Save for later']";
        SAVE_IOS_BUTTON_WITH_ARTICLE = "xpath://XCUIElementTypeButton[@name='Saved']";

    }
    public IOSNavigationUI(RemoteWebDriver driver){
        super(driver);
    }
}
