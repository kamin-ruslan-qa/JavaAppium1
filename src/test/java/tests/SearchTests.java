package tests;

import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class SearchTests extends CoreTestCase
{
    @Test
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
    public void testCancelSearch() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        // SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelButtonToDisappear();

    }
    @Test
    public void testAmountOfNotEmptySearch()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        String search_line = "Linkin Park albums discography";
        SearchPageObject.typeSearchLine(search_line);
        int amount_of_search_results = SearchPageObject.getAmountOfFoundArticles();

        assertTrue(
                "We found too few results",
                amount_of_search_results > 0);

    }

    @Test
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
    public void testSearchAndCancel()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        int searchResults = SearchPageObject.SeveralArticlesWereFound("Java");

        assertTrue(
                "Expected at least 2 search results, but found only " + searchResults,
                searchResults >= 3
        );
        SearchPageObject.clickCancelsTheSearch();
        SearchPageObject.TheSearchResulIsMissing();
    }
    @Test
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
        assertTrue(
                "Expected at least 3 search results for 'Java', but found " + articlesCount,
                articlesCount >= 3
        );
    }
}
