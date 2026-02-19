package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;

public class AndroidArticlePageObject extends ArticlePageObject
{
    static
    {
            TITLE_JAVA = "xpath://android.view.View[@content-desc='Java (programming language)']";
            TITLE_APP = "xpath://android.view.View[@content-desc='Appium']";
            FOOTER_ELEMENT = "xpath://android.view.View[@content-desc='View article in browser']";
            SAVE_BUTTON = "id:org.wikipedia:id/page_save";
            OPTIONS_ADD_TO_MY_LIST_BUTTON = "xpath://android.widget.Button[@resource-id='org.wikipedia:id/snackbar_action'][@text='Add to list']";
            MY_LIST_NAME_INPUT = "id:org.wikipedia:id/text_input";
            MY_LIST_OK_BUTTON = "xpath://*[@text='OK']";
//            APPIUM_ARTICLE_TITLE = "xpath://android.view.View[@content-desc='Appium']";
}
public  AndroidArticlePageObject(AppiumDriver driver)
{
    super(driver);
}
}
