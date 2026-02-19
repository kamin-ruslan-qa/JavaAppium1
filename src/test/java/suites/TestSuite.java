package suites;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import tests.ArticleTest;
import tests.ChangeAppConditionTests;
import tests.MyListsTest;
import tests.SearchTests;


@RunWith(Suite.class)
@Suite.SuiteClasses({
        ArticleTest.class,
        ChangeAppConditionTests.class,
        MyListsTest.class,
        SearchTests.class
})
public class TestSuite {}
