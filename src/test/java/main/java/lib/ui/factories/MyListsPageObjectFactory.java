package main.java.lib.ui.factories;

import io.appium.java_client.AppiumDriver;
import main.java.lib.Platform;
import main.java.lib.ui.MyListsPageObject;
import main.java.lib.ui.android.AndroidMyListsPageObject;
import main.java.lib.ui.ios.IOSMyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MyListsPageObjectFactory {
    public static MyListsPageObject get(RemoteWebDriver driver){
        if (Platform.getInstance().isAndroid()){
            return new AndroidMyListsPageObject((AppiumDriver) driver);
        } else {
            return new IOSMyListsPageObject((AppiumDriver) driver);
        }
    }
}
