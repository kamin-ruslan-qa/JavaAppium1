package lib.ui.android;
import lib.ui.NavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidNavigationUI extends NavigationUI
{
    static
    {
        MY_LIST_LINK = "xpath://android.widget.ImageButton[@content-desc='Navigate up']";
        CLOSE_ARTICLE_BUTTON = "xpath://android.widget.ImageButton[@content-desc='Navigate up']";
        SAVED_LINK = "xpath://android.widget.FrameLayout[@content-desc='Saved']";
    }
    public AndroidNavigationUI(RemoteWebDriver driver) {
        super(driver);
    }
}
