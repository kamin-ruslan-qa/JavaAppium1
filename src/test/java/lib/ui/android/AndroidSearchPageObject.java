package lib.ui.android;

import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidSearchPageObject extends SearchPageObject {

static {
    SEARCH_INIT_ELEMENT = "xpath://*[@text='Search Wikipedia']";
    SEARCH_INPUT = "xpath://*[@resource-id='org.wikipedia:id/search_src_text']";
    SEARCH_CANCEL_BUTTON = "xpath://android.widget.ImageButton[@content-desc='Navigate up']";
    SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://*[@resource-id='org.wikipedia:id/search_results_list']//*[contains(@text,'{substring}')]";
    SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION_TPL =
            "xpath://*[@resource-id='org.wikipedia:id/search_results_list']" +
                    "/android.view.ViewGroup" +
                    "[.//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='{TITLE}']" +
                    " and " +
                    ".//*[@resource-id='org.wikipedia:id/page_list_item_description'][@text='{DESCRIPTION}']]";
    SEARCH_RESULT_ELEMENT = "xpath://*[@resource-id='org.wikipedia:id/search_results_list']//*[@resource-id='org.wikipedia:id/page_list_item_title']";
    SEARCH_EMPTY_RESULT_ELEMENT = "xpath://*[@text='No results']";
    SEARCH_RESULTS_LIST = "id:org.wikipedia:id/search_results_list";
    SEARCH_RESULTS_ITEM = "xpath://*[@resource-id='org.wikipedia:id/search_results_list']/android.view.ViewGroup";
    SEARCH_CANCEL_BUTTON_THE_CROSS = "id:org.wikipedia:id/search_close_btn";
}

    public AndroidSearchPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
