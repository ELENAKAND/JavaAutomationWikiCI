package main.java.lib.ui.mobile_web;

import io.appium.java_client.AppiumDriver;
import main.java.lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWArticlePageObject extends ArticlePageObject {
    static {
        TITLE = "css:#content h1";
        FOOTER_ELEMENT = "css:footer";
        SAVE_BUTTON = "css:li#page-actions-watch #ca-watch.mw-ui-icon-wikimedia-star-base20";
        ADD_TO_LIST = "xpath://ul[@id='page-actions']//li[@id='page-actions-watch']//a[contains(@class,'star-base20')]"; //inactive star or css:li#page-actions-watch a#ca-watch.mw-ui-icon-wikimedia-star-base20
        OPTIONS_REMOVE_FROM_MY_LIST_BUTTON = "xpath://ul[@id='page-actions']//li[@id='page-actions-watch']//a[contains(@class,'unStar-progressive')]"; //open already added article (active star) or css:li#page-actions-watch a#ca-watch.mw-ui-icon-wikimedia-unStar-progressive

    }
    public MWArticlePageObject(RemoteWebDriver driver){
        super(driver);
    }
}
