import base.User;
import base.UserApi;
import io.qameta.allure.junit4.DisplayName;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeOptions;
import pageobjects.LoginPage;
import pageobjects.RegisterPage;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Configuration.browserCapabilities;
import static com.codeborne.selenide.Selenide.open;

public class RegisterTest {

    private RegisterPage registerPage;
    private static User user;

    @Before
    public void setUp() {
//        System.setProperty("webdriver.chrome.driver", "src/test/resources/yandexdriver.exe");
        browserCapabilities = new ChromeOptions().addArguments("--remote-allow-origins=*");

        registerPage = new RegisterPage();
        user = new User("burger-edok", "burger-edok@ya.ru", "123456");

        open(RegisterPage.URL);
    }

    @Test
    @DisplayName("Check successful register user")
    public void testSuccessfulRegister() {
        registerPage.register(user.getName(), user.getEmail(), user.getPassword());

        LoginPage.loginButton.shouldBe(visible);
    }

    @Test
    @DisplayName("Check error for invalid password")
    public void testInvalidPassword() {
        registerPage.register(user.getName(), user.getEmail(), "123");

        RegisterPage.passwordError.should(appear);
    }

    @AfterClass
    public static void tearDown() {
        UserApi.delete(user);
    }
}
