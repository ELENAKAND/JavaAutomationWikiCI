package main.java.lib.ui.factories;

import io.appium.java_client.AppiumDriver;
import main.java.lib.Platform;
import main.java.lib.ui.SearchPageObject;
import main.java.lib.ui.android.AndroidSearchPageObject;
import main.java.lib.ui.ios.IOSSearchPageObject;

public class SearchPageObjectFactory {    //use wherever we need to choose platform
    public static SearchPageObject get(AppiumDriver driver) {
        if (Platform.getInstance().isAndroid()){
            return new AndroidSearchPageObject(driver);
        } else {
            return new IOSSearchPageObject(driver);
        }
    }
}
