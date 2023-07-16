package main.java.lib.ui.mobile_web;

import main.java.lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWSearchPageObject extends SearchPageObject {
    static {
        SEARCH_INIT_ELEMENT = "css:button#searchIcon";
        SEARCH_INPUT = "css:form > input[type='search']";
        SEARCH_CANCEL_BUTTON = "css:body > div.mw-overlays-container > div > div.overlay-header-container.header-container.header-chrome.position-fixed > div > div.header-action > button";
        //    SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://XCUIElementTypeStaticText[@name='{SUBSTRING}']";// or [contains(@name,'{SUBSTRING}')];
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://div[contains(@class,'wikidata-description')][contains(text(),'{SUBSTRING}')]";

        SEARCH_RESULT_ELEMENT = "css:ul.page-list>li.page-summary";///[@name='Java (programming language)']";/////
        SEARCH_EMPTY_RESULT_ELEMENT = "css:p.mw-search-nonefound";
//        GO_BACK_ARROW_BUTTON = "id:Back"; //"xpath://XCUIElementTypeButton[@name='Back']";
//        CLEAR_TEXT = "xpath://XCUIElementTypeButton[@name='Clear text']";
    }
    public MWSearchPageObject(RemoteWebDriver driver){
        super(driver);
    }
}
