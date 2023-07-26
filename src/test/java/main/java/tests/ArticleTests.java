package main.java.tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import main.java.lib.CoreTestCase;
import main.java.lib.ui.ArticlePageObject;
import main.java.lib.ui.SearchPageObject;
import main.java.lib.ui.factories.ArticlePageObjectFactory;
import main.java.lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

@Epic("Tests for articles")
public class ArticleTests extends CoreTestCase {
    @Test
    @Features(value = {@Feature(value = "Search"), @Feature(value = "Article")})
    @DisplayName("Compare article title with expected one")
    @Description("We open 'Java Object-oriented programming language' and make sure title is expected")
    @Step("Starting testCompareArticleTitle")
    @Severity(value = SeverityLevel.BLOCKER)
    public void testCompareArticleTitle() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();                    //instead of waitForElementAndClick for search line
        SearchPageObject.typeSearchLine("Java");    //instead of waitForElementAndSendKeys
        SearchPageObject.clickByArticleWithSubstring("bject-oriented programming language"); //instead of waitForElementAndClick for search result
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver); //get driver android/ios
        String article_title = ArticlePageObject.getArticleTitle();

       // ArticlePageObject.takeScreenshot("article_page");
        Assert.assertEquals(
                "We see unexpected title",
                "Java (programming language)",
                article_title
        );
    }
    @Test
    @Features(value = {@Feature(value = "Search"), @Feature(value = "Article")})
    @DisplayName("Swipe article to the footer")
    @Description("We open the article and swipe it to the footer")
    @Step("Starting testSwipeArticleTitle")
    @Severity(value = SeverityLevel.MINOR)
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
