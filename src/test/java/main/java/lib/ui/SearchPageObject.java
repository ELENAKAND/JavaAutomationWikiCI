package main.java.lib.ui;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import main.java.lib.ui.MainPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;


abstract public class SearchPageObject extends MainPageObject {  //abstract class because we won't create no more instances from this class
     protected static  String       //remove <final> to change CONSTANTS from other classes
        SEARCH_INIT_ELEMENT,
        SEARCH_INPUT,
        SEARCH_CANCEL_BUTTON,
        SEARCH_CANCEL_CROSS_BUTTON,
             SEARCH_RESULT_BY_SUBSTRING_TPL,
        SEARCH_RESULT_BY_SUBSTRING_TPL2,
             SEARCH_RESULT_BY_SUBSTRING_TPL3,
        SEARCH_RESULT_ELEMENT,
        SEARCH_EMPTY_RESULT_ELEMENT,
        GO_BACK_ARROW_BUTTON,
        CLEAR_TEXT;
    public static Object waitFor;

    public SearchPageObject(RemoteWebDriver driver){      //Driver initialization from MainPageObject
        super(driver);                 //invoke the superclass constructor
    }
    /*TEMPLATE METHODS:*/
    public static String getResultSearchElement(String substring){   //Method won't interact with driver, just convert strings, so we can not to use driver and make method static
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
        }

    public static String getResultSearchElement2(String substring){   //Method won't interact with driver, just convert strings, so we can not to use driver and make method static
        return SEARCH_RESULT_BY_SUBSTRING_TPL2.replace("{SUBSTRING}", substring);
    }
    public static String getResultSearchElement3(String substring){   //Method won't interact with driver, just convert strings, so we can not to use driver and make method static
        return SEARCH_RESULT_BY_SUBSTRING_TPL3.replace("{SUBSTRING}", substring);
    }
    /**/
    @Step("Initializing search field")
    public void initSearchInput(){
        this.waitForElementPresent (SEARCH_INIT_ELEMENT, "Cannot find search input after clicking search init element");
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT, "Cannot find and click search init element", 5);
    }
    @Step("Send value '{search_line}' into the search field")
    public void typeSearchLine(String search_line){
        this.waitForElementAndSendKeys(SEARCH_INPUT, search_line, "Cannot find and type into search input", 5);
    }
    @Step("Get the  search result by '{substring}'")
    public void waitForSearchResult(String substring){
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(search_result_xpath, "Cannot find search result with substring "+substring);
    }
    public void waitForSearchFieldMW(String substring){
        String search_result_xpath = getResultSearchElement2(substring);
        this.waitForElementPresent(search_result_xpath, "Cannot find search result with substring "+substring);
    }
    @Step("Make sure that cancel-search button appeared")
    public void waitForCancelButtonToAppear(){
        this.waitForElementPresent(SEARCH_CANCEL_BUTTON, "Cannot find search cancel button", 5);
    }
    @Step("Make sure that cancel-search button disappeared")
    public void waitForCancelButtonToDisappear(){
        this.waitForElementNotPresent(SEARCH_CANCEL_BUTTON, "Search cancel button still present", 5);
    }
    @Step("Make sure that list of the search results elements disappeared")
    public void waitForSearchListIsEmpty(){
        this.waitForElementNotPresent(SEARCH_RESULT_ELEMENT, "Search results still here", 5);
    }
    @Step("Click by cancel-search button")
    public void clickCancelSearch(){
        this.waitForElementAndClick(SEARCH_CANCEL_BUTTON, "Cannot find and click search cancel button", 5);
    }
    @Step("Click twice by cancel-search button")
    public void clickCancelSearchTwice(){
        this.waitForElementAndClick(SEARCH_CANCEL_BUTTON, "Cannot find and click search cancel button", 5);
        this.waitForElementAndClick(SEARCH_CANCEL_BUTTON, "Cannot find and click search cancel button", 5);
    }
    @Step("Click by cancel-search cross-button")
    public void clickCrossCancelButton(){
        this.waitForElementAndClick(SEARCH_CANCEL_BUTTON, "Cannot find and click x cancel button", 5);
    }
    @Step("Find the article title by '{substring}' and click")
    public void clickByArticleWithSubstring(String substring){
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(search_result_xpath, "Cannot find and click search result with substring "+substring, 10);
    }
    @Step("Get amount of articles on the search list")
    public int getAmountOfFoundArticles(){
        this.waitForElementPresent(
                SEARCH_RESULT_ELEMENT,
                "Cannot find search result",
                15
        );
        return this.getAmountOfElements(SEARCH_RESULT_ELEMENT);
    }
    @Step("After entering non-existent value in the search field get label about empty search results")
    public void waitForEmptyResultsLabel(){
        this.waitForElementPresent(
                SEARCH_EMPTY_RESULT_ELEMENT,
                "Cannot find empty result element",
                15
        );
    }
    @Step("Make sure about empty search result page")
    public void assertThereIsNoResultOfSearch(){
        this.assertElementNotPresent(
                SEARCH_RESULT_ELEMENT,
                "We supposed not to find any results"
        );
    }
    @Step("Click cross-button to clear search field")
    public void clearSearchField(){
        this.waitForElementAndClick(CLEAR_TEXT, "Cannot find x button", 5);
    }
}
