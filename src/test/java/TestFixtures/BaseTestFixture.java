package TestFixtures;

import Helpers.Config;
import Helpers.Log;
import Helpers.TakeScreenExtension;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.apache.http.ParseException;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;


public abstract class BaseTestFixture {
    protected AppiumDriver<MobileElement> driver;

    public static boolean isNullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }

    @BeforeEach
    public void setUp(TestInfo testInfo) {
        Log.startLog("setUp");

        new TakeScreenExtension().InitPath(testInfo.getDisplayName());

        Config config = setProjectConfig("config");
        initMobileDriver(config);
    }

    @AfterEach
    public void cleanUp() {
        try {
            driver.quit();
        } catch (Exception e) {
            Log.error(e.toString());
        }

        Log.endLog("cleanUp");
    }

    private Config setProjectConfig(String configName) {
        try {
            String projectPath = System.getProperty("user.dir");
            String content = new String(Files.readAllBytes(Paths.get(projectPath + "//" + configName + ".json")));

            JSONObject jsonFile = new JSONObject(content);

            Config currentConfig = new Config();
            currentConfig.setiOSversion(jsonFile.getString("iOSversion"));
            currentConfig.setAppPath(jsonFile.getString("appPath"));
            currentConfig.setNoReset(jsonFile.getString("noReset"));
            currentConfig.setPlatform(jsonFile.getString("platform"));
            currentConfig.setAppiumServer(jsonFile.getString("appiumServer"));
            currentConfig.setDeviceName(jsonFile.getString("deviceName"));

            return currentConfig;

        } catch (IOException | ParseException e) {
            Log.error(e.toString());
        }

        return null;
    }

    private void initMobileDriver(Config config) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        URL driverUrl;

        try {
            driverUrl = new URL(config.getAppiumServer());
        } catch (MalformedURLException e) {
            Log.error(e.toString());
            throw new RuntimeException(e);
        }

        // important
        capabilities.setCapability(MobileCapabilityType.APP, config.getAppPath());
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, config.getiOSversion());
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, config.getDeviceName());
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, config.getPlatform());
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");

        // important for run on real device
        capabilities.setCapability("udid", "auto");
        capabilities.setCapability("xcodeSigningId", "iPhone Developer");
        if (isNullOrEmpty(config.getBundleid()))
            capabilities.setCapability("bundleId", config.getBundleid());
        if (isNullOrEmpty(config.getXcodeOrgId()))
            capabilities.setCapability("xcodeOrgId", config.getXcodeOrgId());
        if (isNullOrEmpty(config.getUpdatedWDABundleId()))
            capabilities.setCapability("updatedWDABundleId", config.getUpdatedWDABundleId());

        // optional
        capabilities.setCapability("autoGrantPermissions", true);
        capabilities.setCapability("unicodeKeyboard", true);
        capabilities.setCapability("resetKeyboard", true);
        capabilities.setCapability(MobileCapabilityType.NO_RESET, config.getNoReset());

        driver = new IOSDriver<>(driverUrl, capabilities);
    }
}
