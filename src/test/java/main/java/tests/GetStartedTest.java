package tests;

import main.java.lib.CoreTestCase;
import main.java.lib.Platform;
import main.java.lib.ui.WelcomePageObject;
import org.junit.Test;

public class GetStartedTest extends CoreTestCase {
    @Test
    public void testPassThroughWelcome(){     //always failed over the skipWelcomePageForIOSApp in CoreTestCase
        if (Platform.getInstance().isAndroid()) {
            return;
        }
        WelcomePageObject WelcomePageObject = new WelcomePageObject(driver);

        WelcomePageObject.waitForMainPage();
        WelcomePageObject.clickNextButton();

        WelcomePageObject.waitForNewWaysToExploreText();
        WelcomePageObject.clickNextButton();

        WelcomePageObject.waitForSearchInLangText();
        WelcomePageObject.clickNextButton();

        WelcomePageObject.waitForMakeTheAppBetterText();
        WelcomePageObject.clickGetStartedButton();
    }
}
