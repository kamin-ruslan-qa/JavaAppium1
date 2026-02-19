package lib.ui;
import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class NavigationUI extends MainPageObject {
    protected static String
            MY_LIST_LINK,
            CLOSE_ARTICLE_BUTTON,
            SAVED_LINK,
            OPEN_NAVIGATION;


    public NavigationUI(RemoteWebDriver driver) {
        super(driver);
    }

    public void OpenNavigation() {
        if (Platform.getInstance().isMW()) {
            this.waitForElementAndClick(
                    OPEN_NAVIGATION,
                    "Cannot find button to open navigation",
                    10
            );
        } else {
            System.out.println("Method OpenNavigation() does nothing for platform " + Platform.getInstance().getPlatformVar());

        }
    }

    public void clickMyList()
    {
        if (Platform.getInstance().isMW()) {
            this.tryClickElementWithFewAttempts(
                            MY_LIST_LINK,
                            "Cannot find navigation button to My list",
                            5
                    );
        } else {
                    this.waitForElementAndClick(
                            MY_LIST_LINK,
                            "Cannot find navigation button to My list",
                            5
                    );

        }
    }

    public void clickSaved() {
        this.waitForElementAndClick(
                SAVED_LINK,
                "Cannot find 'Saved' button",
                5
        );
    }

    public void closeArticle() {
        if (Platform.getInstance().isIOS() || Platform.getInstance().isAndroid()) {
        this.waitForElementAndClick(
                CLOSE_ARTICLE_BUTTON,
                "Cannot find button to close article options",
                5
            );
        } else {
            System.out.println("Method closeArticle() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }
}