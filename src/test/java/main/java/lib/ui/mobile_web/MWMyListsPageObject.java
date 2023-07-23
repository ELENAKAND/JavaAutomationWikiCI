package main.java.lib.ui.mobile_web;

import main.java.lib.ui.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWMyListsPageObject extends MyListsPageObject {
    static{
        ARTICLE_TITLE_TPL = "xpath://ul[contains(@class,'content-unstyled')]//h3[contains(text(),'{TITLE}')]";
        REMOVE_FROM_SAVED_BUTTON = "css:a.mw-ui-icon-wikimedia-unStar-progressive";//"xpath:////h3[contains(text(),'Appium')]/../..//a[contains(@class,'mw-ui-icon-wikimedia-unStar-progressive')]
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://h3[contains(text(),'{SUBSTRING}')]/../../a[contains(@class,'mw-ui-icon-wikimedia-unStar-progressive')]";
        SEARCH_RESULT_BY_SUBSTRING_TPL3 = "xpath://h3[contains(text(),'{SUBSTRING}')]";
    }
    public MWMyListsPageObject(RemoteWebDriver driver){
        super(driver);
    }
}
