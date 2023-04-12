import base.User;
import base.UserApi;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.junit4.DisplayName;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeOptions;
import pageobjects.AccountPage;
import pageobjects.HomePage;
import pageobjects.LoginPage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Configuration.browserCapabilities;
import static com.codeborne.selenide.Selenide.closeWindow;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.Assert.assertEquals;

public class AccountTest {

    private HomePage homePage;
    private LoginPage loginPage;
    private static User user;

    @Before
    public void setUp() {
//        System.setProperty("webdriver.chrome.driver", "src/test/resources/yandexdriver.exe");
        browserCapabilities = new ChromeOptions().addArguments("--remote-allow-origins=*");

        homePage = new HomePage();
        loginPage = new LoginPage();

        user = new User("burger-edok", "burger-edok@ya.ru", "123456");
        UserApi.register(user);

        open(HomePage.URL);
    }

    @Test
    @DisplayName("Check switch from main page to login page")
    public void testAccountButton() {
        String expectedURI = HomePage.URL + "/login";
        homePage.clickAccountButton();

        assertEquals(expectedURI, WebDriverRunner.url());
    }

    @Test
    @DisplayName("Check switch from account to main page by constructor button")
    public void testConstructorButton() {
        homePage.clickAccountButton();
        homePage.clickConstructorButton();

        HomePage.constructorHeader.shouldBe(visible);
    }

    @Test
    @DisplayName("Check switch from account to main page by logo")
    public void testLogo() {
        homePage.clickAccountButton();
        homePage.clickLogo();

        HomePage.constructorHeader.shouldBe(visible);
    }

    @Test
    @DisplayName("Check switch to login page after logout")
    public void testLogout() {
        homePage.clickLoginButton();
        loginPage.login(user.getEmail(), user.getPassword());
        homePage.clickAccountButton();
        AccountPage.logout();

        LoginPage.loginButton.shouldBe(visible);
    }

    @AfterClass
    public static void afterClass() {
        closeWindow();
        UserApi.delete(user);
    }
}
