package Pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;

public class LoginPage extends BasePage {
    public LoginPage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    public LoginPage login() {
        By account_icon = MobileBy.AccessibilityId("user");
        tap(account_icon);

        By login_button = MobileBy.name("Login");
        tap(login_button);

        sleep(5000);

        return this;
    }
}
