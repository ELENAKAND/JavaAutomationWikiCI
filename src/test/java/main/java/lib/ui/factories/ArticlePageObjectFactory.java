package main.java.lib.ui.factories;

import io.appium.java_client.AppiumDriver;
import main.java.lib.Platform;
import main.java.lib.ui.ArticlePageObject;
import main.java.lib.ui.android.AndroidArticlePageObject;
import main.java.lib.ui.ios.IOSArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class ArticlePageObjectFactory {
    public static ArticlePageObject get(RemoteWebDriver driver){
        if (Platform.getInstance().isAndroid()){
            return new AndroidArticlePageObject((AppiumDriver) driver);
        } else {
            return new IOSArticlePageObject((AppiumDriver) driver);
        }
    }
}
