package main.java.lib.ui.mobile_web;

import main.java.lib.ui.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWMyListsPageObject extends MyListsPageObject {
    static{
        ARTICLE_BY_TITLE_TPL = "xpath://ul[contains(@class,'content-unstyled')]//h3[contains(text(),'{TITLE}')]";
        REMOVE_FROM_SAVED_BUTTON = "xpath://ul[contains(@class,'content-unstyled')]//h3[contains(text(),'{TITLE}')]/../../a[contains(@class,'star-base20')]";
    }
    public MWMyListsPageObject(RemoteWebDriver driver){
        super(driver);
    }
}
