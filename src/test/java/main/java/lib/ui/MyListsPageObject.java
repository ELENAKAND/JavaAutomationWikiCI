package main.java.lib.ui;

import io.appium.java_client.AppiumDriver;
import main.java.lib.Platform;
import main.java.lib.ui.MainPageObject;
import main.java.lib.ui.factories.SearchPageObjectFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;


abstract public class MyListsPageObject extends MainPageObject {
    protected static  String
            FOLDER_BY_NAME_TPL,
            ARTICLE_BY_TITLE_TPL,
            ARTICLE_TITLE_TPL,
            READING_LIST_BUTTON,
            SEARCH_RESULT_BY_SUBSTRING_TPL,
            CLICK_BY_ARTICLE,
            OVERLAY_BUTTON,
            TRASH_BUTTON,
            APPIUM_LOCATOR,
            SAVED_LIST_BUTTON,
            SEARCH_RESULT_BY_SUBSTRING_TPL3,
            REMOVE_FROM_SAVED_BUTTON;
    private static String getFolderXpathByName(String name_of_folder){
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", name_of_folder);
    }
    private static String getSavedArticleXpathByTitle(String article_title){
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", article_title);
    }
    private static String getSavedArticleXpath(String article){
        return ARTICLE_TITLE_TPL.replace("{SUBSTRING}", article);
    }
    public static String getResultElement(String substring){   //Method won't interact with driver, just convert strings, so we can not to use driver and make method static
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }
    private static String getRemoveButtonByTitle(String article_title){
        return REMOVE_FROM_SAVED_BUTTON.replace("{TITLE}", article_title);
    }
    public MyListsPageObject(RemoteWebDriver driver){      //constructor
        super(driver);
    }
    public  void openSavedFolderByName(String name_of_folder){
        if (Platform.getInstance().isIOS()){
            this.waitForElementAndClick(SAVED_LIST_BUTTON, "Cannot find reading list button", 5);
        } else {
            String folder_name_xpath = getFolderXpathByName(name_of_folder);
            this.waitForElementAndClick(
                    folder_name_xpath,
                    "Cannot find folder by name in the SAVE " + name_of_folder,
                    5
            );
        }
    }
    public void waitForArticleToAppearByTitle(String article_title){
        String article_xpath = getFolderXpathByName(article_title);
        this.waitForElementPresent(
                article_xpath,
                "Cannot find saved article by  title "+article_title,
                15);

    }
    public  void waitWebArticleToAppearBySubstring(String substring){
        String article_locator = SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
        this.waitForElementPresent(
                article_locator,
                "Cannot find article by substring",
                5
        );
    }
    public void waitForArticleToDisappearByTitle(String article_title){
        String article_xpath = getFolderXpathByName(article_title);
        this.waitForElementNotPresent(
                article_xpath,
                "Saved article still present with title "+article_title,
                15);

    }
    public void waitForWebArticleToDisappearByTitle(String article_title){
        String title = this.getSavedArticleXpath(article_title);
        this.waitForElementNotPresent(title, "Article title still on the page", 5);
    }
    public void swipeArticleToDelete(String article_title){
        this.waitForArticleToAppearByTitle(article_title);
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        if (Platform.getInstance().isAndroid() || Platform.getInstance().isIOS()){
            this.swipeElementToLeft(
                    article_xpath,
                    "Cannot find saved article"
            );
        } else {
            String remove_locator = getRemoveButtonByTitle(article_title);//REMOVE_FROM_SAVED_BUTTON
            this.waitForElementAndClick(
                    remove_locator,
                    "Cannot click button to remove article from saved",
                    10
            );
        }
        if (Platform.getInstance().isIOS()){
            this.clickElementToTheRightUpperCorner(article_xpath, "Cannot find saved article");
            this.waitForElementAndClick(TRASH_BUTTON, "Cannot find trash icon", 5);
        }
        this.waitForArticleToDisappearByTitle(article_title);
    }
    public void clickWebArticleWithSubstring(String substring){
        String article_locator = SearchPageObject.getResultSearchElement3(substring);
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.waitForElementAndClick(article_locator, "Cannot find and click search result with substring "+substring, 10);
    }
    public void  deleteWebArticle(){
        this.waitForElementAndClick(
                REMOVE_FROM_SAVED_BUTTON,
                "Cannot click button to remove article from saved",
                10
        );
        driver.navigate().refresh();
    //    this.waitForArticleToDisappearByTitle(article_title);
    }

    public void swipeArticle2Delete(String substring, String article_title){
    //    this.waitForArticle(substring, "Cannot find article");
        String article = this.getSavedArticleXpath(substring);
        if (Platform.getInstance().isAndroid() || Platform.getInstance().isIOS()){
            this.swipeElementToLeft(article, "Cannot find to swipe");
        } else {
            String remove_locator = getRemoveButtonByTitle(article_title);//REMOVE_FROM_SAVED_BUTTON
            this.waitForElementAndClick(
                    remove_locator,
                    "Cannot click button to remove article from saved",
                    10
            );
        }
        if (Platform.getInstance().isIOS()){
            //    this.clickElementToTheRightUpperCorner(article_xpath, "Cannot find saved article");
            this.waitForElementAndClick(TRASH_BUTTON, "Cannot find trash icon", 5);
        }
    }
}
