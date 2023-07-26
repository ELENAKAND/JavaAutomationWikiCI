package main.java.tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import main.java.lib.CoreTestCase;
import main.java.lib.Platform;
import main.java.lib.ui.WelcomePageObject;
import org.junit.Test;

@Epic("Tests for passing welcome pages")
public class GetStartedTest extends CoreTestCase {
    @Test
    @Features(value = {@Feature(value = "Search")})
    @DisplayName("Test passing iOS welcome page")
    @Severity(value = SeverityLevel.MINOR)
    public void testPassThroughWelcome(){     //always failed over the skipWelcomePageForIOSApp in CoreTestCase
        if (Platform.getInstance().isAndroid() || Platform.getInstance().isMW()){
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
