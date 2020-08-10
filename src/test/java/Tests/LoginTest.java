package Tests;

import Pages.LoginPage;
import TestFixtures.BaseTestFixture;
import org.junit.jupiter.api.Test;

public class LoginTest extends BaseTestFixture {
    @Test
    public void Login() {
        new LoginPage(driver)
                .login();
    }
}
