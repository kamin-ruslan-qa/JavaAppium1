package lib.ui.mobile_web;

import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWSearchPageObject extends SearchPageObject {

    static {
        SEARCH_INIT_ELEMENT = "css:button#searchIcon";
        SEARCH_INPUT = "css:input.cdx-text-input__input";
        SEARCH_CANCEL_BUTTON = "css:button[aria-label='Close search dialog']";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://li[contains(@class,'cdx-menu-item')]//*[contains(text(),'{substring}')]";
        SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION_TPL =
                "xpath://li[contains(@class,'cdx-menu-item') and contains(@title,'{TITLE}')]" +
                        "//span[contains(@class,'cdx-menu-item__text__description') and contains(.,'{DESCRIPTION}')]";
        SEARCH_RESULT_ELEMENT = "css:li[class='cdx-menu-item cdx-menu-item--enabled cdx-menu-item--bold-label cdx-menu-item--has-description cdx-menu-item--hide-description-overflow']";
        SEARCH_EMPTY_RESULT_ELEMENT = "css:span.cdx-menu-item__text.cdx-typeahead-search__search-footer__text";
        SEARCH_RESULTS_LIST = "id:org.wikipedia:id/search_results_list";
        SEARCH_RESULTS_ITEM = "xpath://*[@resource-id='org.wikipedia:id/search_results_list']/android.view.ViewGroup";
        SEARCH_CANCEL_BUTTON_THE_CROSS = "id:org.wikipedia:id/search_close_btn";

    }

    public MWSearchPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }
}