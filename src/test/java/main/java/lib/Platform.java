package main.java.lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.apache.commons.exec.OS;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import sun.plugin2.gluegen.runtime.CPU;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Platform {
    private static final String PLATFORM_IOS = "ios";
    private static final String PLATFORM_ANDROID = "android";
    private static final String PLATFORM_MOBILE_WEB = "mobile_web";
    private static final String APPIUM_URL = "http://127.0.0.1:4723/wd/hub";
    private static Platform instance;    //create Singleton
    private Platform(){}     //create private constructor
    public static Platform getInstance(){  //method getInstance for invoke Platform
        if (instance == null){
            instance = new Platform();
        }
        return instance;
    }
    public RemoteWebDriver getDriver() throws Exception{
        URL URL = new URL(APPIUM_URL);
        if (this.isAndroid()){
            return new AndroidDriver(URL, this.getAndroidDesiredCapabilities());
        } else if (this.isIOS()){
            return new IOSDriver(URL, this.getIOSDesiredCapabilities());
        } else if (this.isMW()){
            return new ChromeDriver(this.getMWChromeOptions());
        } else throw new Exception("Cannot detect Driver type. Platform value "+ this.getPlatformVar());
    }
    public boolean isAndroid(){
        return isPlatform(PLATFORM_ANDROID);
    }
    public boolean isIOS(){
        return isPlatform(PLATFORM_IOS);
    }
    public boolean isMW(){
        return isPlatform(PLATFORM_MOBILE_WEB);
    }
    private DesiredCapabilities getAndroidDesiredCapabilities(){
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "9");
        capabilities.setCapability("automationName", "UiAutomator2");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "/Users/elenakandaurova/Desktop/JavaAppAuto/JavaAuto/apks/org.wikipedia.apk");
        return capabilities;
    }
    private DesiredCapabilities getIOSDesiredCapabilities(){
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("deviceName", "iPhone 14 Plus Simulator");
        capabilities.setCapability("platformVersion", "16.4");
        capabilities.setCapability("udid", "F0F248BE-F535-4BC8-8191-F36AFA7247EC");
        capabilities.setCapability("app", "/Users/elenakandaurova/Desktop/JavaAppAuto/JavaAuto/apks/Wikipedia.app");
        capabilities.setCapability("automationName", "XCUITest");
        return capabilities;
    }
    private ChromeOptions getMWChromeOptions(){
        Map<String, Object> deviceMetrics = new HashMap<String, Object>();
        deviceMetrics.put("width", 360);
        deviceMetrics.put("height", 640);
        deviceMetrics.put("pixelRatio", 3.0);

        Map<String, Object> mobileEmulation = new HashMap<String, Object>();
        mobileEmulation.put("deviceMetrics", deviceMetrics);
        mobileEmulation.put("userAgent", "Mozilla/5.0 (Linux; Android 4.2.1; en-us; Nexus 5 Build/JOP40D) \n" +
                "\n" +
                "AppleWebKit/535.19 (KHTML, like Gecko) \n" +
                "\n" +
                "Chrome/18.0.1025.166 Mobile Safari/535.19");

        ChromeOptions chromeOptions = new ChromeOptions();
     //   chromeOptions.setBinary("/Applications/Google Chrome.app/Contents/MacOS/Google Chrome");
        chromeOptions.addArguments("window-size=360,640");
        chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
        return chromeOptions;
    }
    private boolean isPlatform(String my_platform){
        String platform = this.getPlatformVar();
        return my_platform.equals(platform);
    }
    public String getPlatformVar(){
        return System.getenv("PLATFORM");
    }
}
