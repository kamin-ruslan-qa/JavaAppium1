package lib.ui;

import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class MyListPageObject extends lib.ui.MainPageObject {
    public static String
            NOT_NOW_BUTTON,
            FOLDER_BY_NAME_TPL,
            ARTICLE_BY_TITLE_TPL,
            REMOVE_FROM_SAVED_BUTTON;

    public static String getFolderXpathByName(String name_of_folder)
    {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", name_of_folder);
    }
    private static String getSaveArticleXpathByTitle(String article_title)
    {
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", article_title);
    }
    private static String getRemoveButtonByTitle(String article_title)
    {
        return REMOVE_FROM_SAVED_BUTTON.replace("{TITLE}", article_title);
    }

   public MyListPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    public void clickNotNow() {
        this.waitForElementAndClick(
                NOT_NOW_BUTTON,
                "Cannot find 'Not now' button",
                5
        );
    }
    public void openFolderByName(String name_of_folder) {
        String folder_name_xpath = getFolderXpathByName(name_of_folder);
        this.waitForElementAndClick(
                folder_name_xpath,
                "Cannot find folder by name " + name_of_folder,
                15
        );
    }
    public void waitForArticleToAppearByTitle(String article_title) {
        String article_xpath = getSaveArticleXpathByTitle(article_title);
        this.waitForElementPresent(
                article_xpath,
                "Cannot find saved article by title " + article_title,
                15
        );
    }
    public void waitForArticleToDisappearByTitle(String article_title) {
        String article_xpath = getSaveArticleXpathByTitle(article_title);
        this.waitForElementNotPresent(
                article_xpath,
                "Saved article is still present with title " + article_title,
                15
        );
    }
    public void swipeArticleToDelete(String article_title) {
        this.waitForArticleToAppearByTitle(article_title);
        String article_xpath = getSaveArticleXpathByTitle(article_title);

        if (Platform.getInstance().isIOS() || Platform.getInstance().isAndroid()) {
        this.swipeElementToLeft(
                article_xpath,
                "Cannot find saved article to delete"
            );
        } else {
            String remove_locator = getRemoveButtonByTitle(article_title);
            this.waitForElementAndClick(
                remove_locator,
                "Cannot click button to remove article from saved ",
                10
            );
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

if (Platform.getInstance().isMW()) {
    driver.navigate().refresh();
}
        this.waitForArticleToDisappearByTitle(article_title);
    }
    public void clickArticle(String article_title)
    {
        String articleXpath = getSaveArticleXpathByTitle(article_title);

        this.waitForElementAndClick(
                articleXpath,
                "Cannot find article by title " + article_title,
                5
        );

    }

}
