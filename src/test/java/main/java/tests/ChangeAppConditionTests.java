package main.java.tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import main.java.lib.CoreTestCase;
import main.java.lib.Platform;
import main.java.lib.ui.ArticlePageObject;
import main.java.lib.ui.SearchPageObject;
import main.java.lib.ui.factories.ArticlePageObjectFactory;
import main.java.lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

import java.time.Duration;

@Epic("Tests for checking App conditions ")
public class ChangeAppConditionTests extends CoreTestCase {
    @Test
    @Features(value = {@Feature(value = "Search"), @Feature(value = "Article"), @Feature(value = "App conditions")})
    @DisplayName("Check article before and after rotation")
    @Severity(value = SeverityLevel.TRIVIAL)
    public void testChangeScreenOrientationOnSearchResults() {
        if (Platform.getInstance().isMW()){
            return;
        }
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();                    //instead of waitForElementAndClick for search line
        SearchPageObject.typeSearchLine("Java");    //instead of waitForElementAndSendKeys
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language"); //instead of waitForElementAndClick for search result

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        String title_before_rotation = ArticlePageObject.getArticleTitle();
        this.rotateScreenLandscape();
        String title_after_rotation = ArticlePageObject.getArticleTitle();
        Assert.assertEquals(
                "Article was changed after rotation",
                title_before_rotation,
                title_after_rotation
        );
        this.rotateScreenPortrait();
        String title_after_second_rotation = ArticlePageObject.getArticleTitle();
        Assert.assertEquals(
                "Article was changed after rotation",
                title_before_rotation,
                title_after_second_rotation
        );
    }
    @Test
    @Features(value = {@Feature(value = "Search"), @Feature(value = "Article"), @Feature(value = "App conditions")})
    @DisplayName("Check article before and after background condition")
    @Severity(value = SeverityLevel.TRIVIAL)
    public void testCheckSearchArticleInBackground() {
        if (Platform.getInstance().isMW()){
            return;
        }
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();                    //instead of waitForElementAndClick for search line
        SearchPageObject.typeSearchLine("Java");    //instead of waitForElementAndSendKeys
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        SearchPageObject.waitForSearchResult("Object-oriented programming language"); //instead of waitForElementAndClick for search result
        this.backgroundApp(Duration.ofMillis(200));
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
    }
}
