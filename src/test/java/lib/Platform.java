package lib;


import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;


public class Platform {
    private static final String PLATFORM_IOS = "ios";
    private static final String PLATFORM_ANDROID = "android";
    private static final String PLATFORM_MOBILE_WEB = "mobile_web";
    private static final String APPIUM_URL = "http://127.0.0.1:4723/";

   private static Platform instance;

   private Platform() {}
   public static Platform getInstance() {
       if (instance == null) {
           instance = new Platform();
       }
       return instance;
   }

public RemoteWebDriver getDriver() throws Exception {
    URL URL = new URL(APPIUM_URL);
    if (this.isAndroid()) {
        return new AndroidDriver(URL, this.getAndroidCapabilities());
    }else if (this.isMW()) {
        System.setProperty("webdriver.chrome.driver", "C:/Users/user/Desktop/JavaAppium1/JavaAppium1/chromedriver-win32/chromedriver.exe");
        return new ChromeDriver(this.getMWChromeOptions());
    }else {
        throw new Exception("Cannot detect the platform!" + this.getPlatformVar());
    }
}


    public boolean isAndroid () {
        return this.isPlatform(PLATFORM_ANDROID);
    }
    public boolean isMW () {return this.isPlatform(PLATFORM_MOBILE_WEB);}

    private DesiredCapabilities getCapabilitiesByPlatform() throws Exception {
        String platform = System.getenv("PLATFORM");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        if (platform.equals(PLATFORM_IOS)) {
            capabilities.setCapability("platformName", "iOS");
            capabilities.setCapability("appium:automationName", "XCUITest");
            capabilities.setCapability("appium:deviceName", "iPhone SE");
            capabilities.setCapability("appium:platformVersion", "14.4");
            capabilities.setCapability("appium:app", "C:/Users/user/Desktop/JavaAppiumAutomatoin/JavaAppiumAutomatoin/apiks/Wikipedia.app");
        } else if (platform.equals(PLATFORM_ANDROID)) {
            capabilities.setCapability("platformName", "Android");
            capabilities.setCapability("appium:automationName", "UiAutomator2");
            capabilities.setCapability("appium:deviceName", "AndroidTestDevice");
            capabilities.setCapability("appium:platformVersion", "8");
            capabilities.setCapability("appium:appPackage", "org.wikipedia");
            capabilities.setCapability("appium:appActivity", ".main.MainActivity");
            capabilities.setCapability("appium:app", "C:/Users/user/Desktop/JavaAppiumAutomatoin/JavaAppiumAutomatoin/apiks/org.wikipedia.apk");
        } else {
            throw new Exception("Cannot detect the platform name!");
        }
        return capabilities;
    }
private DesiredCapabilities getAndroidCapabilities() {
    DesiredCapabilities capabilities = new DesiredCapabilities();

    capabilities.setCapability("platformName", "Android");
    capabilities.setCapability("appium:automationName", "UiAutomator2");
    capabilities.setCapability("appium:deviceName", "AndroidTestDevice");
    capabilities.setCapability("appium:platformVersion", "8");
    capabilities.setCapability("appium:appPackage", "org.wikipedia");
    capabilities.setCapability("appium:appActivity", ".main.MainActivity");
    capabilities.setCapability("appium:app", "C:/Users/user/Desktop/JavaAppiumAutomatoin/JavaAppiumAutomatoin/apiks/org.wikipedia.apk");
    return capabilities;
}

private ChromeOptions getMWChromeOptions() {
    Map<String, Object> deviceMetrics = new HashMap<String, Object>();
    deviceMetrics.put("width", 360);
    deviceMetrics.put("height", 640);
    deviceMetrics.put("pixelRatio", 3.0);

    Map<String, Object> mobileEmulation = new HashMap<String, Object>();
    mobileEmulation.put("deviceMetrics", deviceMetrics);
    mobileEmulation.put("userAgent", "Mozilla/5.0 (Linux; Android 4.2.1; en-us; Nexus 5 Build/JOP40D) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.166 Mobile Safari/535.19");

    ChromeOptions chromeOptions = new ChromeOptions();
    chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
    chromeOptions.addArguments("window-size=360,640");
    return chromeOptions;

}
private  boolean isPlatform(String my_platform) {
        String platform = this.getPlatformVar();
        return my_platform.equals(platform);
}
public String getPlatformVar() {
    return System.getenv("PLATFORM");
}

    public boolean isIOS() {
        return this.isPlatform(Platform.PLATFORM_IOS);
    }
}

