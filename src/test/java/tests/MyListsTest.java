package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class MyListsTest extends CoreTestCase {
    private static final String name_of_folder = "Learning programming";
    private static final String
            login = "Ruslan4uk",
            password = "Ha367!!Ti2022";

    @Test
    public void testSaveFirstToMyList() {
        {
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
            ArticlePageObject.waitForTitleElement();
            String article_title = ArticlePageObject.getArticleTitle();

            if (Platform.getInstance().isAndroid()) {
                ArticlePageObject.addArticleToMyList(name_of_folder);


            } else {
                ArticlePageObject.addArticleToMySaved();
            }

            if (Platform.getInstance().isMW()) {
                AuthorizationPageObject Auth = new AuthorizationPageObject(driver);

                Auth.clickAuthButton();
                Auth.enterLoginData(login, password);
                Auth.submitForm();

                ArticlePageObject.waitForTitleElement();

                assertEquals("We are not on the same page after login.",
                        article_title,
                        ArticlePageObject.getArticleTitle()
                );
                ArticlePageObject.addArticleToMySaved();
            }
            NavigationUI NavigationUI = NavigationUIFactory.get(driver);
            NavigationUI.OpenNavigation();
            NavigationUI.clickMyList();
            NavigationUI.closeArticle();

            MyListPageObject MyListPageObject = MyListPageObjectFactory.get(driver);
            if (Platform.getInstance().isAndroid()) {
                MyListPageObject.openFolderByName(name_of_folder);
            }
            MyListPageObject.swipeArticleToDelete(article_title);

        }
    }

    @Test
    public void testSaveFirstArticleToMyList() {
        String name_of_folder;
        {
            SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

//   Сохраняем статью Java
            SearchPageObject.initSearchInput();
            SearchPageObject.typeSearchLine("Java");
            SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

            ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
            ArticlePageObject.waitForTitleElement();
            String article_title = ArticlePageObject.getArticleTitle();
            name_of_folder = "Learning programming";
            ArticlePageObject.addArticleToMyList(name_of_folder);

            NavigationUI NavigationUI = NavigationUIFactory.get(driver);
            NavigationUI.clickMyList();

            SearchPageObject.clickCancelsTheSearch();

            //   Сохраняем статью Appium
            SearchPageObject.initSearchInput();
            SearchPageObject.typeSearchLine("Appium");
            SearchPageObject.clickByArticleWithSubstring("Appium");

            ArticlePageObject.waitForTitleElement();
            ArticlePageObject.addArticleToExistingList(name_of_folder);

            //Переходим в раздел "Saved"
            NavigationUI.clickMyList();
            NavigationUI.clickMyList();
            NavigationUI.clickSaved();

            //Удаляем статью Appium
            MyListPageObject MyListPageObject = MyListPageObjectFactory.get(driver);
            MyListPageObject.clickNotNow();
            MyListPageObject.openFolderByName(name_of_folder);
            MyListPageObject.swipeArticleToDelete("Appium");
            MyListPageObject.waitForArticleToAppearByTitle(article_title);
            MyListPageObject.clickArticle("Java (programming language)");

            //Переходим в неё и убеждаемся, что title совпадает
            ArticlePageObject.waitForTitleElement();
        }
    }
}
