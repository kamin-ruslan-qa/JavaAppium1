package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;


@Epic("Wikipedia Application")
public class SearchTests extends CoreTestCase
{
    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Description("Verify that search returns results for valid query")
    @Step("Executing search and verifying results")
    @DisplayName("Basic search test")
    @Features({@Feature("Search"), @Feature("Results Validation")})
    public void testSearch()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        try {
            Thread.sleep(1000); // небольшая пауза
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that search can be cancelled and results disappear")
    @Step("Cancelling search and verifying results are gone")
    @DisplayName("Cancel search test")
    @Feature("Search Cancellation")
    public void testCancelSearch() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        // SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelButtonToDisappear();

    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that search returns non-empty results for valid query")
    @Step("Checking non-empty search results")
    @DisplayName("Non-empty search test")
    @Features({@Feature("Search"), @Feature("Results Count")})
    public void testAmountOfNotEmptySearch()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        String search_line = "Linkin Park albums discography";
        SearchPageObject.typeSearchLine(search_line);
        int amount_of_search_results = SearchPageObject.getAmountOfFoundArticles();

        Assert.assertTrue(
                "We found too few results",
                amount_of_search_results > 0);

    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that empty search query shows no results")
    @Step("Checking empty search results")
    @DisplayName("Empty search test")
    @Features({@Feature("Search"), @Feature("Empty Results")})
    public void testAmountEmptySearch()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        String search_line = "zxcvasdfqwer";
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.waitForEmptyResultsLabel();
        SearchPageObject.assertThereIsNoResultOfSearch();


    }
    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that search results can be cancelled and disappear")
    @Step("Searching and cancelling results")
    @DisplayName("Search and cancel test")
    @Features({@Feature("Search"), @Feature("Search Cancellation")})
    public void testSearchAndCancel()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        int searchResults = SearchPageObject.SeveralArticlesWereFound("Java");

        Assert.assertTrue(
                "Expected at least 2 search results, but found only " + searchResults,
                searchResults >= 3
        );
        SearchPageObject.clickCancelsTheSearch();
        SearchPageObject.TheSearchResulIsMissing();
    }
    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that search results have correct titles and descriptions")
    @Step("Verifying search results by title and description")
    @DisplayName("Search by title and description test")
    @Features({@Feature("Search"), @Feature("Results Validation")})
    public void testSearchByTitleAndDescription() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        try {
            Thread.sleep(1000); // небольшая пауза
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Java");
        SearchPageObject.waitForElementByTitleAndDescription("Java", "Region and island in Indonesia");
        SearchPageObject.waitForElementByTitleAndDescription("JavaScript", "High-level programming language");
        SearchPageObject.waitForElementByTitleAndDescription("Java (programming language)", "Object-oriented programming language");

        int articlesCount = SearchPageObject.getAmountOfFoundArticles();
        Assert.assertTrue(
                "Expected at least 3 search results for 'Java', but found " + articlesCount,
                articlesCount >= 3
        );
    }
}
