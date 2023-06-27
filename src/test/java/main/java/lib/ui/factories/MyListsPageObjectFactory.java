package main.java.lib.ui.factories;

import io.appium.java_client.AppiumDriver;
import main.java.lib.Platform;
import main.java.lib.ui.MyListsPageObject;
import main.java.lib.ui.android.AndroidMyListsPageObject;
import main.java.lib.ui.ios.IOSMyListsPageObject;

public class MyListsPageObjectFactory {
    public static MyListsPageObject get(AppiumDriver driver){
        if (Platform.getInstance().isAndroid()){
            return new AndroidMyListsPageObject(driver);
        } else {
            return new IOSMyListsPageObject(driver);
        }
    }
}
