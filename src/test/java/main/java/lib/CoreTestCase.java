package main.java.lib;
import io.appium.java_client.AppiumDriver;
import junit.framework.TestCase;
import main.java.lib.ui.WelcomePageObject;
import main.java.lib.ui.WelcomePageObjectAndroid;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.time.Duration;


public class CoreTestCase extends TestCase {
    protected RemoteWebDriver driver;


    @Override
    protected void setUp() throws Exception {
        super.setUp();
        driver = Platform.getInstance().getDriver();
        this.rotateScreenPortrait();
        this.skipWelcomePageForIOSApp();
        this.skipWelcomePageForAndroid();
        this.openWikiWebPageForMobileWeb();
      //  WebElement element = driver.findElementById("org.wikipedia:id/fragment_onboarding_skip_button");
      //  element.click();
    }

    @Override
    protected void tearDown() throws Exception {
        driver.quit();
        super.tearDown();
    }

    protected void rotateScreenPortrait() {
        if (driver instanceof AppiumDriver){
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.rotate(ScreenOrientation.PORTRAIT);
        } else {
            System.out.println("Method rotateScreenPortrait does nothing for "+Platform.getInstance().getPlatformVar());
        }

    }

    protected void rotateScreenLandscape() {
        if (driver instanceof AppiumDriver){
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.rotate(ScreenOrientation.LANDSCAPE);
        } else {
            System.out.println("Method rotateScreenLandscape does nothing for "+Platform.getInstance().getPlatformVar());
        }
    }

    protected void backgroundApp(Duration seconds) {
        if (driver instanceof AppiumDriver){
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.runAppInBackground(seconds);
        } else {
            System.out.println("Method backgroundApp does nothing for "+Platform.getInstance().getPlatformVar());
        }
    }

    protected void openWikiWebPageForMobileWeb(){
        if (Platform.getInstance().isMW()){
            driver.get("https://en.m.wikipedia.org/wiki/Main_Page");
        } else {
            System.out.println("Method openWikiWebPageForMobileWeb does nothing for "+Platform.getInstance().getPlatformVar());

        }
    }
    private void skipWelcomePageForIOSApp(){
        if (Platform.getInstance().isIOS()){
            AppiumDriver driver = (AppiumDriver) this.driver;
            WelcomePageObject WelcomePageObject = new WelcomePageObject(driver);
            WelcomePageObject.clickSkip();
        }
    }
    private void skipWelcomePageForAndroid(){
        if (Platform.getInstance().isAndroid()){
            AppiumDriver driver = (AppiumDriver) this.driver;
            WelcomePageObjectAndroid WelcomePageObjectAndroid = new WelcomePageObjectAndroid(driver);
            WelcomePageObjectAndroid.clickSkipButtonAndroid();
        }
    }
}

