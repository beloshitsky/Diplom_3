import base.User;
import base.UserApi;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.chrome.ChromeOptions;
import pageobjects.ForgotPasswordPage;
import pageobjects.HomePage;
import pageobjects.LoginPage;
import pageobjects.RegisterPage;

import static com.codeborne.selenide.Configuration.browserCapabilities;
import static com.codeborne.selenide.Selenide.closeWindow;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class LoginParametrizedTest {
    private final String button;
    private final String page;
    private static User user;
    private static HomePage homePage;
    private static LoginPage loginPage;
    private static RegisterPage registerPage;
    private static ForgotPasswordPage forgotPasswordPage;

    public LoginParametrizedTest(String button, String page) {
        this.button = button;
        this.page = page;
    }

    @Parameterized.Parameters(name = "{index}: Check button {0} from {1}")
    public static Object[][] getData() {
        return new Object[][] {
                {"loginButton", HomePage.URL},
                {"accountButton", HomePage.URL},
                {"loginButtonRegisterPage", RegisterPage.URL},
                {"loginButtonForgotPasswordPage", ForgotPasswordPage.URL},
        };
    }

    @BeforeClass
    public static void setUp() {
//        System.setProperty("webdriver.chrome.driver", "src/test/resources/yandexdriver.exe");
        browserCapabilities = new ChromeOptions().addArguments("--remote-allow-origins=*");

        user = new User("burger-edok", "burger-edok@ya.ru", "123456");
        UserApi.register(user);

        homePage = new HomePage();
        loginPage = new LoginPage();
        registerPage = new RegisterPage();
        forgotPasswordPage = new ForgotPasswordPage();
    }

    @Before
    public void openURL() {
        open(page);
    }

    @Test
    public void testLogin() {
        switch (button) {
            case "loginButton":
                homePage.clickLoginButton();
                break;
            case "accountButton":
                homePage.clickAccountButton();
                break;
            case "loginButtonRegisterPage":
                registerPage.clickLogin();
                break;
            case "loginButtonForgotPasswordPage":
                forgotPasswordPage.clickLogin();
                break;
        }

        loginPage.login(user.getEmail(), user.getPassword());

        assertTrue("User is not logged in!", homePage.isOrderButtonDisplayed());
    }

    @After
    public void tearDown() {
        closeWindow();
    }

    @AfterClass
    public static void afterClass() {
        UserApi.delete(user);
    }
}
