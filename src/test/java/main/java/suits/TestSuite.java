package main.java.suits;

import main.java.tests.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ArticleTests.class,
        ChangeAppConditionTests.class,
     //   GetStartedTest.class,
        MyListsTests.class,
        SearchTests.class
})
public class TestSuite {}
