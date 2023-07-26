package main.java.tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import main.java.lib.CoreTestCase;
import main.java.lib.Platform;
import main.java.lib.ui.SearchPageObject;
import main.java.lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

@Epic("Searching tests")
public class SearchTests extends CoreTestCase {
    @Test
    @Features(value = {@Feature(value = "Search"), @Feature(value = "Article")})
    @DisplayName("Test puts value to the search line and confirm results")
    @Severity(value = SeverityLevel.BLOCKER)
    public void testSearch() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();                    //instead of waitForElementAndClick
        SearchPageObject.typeSearchLine("Java");    //instead of waitForElementAndSendKeys
        SearchPageObject.waitForSearchResult("bject-oriented programming language"); //instead of waitForElementPresent
    }

    @Test
    @Features(value = {@Feature(value = "Search")})
    @DisplayName("Test initializes search and cancel it")
    @Severity(value = SeverityLevel.BLOCKER)
    public void testCancelSearch() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();                    //instead of waitForElementAndClick for search line
        SearchPageObject.clickCancelSearch();             //go back to the main page
    }

    @Test
    @Features(value = {@Feature(value = "Search")})
    @DisplayName("Test puts value to the search line and clear search field")
    @Severity(value = SeverityLevel.BLOCKER)
    public void testClearSearchAndReturn() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();                    //instead of waitForElementAndClick
        SearchPageObject.typeSearchLine("Java");    //instead of waitForElementAndSendKeys
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelButtonToDisappear();
    }

    @Test
    @Features(value = {@Feature(value = "Search")})
    @DisplayName("Test initializes search field and confirm placeholder")
    @Severity(value = SeverityLevel.BLOCKER)
    public void testElementHasText() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();                    //instead of waitForElementAndClick
        SearchPageObject.waitForSearchFieldMW("Search Wikipedia");
    }

    @Test
    @Features(value = {@Feature(value = "Search")})
    @DisplayName("Test puts value to the search line, confirms result list by title and cancel, confirms no results")
    @Severity(value = SeverityLevel.BLOCKER)
    public void testGetSearchResultsAndCancel() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();                    //instead of waitForElementAndClick
        String search_line = "Java";
        SearchPageObject.typeSearchLine(search_line);    //instead of waitForElementAndSendKeys
        int amount_of_search_results = SearchPageObject.getAmountOfFoundArticles();
        Assert.assertTrue(
                "We found no results",
                amount_of_search_results > 0
        );
        SearchPageObject.clearSearchField();
        SearchPageObject.waitForSearchListIsEmpty();
    }

    @Test
    @Features(value = {@Feature(value = "Search"), @Feature(value = "Article")})
    @DisplayName("Test puts value to the search line and confirm results by word")
    @Severity(value = SeverityLevel.BLOCKER)
    public void testSearchByWord() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();                    //instead of waitForElementAndClick
        SearchPageObject.typeSearchLine("Java");    //instead of waitForElementAndSendKeys
        SearchPageObject.waitForSearchResult("Java");
    }


    @Test
    @Features(value = {@Feature(value = "Search"), @Feature(value = "Article")})
    @DisplayName("Test puts value to the search line and confirms that any results existing")
    @Severity(value = SeverityLevel.BLOCKER)
    public void testAmountOfNotEmptySearch() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();                    //instead of waitForElementAndClick for search line
        String search_line = "Linkin Park discography";
        SearchPageObject.typeSearchLine(search_line);         //instead of waitForElementAndSendKeys
        int amount_of_search_results = SearchPageObject.getAmountOfFoundArticles();

        Assert.assertTrue(
                "We found too few results",
                amount_of_search_results > 0
        );
    }

    @Test
    @Features(value = {@Feature(value = "Search")})
    @DisplayName("Test puts non-existing value to the search line and confirm no search results")
    @Severity(value = SeverityLevel.BLOCKER)
    public void testAmountOfEmptySearch() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();                    //instead of waitForElementAndClick for search line
        String search_line = "whdfjgkxkan";
        SearchPageObject.typeSearchLine(search_line);          //instead of waitForElementAndSendKeys
        SearchPageObject.waitForEmptyResultsLabel();
        if (Platform.getInstance().isIOS()) {
            return; //can't define specific locator for iOS
        } else {
            SearchPageObject.assertThereIsNoResultOfSearch();
        }
    }
}

