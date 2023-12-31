package main.java.lib.ui.factories;

import io.appium.java_client.AppiumDriver;
import main.java.lib.Platform;
import main.java.lib.ui.NavigationUI;
import main.java.lib.ui.android.AndroidNavigationUI;
import main.java.lib.ui.ios.IOSNavigationUI;
import main.java.lib.ui.mobile_web.MWNavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class NavigationUIFactory {
    public static NavigationUI get(RemoteWebDriver driver) {
        if (Platform.getInstance().isAndroid()) {
            return new AndroidNavigationUI(driver);
        } else if (Platform.getInstance().isIOS()) {
            return new IOSNavigationUI(driver);
        } else {
            return new MWNavigationUI(driver);
        }
    }
}

