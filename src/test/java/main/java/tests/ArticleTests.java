package main.java.tests;

import main.java.lib.CoreTestCase;
import main.java.lib.ui.ArticlePageObject;
import main.java.lib.ui.SearchPageObject;
import main.java.lib.ui.factories.ArticlePageObjectFactory;
import main.java.lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class ArticleTests extends CoreTestCase {
    @Test
    public void testCompareArticleTitle() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();                    //instead of waitForElementAndClick for search line
        SearchPageObject.typeSearchLine("Java");    //instead of waitForElementAndSendKeys
        SearchPageObject.clickByArticleWithSubstring("bject-oriented programming language"); //instead of waitForElementAndClick for search result
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver); //get driver android/ios
        String article_title = ArticlePageObject.getArticleTitle();

        assertEquals(
                "We see unexpected title",
                "Java (programming language)",
                article_title
        );
    }
    @Test
    public void testSwipeArticleTitle() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();                    //instead of waitForElementAndClick for search line
        SearchPageObject.typeSearchLine("Java");    //instead of waitForElementAndSendKeys
        SearchPageObject.clickByArticleWithSubstring("bject-oriented programming language");
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();
        ArticlePageObject.swipeToFooter();
    }
}
