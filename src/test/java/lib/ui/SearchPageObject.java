package lib.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.w3c.dom.html.HTMLInputElement;

import java.util.List;

abstract public class SearchPageObject extends lib.ui.MainPageObject {
    protected static String
            SEARCH_INIT_ELEMENT,
            SEARCH_INPUT,
            SEARCH_CANCEL_BUTTON,
            SEARCH_RESULT_BY_SUBSTRING_TPL,
            SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION_TPL,
            SEARCH_RESULT_ELEMENT,
            SEARCH_EMPTY_RESULT_ELEMENT,
            SEARCH_RESULTS_LIST,
            SEARCH_RESULTS_ITEM,
            SEARCH_CANCEL_BUTTON_THE_CROSS,
            SEARCH_CLICK;

    public SearchPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    /* TemplateS method */
    public static String getSearchResult(String substring) {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{substring}", substring);
    }

    public static String getSearchResultByTitleAndDescription(String title, String description) {
        return SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION_TPL.replace("{TITLE}", title).replace("{DESCRIPTION}", description);
    }
    /* TemplateS method */

    public void initSearchInput() {

        this.waitForElementPresent(
                SEARCH_INIT_ELEMENT,
                "Cannot find search input after clicking search init element",
                30
        );
        this.waitForElementAndClick(
                SEARCH_INIT_ELEMENT,
                "Cannot find and click search init element",
                20
        );
    }

    public void waitForCancelButtonToAppear() {
        this.waitForElementPresent(
                SEARCH_CANCEL_BUTTON,
                "Cannot find search cancel button",
                5
        );
    }

    public void waitForCancelButtonToDisappear() {
        this.waitForElementNotPresent(
                SEARCH_CANCEL_BUTTON,
                "Search cancel button is still present",
                5
        );
    }

    public void clickCancelSearch() {
        this.waitForElementAndClick(
                SEARCH_CANCEL_BUTTON,
                "Cannot find and click search cancel button",
                5
        );
    }

    public void typeSearchLine(String search_line) {
        this.waitForElementAndSendKeys(
                SEARCH_INPUT,
                search_line,
                "Cannot find and type into search input",
                20
        );
    }
    public void waitForSearchResult(String substring) {
        String search_result_xpath = getSearchResult(substring);
        this.waitForElementPresent(
                search_result_xpath,
                "Cannot find search result with substring" + substring
        );
    }

    public void clickByArticleWithSubstring(String substring) {
        String search_result_xpath = getSearchResult(substring);
        this.waitForElementAndClick(
                search_result_xpath,
                "Cannot find and click search result with substring" + substring,
                10
        );
    }

    public int getAmountOfFoundArticles() {
        this.waitForElementPresent(
                SEARCH_RESULT_ELEMENT,
                "Cannot find anything by the request ",
                15
        );
        return this.getAmountOfElements(SEARCH_RESULT_ELEMENT);
    }

    public void waitForEmptyResultsLabel() {
        this.waitForElementPresent(
                SEARCH_EMPTY_RESULT_ELEMENT,
                "Cannot find empty result element",
                15
        );
    }

    public void assertThereIsNoResultOfSearch() {
        this.assertElementNotPresent(
                SEARCH_RESULT_ELEMENT,
                "We supposed not to find  any results",
                15
        );

    }

    public int SeveralArticlesWereFound(String search_results) {
        this.waitForElementPresent(
                SEARCH_RESULTS_LIST,
                "Cannot find results list",
                30
        );
        By itemLocator = this.getLocatorByString(SEARCH_RESULTS_ITEM);
        List<WebElement> searchResults = driver.findElements(itemLocator);
        return searchResults.size();
    }

    public void clickCancelsTheSearch() {
        this.waitForElementAndClick(
                SEARCH_CANCEL_BUTTON_THE_CROSS,
                "Cannot find X to cancel search",
                5
        );
    }

    public void TheSearchResulIsMissing() {
        this.waitForElementNotPresent(
                SEARCH_RESULTS_LIST,
                "Search results are still present after canceling search",
                5
        );
    }

    public void waitForElementByTitleAndDescription(String title, String description) {
        String search_result_xpath = SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION_TPL.replace("{TITLE}", title).replace("{DESCRIPTION}", description);
        this.waitForElementPresent(
                search_result_xpath,
                "Cannot find article with title '" + title + "' and description '" + description + "'",
                15
        );
    }
}
