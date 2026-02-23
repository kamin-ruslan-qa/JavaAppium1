package lib;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.FileOutputStream;
import java.time.Duration;
import java.util.Properties;


public class CoreTestCase {
//    private static final String PLATFORM_IOS = "ios";
//    private static final String PLATFORM_ANDROID = "android";


    protected RemoteWebDriver driver;



    @Before
    @Step("Run driver and session")
    public void setUp() throws Exception {
        driver = Platform.getInstance().getDriver();
        this.createAllurePropertyFile();
        Thread.sleep(3000);
        driver.navigate().back();
        this.rotateScreenPortrait();
        this.openWikiWebPageForMobileWeb();
    }

    @After
    @Step("Remove driver and session")
    public void tearDown() {
        if (driver != null) {
            try {
                driver.executeScript("mobile: terminateApp", ImmutableMap.of("bundleId", "org.wikipedia"));
            } catch (Exception e) {
                driver.quit();
            }
        }
    }
@Step("Rotate screen to portrait mode")
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


    @Step("Rotate screen to landscape mode")
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
    @Step("Send mobile app to background(this method does nothing for Mobile Web)")
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
    @Step("Open Wikipedia URL for Mobile Web (this method does nothing for Android and IOS")
    protected void openWikiWebPageForMobileWeb()
    {
       if (Platform.getInstance().isMW()) {
           driver.get("https://en.m.wikipedia.org");
       }else{
           System.out.println("Method openWikiWebPageForMobileWeb() does nothing for platform " + Platform.getInstance().getPlatformVar());
       }
    }
    private void createAllurePropertyFile(){
        String path = System.getProperty("allure.results.directory");
        try {
            Properties props = new Properties();
            FileOutputStream fos = new FileOutputStream(path + "/environment.properties");
            props.setProperty("Environment", Platform.getInstance().getPlatformVar());
            props.store(fos, "See https://github.com/allure-framework/allure-app/wiki/Environment");
            fos.close();
        }catch (Exception e){
            System.err.println("IO problem when writing allure properties file");
            e.printStackTrace();
        }
    }
}