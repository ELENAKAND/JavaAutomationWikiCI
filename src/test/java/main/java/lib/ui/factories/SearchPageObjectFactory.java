package main.java.lib.ui.factories;

import io.appium.java_client.AppiumDriver;
import main.java.lib.Platform;
import main.java.lib.ui.SearchPageObject;
import main.java.lib.ui.android.AndroidSearchPageObject;
import main.java.lib.ui.ios.IOSSearchPageObject;
import main.java.lib.ui.mobile_web.MWSearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class SearchPageObjectFactory {    //use wherever we need to choose platform
    public static SearchPageObject get(RemoteWebDriver driver) {
        if (Platform.getInstance().isAndroid()){
            return new AndroidSearchPageObject((AppiumDriver) driver);
        } else if (Platform.getInstance().isIOS()){
            return new IOSSearchPageObject((AppiumDriver) driver);
        } else {
            return new MWSearchPageObject(driver);
        }
    }
}
