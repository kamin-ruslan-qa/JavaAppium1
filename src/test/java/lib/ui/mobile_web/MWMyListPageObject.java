package lib.ui.mobile_web;

import lib.ui.MyListPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWMyListPageObject extends MyListPageObject
    {
        static {
        NOT_NOW_BUTTON = "xpath://android.widget.Button[@resource-id='org.wikipedia:id/negativeButton'][@text='Not now']";
        FOLDER_BY_NAME_TPL = "xpath://*[@text='{FOLDER_NAME}']";
        ARTICLE_BY_TITLE_TPL = "xpath://ul[contains(@class,'watchlist')]//h3[contains(text(),'{TITLE}')]";
        REMOVE_FROM_SAVED_BUTTON = "xpath://li[contains(@title, '{TITLE}')]//a[contains(@class, 'watched')]";
    }
public MWMyListPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}