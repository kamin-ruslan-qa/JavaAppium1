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

            SearchPageObject.initSearchInput();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            SearchPageObject.typeSearchLine("Java");
            SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

            ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
            ArticlePageObject.waitForTitleElement();
            name_of_folder = "Learning programming";
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

                ArticlePageObject.addArticleToMySaved();

                // Сохраняем статью Appium
                SearchPageObject.initSearchInput();
                try {
                    Thread.sleep(1000); // небольшая пауза
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                SearchPageObject.typeSearchLine("Appium");
                SearchPageObject.clickByArticleWithSubstring("Automation for Apps");

                ArticlePageObject.waitForTitleElement();

                if (Platform.getInstance().isAndroid()) {

                    ArticlePageObject.addArticleToMyList(name_of_folder);

                } else {
                    ArticlePageObject.addArticleToMySaved();

                    NavigationUI NavigationUI = NavigationUIFactory.get(driver);
                    NavigationUI.OpenNavigation();
                    NavigationUI.clickMyList();
                    NavigationUI.closeArticle();

                    //Удаляем статью Java
                    MyListPageObject MyListPageObject = MyListPageObjectFactory.get(driver);

                    if (Platform.getInstance().isAndroid()) {
                        MyListPageObject.clickNotNow();
                        MyListPageObject.openFolderByName(name_of_folder);
                        MyListPageObject.swipeArticleToDelete("Appium");
                        MyListPageObject.waitForArticleToAppearByTitle(article_title);
                    }

                    MyListPageObject.swipeArticleToDelete("Appium");


                    //Переходим в неё и убеждаемся, что URL совпадает
                        MyListPageObject.clickArticle("Java (programming language)");

                        ArticlePageObject.verifyUrlMatches();
                        ArticlePageObject.waitForTitleElement();


                }
            }
        }
    }
}