package main.java.lib.ui.mobile_web;

import main.java.lib.ui.NavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWNavigationUI extends NavigationUI {
    static {
        MY_LISTS_LINK = "css:a[data-event-name='menu.watchlist']";   //a.menu__item--watchlist
        CLOSE_IOS_OVERLAY_BUTTON = "xpath://XCUIElementTypeButton[@name='Close']";
        //  SAVE_BUTTON = "xpath://XCUIElementTypeButton[@name='Save for later']";
        SAVE_IOS_BUTTON_WITH_ARTICLE = "xpath://XCUIElementTypeButton[@name='Saved']";
        OPEN_NAVIGATION = "css:#mw-mf-main-menu-button";


    }
    public MWNavigationUI(RemoteWebDriver driver){
        super(driver);
    }
}
