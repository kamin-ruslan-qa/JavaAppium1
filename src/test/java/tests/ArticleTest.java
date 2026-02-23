package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.MyListPageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListPageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;


@Epic("Tests for articles")
public class ArticleTest extends CoreTestCase
{
    @Test
    @Features(value = {@Feature(value="Search"),@Feature(value = "Article")})
    @DisplayName("Compare article title with expected one")
    @Description("We open 'Java Object-oriented programming language' article and make sure the title is expected")
    @Step("Starting test testCompareArticleTitle")
    @Severity(value = SeverityLevel.BLOCKER)
    public void testCompareArticleTitle() {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        try {
            Thread.sleep(1000); // небольшая пауза
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        String article_title = ArticlePageObject.getArticleTitle();

//        ArticlePageObject.takeScreenshot("article_page");

        Assert.assertEquals(
                "We see unexpected title!",
                "Java (programming language)",
                article_title
        );
    }

    @Test
    @Features(value = {@Feature(value="Search"),@Feature(value = "Article")})
    @DisplayName("Swipe article to the footer")
    @Description("We open an article and swipe it to the footer")
    @Step("Starting test testSwipeArticle")
    @Severity(value = SeverityLevel.MINOR)
    public void testSwipeArticle() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        try {
            Thread.sleep(1000); // небольшая пауза
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        SearchPageObject.typeSearchLine("Appium");
        SearchPageObject.clickByArticleWithSubstring("Appium");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();
        ArticlePageObject.swipeToFooter();
    }
}
//    @Test
//    // Задание:6
//    public void testArticleHasTitle()
//    {
//        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
//        SearchPageObject.initSearchInput();
//        try {
//            Thread.sleep(1000); // небольшая пауза
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        SearchPageObject.typeSearchLine("Appium");
//
//        MyListPageObject MyListPageObject = MyListPageObjectFactory.get(driver);
//
//        MyListPageObject.clickArticle("Appium");
//
//        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
//        ArticlePageObject.waitForTitleElement();
//    }
//}
