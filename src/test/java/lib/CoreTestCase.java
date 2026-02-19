package lib;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumDriver;
import junit.framework.TestCase;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.time.Duration;


public class CoreTestCase extends TestCase {
//    private static final String PLATFORM_IOS = "ios";
//    private static final String PLATFORM_ANDROID = "android";


    protected RemoteWebDriver driver;



    @Override
    protected void setUp() throws Exception {
        super.setUp();
        driver = Platform.getInstance().getDriver();
        Thread.sleep(3000);
        driver.navigate().back();
        this.rotateScreenPortrait();
        this.openWikiWebPageForMobileWeb();
    }

    @Override
    protected void tearDown() throws Exception {
        if (driver != null) {
            try {
                driver.executeScript("mobile: terminateApp", ImmutableMap.of("bundleId", "org.wikipedia"));
            } catch (Exception e) {
                driver.quit();
                super.tearDown();
            }
        }
    }

    protected void rotateScreenPortrait() {

        if (driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.rotate(ScreenOrientation.PORTRAIT);
        } else {
            System.out.println("Method rotateScreenPortrait() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }

//        AndroidDriver androidDriver = (AndroidDriver) driver;
//        androidDriver.rotate(ScreenOrientation.PORTRAIT);



    protected void rotateScreenLandscape() {
        if (driver instanceof AppiumDriver){
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.rotate(ScreenOrientation.LANDSCAPE);
        } else{
            System.out.println("Method rotateScreenLandscape() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }

//        AndroidDriver androidDriver = (AndroidDriver) driver;
//        androidDriver.rotate(ScreenOrientation.LANDSCAPE);
    }

    protected void backgroundApp(int seconds) {
        if (driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.runAppInBackground(Duration.ofSeconds(seconds));
        } else{
            System.out.println("Method rotateScreenLandscape() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
//        AndroidDriver androidDriver = (AndroidDriver) driver;
//        androidDriver.runAppInBackground(Duration.ofSeconds(seconds));
    }
    protected void openWikiWebPageForMobileWeb()
    {
       if (Platform.getInstance().isMW()) {
           driver.get("https://en.m.wikipedia.org");
       }else{
           System.out.println("Method openWikiWebPageForMobileWeb() does nothing for platform " + Platform.getInstance().getPlatformVar());
       }
    }
}