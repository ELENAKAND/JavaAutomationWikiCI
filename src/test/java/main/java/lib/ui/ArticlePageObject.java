package main.java.lib.ui;

import io.appium.java_client.AppiumDriver;
import main.java.lib.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import static main.java.lib.ui.SearchPageObject.SEARCH_RESULT_BY_SUBSTRING_TPL;
import static main.java.lib.ui.SearchPageObject.SEARCH_RESULT_BY_SUBSTRING_TPL2;

abstract public class ArticlePageObject extends main.java.lib.ui.MainPageObject {
    protected static String
            TITLE,
            FOOTER_ELEMENT,
            SAVE_BUTTON,
            CREATE_NEW_LIST_BUTTON,
            SAVE_BUTTON_SECOND_SAVING,
            ADD_TO_LIST,
            ADD_APPIUM_TO_LIST,
            SECOND_ADD_TO_LIST,
            MY_LIST_NAME_INPUT,
            MY_LIST_OK_BUTTON,
            GO_BACK_ARROW_BUTTON,
            GO_BACK_IOS_BUTTON,
            SEARCH_CANCEL_BUTTON,
            OPTIONS_REMOVE_FROM_MY_LIST_BUTTON,
            GO_HOMEPAGE_BUTTON;

    public ArticlePageObject(RemoteWebDriver driver) {      //driver initialization
        super(driver);
    }

    public WebElement waitForTitleElement(){
        return this.waitForElementPresent(TITLE, "Cannot find article title on page", 30);
    }
    public WebElement waitForArticle (String substring, String error_message){
        return this.waitForElementPresent(this.getElementBySubstring(substring), "Cannot find article", 5);
    }

    public String getArticleTitle() {
        WebElement title_element = waitForTitleElement();
     //   screenshot(this.takeScreenshot("article_title"));
        if (Platform.getInstance().isAndroid()){
            return title_element.getAttribute("text");
        } else if (Platform.getInstance().isIOS()){
            return title_element.getAttribute("name");
        } else {
            return title_element.getText();
        }
    }
    public String getElementBySubstring(String substring){
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }
    public void swipeToFooter(){
        if (Platform.getInstance().isAndroid()){
            this.swipeUpToFindElement(
                    FOOTER_ELEMENT,
                    "Cannot find the end of the article",
                    40
            );
        } else if (Platform.getInstance().isIOS()){
                this.swipeUpTillElementAppear(
                        FOOTER_ELEMENT,
                        "Cannot find the end of the article",
                        40
                );
            } else {
            this.scrollTillElementNotVisible(
                    FOOTER_ELEMENT,
                    "Cannot find the end of the article",
                    40
            );
        }
    }

        public void addArticleToMyList(String folder_name){
            this.waitForElementAndClick(
                    SAVE_BUTTON,
                    "Cannot find SAVE button",
                    5
            );
            this.waitForElementAndClick(
                    ADD_TO_LIST,
                    "Cannot find ADD TO LIST button",
                    5
            );
            this.waitForElementAndClick(
                    MY_LIST_NAME_INPUT,
                    "Cannot find NAME OF LIST input field",
                    5
            );
            this.waitForElementAndSendKeys(
                    MY_LIST_NAME_INPUT,
                    folder_name,
                    "Cannot send input to the name of list",
                    5
            );
            this.waitForElementAndClick(
                    MY_LIST_OK_BUTTON,
                    "Cannot find OK button",
                    5
            );
        }
        public void addArticlesToMySaved(String folder_name){
            if (Platform.getInstance().isMW()){
                this.removeArticleFromSavedIfItAdded();
            }
            this.waitForElementAndClick(SAVE_BUTTON, "Cannot find option to add article to reading list",5);
            this.waitForElementAndClick(ADD_TO_LIST, "Cannot find ADD TO LIST option", 5);
            this.waitForElementAndClick(CREATE_NEW_LIST_BUTTON, "Cannot find Create new list",5);
            this.waitForElementAndClick(MY_LIST_NAME_INPUT, "Cannot find Create new list",5);
            this.waitForElementAndSendKeys(MY_LIST_NAME_INPUT, folder_name, "Cannot send input to the name of list", 5);
            this.waitForElementAndClick(MY_LIST_OK_BUTTON, "Cannot find Create new list",5);
        }
        public void addWebArticlesToMySaved(){
        if (Platform.getInstance().isMW()){
            this.removeArticleFromSavedIfItAdded();
        }
        this.waitForElementAndClick(SAVE_BUTTON, "Cannot find 'star-button' to save article", 5);
        }
        public void addAnotherWebArticleToSaved(){
            this.waitForElementAndClick(SAVE_BUTTON, "Cannot find 'star-button' to save article", 5);

        }
        public void removeArticleFromSavedIfItAdded(){
            if (this.isElementPresent(OPTIONS_REMOVE_FROM_MY_LIST_BUTTON)){
                this.waitForElementAndClick(
                        OPTIONS_REMOVE_FROM_MY_LIST_BUTTON,
                        "Cannot click button to remove article from saved",
                        1
                );
                this.waitForElementPresent(
                        OPTIONS_REMOVE_FROM_MY_LIST_BUTTON,
                        "Cannot find button to add article to saved after removing it from this list before",
                        5
                );
            }
        }
        public void addAnotherArticleToMySaved (String folder_name) {
            this.waitForElementAndClick(SAVE_BUTTON, "Cannot find option to add article to reading list", 5);
            this.waitForElementAndClick(ADD_APPIUM_TO_LIST, "Cannot find ADD TO LIST option", 5);
        }
        public void addAnotherArticleToMyList(){
            this.waitForElementAndClick(
                    SAVE_BUTTON_SECOND_SAVING,
                    "Cannot find second-save button ",
                    5
            );
            this.waitForElementAndClick(
                    ADD_TO_LIST,
                    "Cannot find ADD TO LIST button",
                    5
            );
        }
        public void closeArticle(){            //click arrow-button to go back
//            try {
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
            this.waitForElementAndClick(
                    GO_BACK_ARROW_BUTTON,
                    "Cannot find arrow-button to go back after saving list",
                    5
            );
        }
        public void closeIOSArticle(){
            this. waitForElementAndClick(
                    GO_BACK_IOS_BUTTON,
                    "Cannot find arrow-button to go back after saving list",
                    5
            );
            this. waitForElementAndClick(
                    SEARCH_CANCEL_BUTTON,
                    "Cannot find arrow-button to go back after saving list",
                    5
            );
        }
        public void closeWebArticle(){
         this.waitForElementAndClick(
                 GO_HOMEPAGE_BUTTON,
                 "Cannot return to the home page",
                 5
         );
        }
}
