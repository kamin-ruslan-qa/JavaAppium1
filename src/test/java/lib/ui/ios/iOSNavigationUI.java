package lib.ui.ios;

import lib.ui.NavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class iOSNavigationUI extends NavigationUI {
    static
    {
        MY_LIST_LINK = "xpath://XCUIElementTypeButton[@name='Saved']";
        CLOSE_ARTICLE_BUTTON = "xpath://XCUIElementTypeButton[@name='Close']";
        SAVED_LINK = "xpath://XCUIElementTypeButton[@name='Saved']";
    }
    public iOSNavigationUI(RemoteWebDriver driver) {
        super(driver);
    }
}
