package lib.ui.mobile_web;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWArticlePageObject extends ArticlePageObject
{
    static
    {
        TITLE_JAVA = "css:h1#firstHeading";
        TITLE_APP = "css:[name=mw-page-title-main]";
        FOOTER_ELEMENT = "css:footer";
//        SAVE_BUTTON = "id:org.wikipedia:id/page_save";
        OPTIONS_ADD_TO_MY_LIST_BUTTON = "css:#page-actions-watch";
        OPTIONS_REMOVE_FROM_MY_LIST_BUTTON = "css:#page-actions-watch.watched";
//        MY_LIST_NAME_INPUT = "id:org.wikipedia:id/text_input";
//        MY_LIST_OK_BUTTON = "xpath://*[@text='OK']";
    }
    public  MWArticlePageObject(RemoteWebDriver driver)
    {
        super(driver);
    }
}
