package lib.ui.mobile_web;

import lib.ui.NavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWNavigationUI extends NavigationUI
{
    static
    {
        MY_LIST_LINK = "css:a[data-event-name='menu.watchlist']";
        CLOSE_ARTICLE_BUTTON = "css:button.cancel";
        SAVED_LINK = "css:a[data-event-name='menu.watchlist']";
        OPEN_NAVIGATION = "css:input#main-menu-input";
    }
    public MWNavigationUI(RemoteWebDriver driver) {
        super(driver);
    }
}
