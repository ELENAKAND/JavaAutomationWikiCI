package main.java.lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import main.java.lib.Platform;
import org.example.App;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import java.util.regex.Pattern;

public class MainPageObject {         //created for tests methods
    protected RemoteWebDriver driver;   //driver initialization
    public MainPageObject(RemoteWebDriver driver){      //class constructor the tests will access
        this.driver = driver;
    }

    public  WebElement waitForElementPresent(String locator, String error_message, long timeoutInSeconds) {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        wait.until(ExpectedConditions.and(
                ExpectedConditions.visibilityOfElementLocated(by)
            //   ExpectedConditions.presenceOfElementLocated(by)
        )); return driver.findElement(by);

    }
    public WebElement waitForElementClickable(String locator, String error_message, long timeoutInSeconds) {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.elementToBeClickable(by) //elementToBeClickable(by)
        );
    }

    public WebElement waitForElementPresent(String locator, String error_message) {
        return waitForElementPresent(locator, error_message, 5);
    }

    public WebElement waitForElementAndClick(String locator, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementClickable(locator, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    public WebElement waitForElementAndSendKeys(String locator, String value, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    public boolean waitForElementNotPresent(String locator, String error_message, long timeoutInSeconds) {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by) //invisibilityOfElementLocated(by)
        );
    }

    public WebElement waitForElementAndClear(String locator, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }

    public WebElement assertElementHasText(String locator, String error_message, String text) {
        WebElement text_element = waitForElementPresent(locator, error_message, 5);
        String field_has_text = text_element.getAttribute("text");
        Assert.assertEquals(error_message, text, field_has_text);
        return text_element;
    }

    public WebElement assertListTitlesHaveText(String locator, String error_message, String text) {
        WebElement text_element = waitForElementPresent(locator, error_message, 5);
        String field_has_text = text_element.getAttribute("text");
        Assert.assertTrue(error_message, field_has_text.contains(text));
        return text_element; 
    }
    public void swipeUp(Duration timeOfSwipe){
        if (driver instanceof AppiumDriver){
            TouchAction action = new TouchAction((AppiumDriver) driver);
            Dimension size = driver.manage().window().getSize();
            int x = size.width/2;
            int start_y = (int)(size.height*0.8);
            int end_y = (int)(size.height*0.2);
            action
                    .press(PointOption.point(x, start_y))
                    .waitAction(WaitOptions.waitOptions(timeOfSwipe))
                    .moveTo(PointOption.point(x, end_y))
                    .release()
                    .perform();
        } else {
            System.out.println("Method swipeUp does nothing for "+Platform.getInstance().getPlatformVar());

        }

    }
    public void swipeUpQuick(){
        swipeUp(Duration.ofMillis(200));
    }

    public void scrollWebPageUp(){
        if (Platform.getInstance().isMW()){
            JavascriptExecutor JSExecutor = (JavascriptExecutor) driver; //to execute JS on the current web page
            JSExecutor.executeScript("window.scrollBy(0,250)");
        } else {
            System.out.println("Method scrollWebPageUp do nothing for platform "+Platform.getInstance().getPlatformVar());
        }
    }
    public void scrollTillElementNotVisible(String locator, String error_message, int max_swipes){
        int already_swiped = 0;
        WebElement element = this.waitForElementPresent(locator, error_message);
        while (!this.isElementLocatedOnTheScreen(locator)){
            scrollWebPageUp();
            ++already_swiped;
            if (already_swiped > max_swipes){
                Assert.assertTrue(error_message, element.isDisplayed());
            }
        }
    }

    public void swipeUpToFindElement(String locator, String error_message, int max_swipes){
        By by = this.getLocatorByString(locator);
        int already_swiped = 0;
        while (driver.findElements(by).size()==0){
            if(already_swiped > max_swipes){
                waitForElementPresent(locator, "Cannot find element by swiping up.\n"+error_message, 0);
                return;
            }
            swipeUpQuick();
            ++already_swiped;
        }
    }
    public void swipeUpTillElementAppear(String locator, String error_message, int max_swipes){
        int already_swiped = 0;
        while (!this.isElementLocatedOnTheScreen(locator)){
            if (already_swiped > max_swipes){
                Assert.assertTrue(error_message, this.isElementLocatedOnTheScreen(locator));//exit the loop
            }
            swipeUpQuick();
            ++already_swiped;  //continue the loop
        }
    }
    public boolean isElementLocatedOnTheScreen(String locator){
        int element_location_by_y = this.waitForElementPresent(locator, "Cannot find element by locator", 1).getLocation().getY();
        if (Platform.getInstance().isMW()){
            JavascriptExecutor JSExecutor = (JavascriptExecutor) driver;
            Object js_result = JSExecutor.executeScript("return window.pageYOffset");
            element_location_by_y -= Integer.parseInt(js_result.toString());//the result of JS execution->to string->parse it to int->subtract it from element_location_by_y
        }
        int screen_size_by_y = driver.manage().window().getSize().getHeight();//get the length of the entire screen
        return element_location_by_y < screen_size_by_y;//while 1st>2nd by height->return false and continue swiping, when element has found->return true;
    }
    public void clickElementToTheRightUpperCorner(String locator, String error_message){
        if (driver instanceof AppiumDriver){
            WebElement element = this.waitForElementPresent(locator, error_message);//or locator+"/.." to go to the parent element on xml
            int right_x = element.getLocation().getX();
            int upper_y = element.getLocation().getY();
            int lower_y = upper_y + element.getSize().getHeight();
            int middle_y = (upper_y + lower_y)/2;
            int width = element.getSize().getWidth();
            int point_to_click_x = (right_x + width)-3;//3 pixels to the left than the width of the element
            int point_to_click_y = middle_y;
            TouchAction action = new TouchAction((AppiumDriver) driver);
            action.tap(PointOption.point(point_to_click_x, point_to_click_y)).perform();
        } else {
            System.out.println("Method clickElementToTheRightUpperCorner does nothing for "+Platform.getInstance().getPlatformVar());
        }
    }
    public void swipeElementToLeft(String locator, String error_message){
        if (driver instanceof AppiumDriver){
            WebElement element = waitForElementClickable(
                    locator,
                    error_message,
                    10);
            int left_x = element.getLocation().getX(); //find left element edge  on X
            int right_x = left_x + element.getSize().getWidth();//add width of element and get right edge
            int middle_x = (left_x + right_x)/2;
            int upper_y = element.getLocation().getY();
            int lower_y = upper_y + element.getSize().getHeight();
            int middle_y = (upper_y + lower_y) / 2;
            TouchAction action = new TouchAction((AppiumDriver) driver);
            action.press(PointOption.point(middle_x, middle_y));
            action.waitAction(WaitOptions.waitOptions(Duration.ofMillis(300)));
            if (Platform.getInstance().isAndroid()){
                action.moveTo(PointOption.point(0, middle_y));
            }else {
                int offset_x = (-1 * element.getSize().getWidth());//to the left by the entire width of the element
                action.moveTo(PointOption.point(0, middle_y));
            }

            action.release();
            action.perform();
        } else {
            System.out.println("Method swipeElementToLef does nothing for "+Platform.getInstance().getPlatformVar());
        }
    }
    public int getAmountOfElements(String locator){
        By by = this.getLocatorByString(locator);
        List elements = driver.findElements(by);
        return elements.size();
    }
    public boolean isElementPresent(String locator){
        return getAmountOfElements(locator) > 0;
    }
    public void tryClickElementsWithFewAttempts(String locator, String error_message, int amount_of_attempts){
        int current_attempts = 0;
        boolean need_more_attempts = true;
        while (need_more_attempts){
            try {
                this.waitForElementAndClick(locator,error_message, 1);
                need_more_attempts = false;
            } catch (Exception e){
                if (current_attempts > amount_of_attempts){
                    this.waitForElementAndClick(locator,error_message, 1);
                }
            }
            ++ current_attempts;
        }
    }
    public void assertElementNotPresent(String locator, String error_message){
        int amount_of_elements = getAmountOfElements(locator);
        if (amount_of_elements>0){
            String default_message = "An element '"+ locator+"' supposed to be not present";
            throw new AssertionError(default_message + "" +error_message);
        }
    }
    public String waitForElementAndGetAttribute(String locator, String attribute, String error_message, long timeoutInSeconds){
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
        return element.getAttribute(attribute);
    }
    private By getLocatorByString(String locator_with_type){
        String[] exploded_locator = locator_with_type.split(Pattern.quote(":"),2);
        String by_type = exploded_locator[0];
        String locator = exploded_locator[1];
        if (by_type.equals("xpath")){
            return By.xpath(locator);
        } else if (by_type.equals("id")){
            return By.id(locator);
        } else if (by_type.equals("css")){
            return By.cssSelector(locator);
        } else {
            throw new IllegalArgumentException("Cannot get type of locator. Locator "+locator_with_type);
        }
    }
}

