package main.java.lib;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import junit.framework.TestCase;
import main.java.lib.ui.WelcomePageObject;
import main.java.lib.ui.WelcomePageObjectAndroid;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.FileOutputStream;
import java.time.Duration;
import java.util.Properties;


public class CoreTestCase {
    protected RemoteWebDriver driver;


    @Before
    @Step("Run driver and session")
    public void setUp() throws Exception {
        driver = Platform.getInstance().getDriver();
        this.createAllurePropertyFile();
        this.rotateScreenPortrait();
        this.skipWelcomePageForIOSApp();
        this.skipWelcomePageForAndroid();
        this.openWikiWebPageForMobileWeb();
      //  WebElement element = driver.findElementById("org.wikipedia:id/fragment_onboarding_skip_button");
      //  element.click();
    }

    @After
    @Step("Remove driver and session")
    public void tearDown(){
        driver.quit();
    }

    @Step("Rotate screen to portrait mode")
    protected void rotateScreenPortrait() {
        if (driver instanceof AppiumDriver){
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.rotate(ScreenOrientation.PORTRAIT);
        } else {
            System.out.println("Method rotateScreenPortrait does nothing for "+Platform.getInstance().getPlatformVar());
        }

    }
    @Step("Rotate screen to landscape mode")
    protected void rotateScreenLandscape() {
        if (driver instanceof AppiumDriver){
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.rotate(ScreenOrientation.LANDSCAPE);
        } else {
            System.out.println("Method rotateScreenLandscape does nothing for "+Platform.getInstance().getPlatformVar());
        }
    }
    @Step("Send mobile app to background (does nothing for Mobile Web)")
    protected void backgroundApp(Duration seconds) {
        if (driver instanceof AppiumDriver){
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.runAppInBackground(seconds);
        } else {
            System.out.println("Method backgroundApp does nothing for "+Platform.getInstance().getPlatformVar());
        }
    }
    @Step("Send mobile app to background (does nothing for Android and iOS)")
    protected void openWikiWebPageForMobileWeb(){
        if (Platform.getInstance().isMW()){
            driver.get("https://en.m.wikipedia.org/wiki/Main_Page");
        } else {
            System.out.println("Method openWikiWebPageForMobileWeb does nothing for "+Platform.getInstance().getPlatformVar());

        }
    }
    @Step("Skip welcome page for iOS")
    private void skipWelcomePageForIOSApp(){
        if (Platform.getInstance().isIOS()){
            AppiumDriver driver = (AppiumDriver) this.driver;
            WelcomePageObject WelcomePageObject = new WelcomePageObject(driver);
            WelcomePageObject.clickSkip();
        }
    }
    @Step("Skip welcome page for Android")
    private void skipWelcomePageForAndroid(){
        if (Platform.getInstance().isAndroid()){
            AppiumDriver driver = (AppiumDriver) this.driver;
            WelcomePageObjectAndroid WelcomePageObjectAndroid = new WelcomePageObjectAndroid(driver);
            WelcomePageObjectAndroid.clickSkipButtonAndroid();
        }
    }
    private void createAllurePropertyFile(){
        String path = System.getProperty("allure.results.directory");
        try{
            Properties props = new Properties();
            FileOutputStream fos = new FileOutputStream(path+"/environment.properties");
            props.setProperty("Environment", Platform.getInstance().getPlatformVar());
            props.store(fos, "See https://docs.qameta.io/allure/#_environment");
            fos.close();
        } catch (Exception e){
            System.err.println("IO problem when writing Allure properties file");
            e.printStackTrace();
        }
    }
}

