package lib.ui.android;

import lib.ui.MyListPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidMyListPageObject extends MyListPageObject
{
     static {
            NOT_NOW_BUTTON = "xpath://android.widget.Button[@resource-id='org.wikipedia:id/negativeButton'][@text='Not now']";
            FOLDER_BY_NAME_TPL = "xpath://*[@text='{FOLDER_NAME}']";
            ARTICLE_BY_TITLE_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_title' and @text='{TITLE}']";
}
public  AndroidMyListPageObject(RemoteWebDriver driver) {
    super(driver);
}
}