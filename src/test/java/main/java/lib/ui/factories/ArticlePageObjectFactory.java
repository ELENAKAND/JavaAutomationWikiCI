package main.java.lib.ui.factories;

import io.appium.java_client.AppiumDriver;
import main.java.lib.Platform;
import main.java.lib.ui.ArticlePageObject;
import main.java.lib.ui.android.AndroidArticlePageObject;
import main.java.lib.ui.ios.IOSArticlePageObject;

public class ArticlePageObjectFactory {
    public static ArticlePageObject get(AppiumDriver driver){
        if (Platform.getInstance().isAndroid()){
            return new AndroidArticlePageObject(driver);
        } else {
            return new IOSArticlePageObject(driver);
        }
    }
}
