import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.chrome.ChromeOptions;
import pageobjects.HomePage;

import java.util.Objects;

import static com.codeborne.selenide.Configuration.browserCapabilities;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class ConstructorTest {

    private SelenideElement tabName;

    public ConstructorTest(SelenideElement tabName) {
        this.tabName = tabName;
    }

    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][] {
                {HomePage.sauceTab},
                {HomePage.fillingTab},
                {HomePage.bunsTab},
        };
    }

    @Before
    public void setUp() {
//        System.setProperty("webdriver.chrome.driver", "src/test/resources/yandexdriver.exe");
        browserCapabilities = new ChromeOptions().addArguments("--remote-allow-origins=*");

        open(HomePage.URL);
        if (!Objects.equals(tabName, HomePage.bunsTab)) tabName.click();
    }

    @Test
    @DisplayName("Check switch tabs in constructor")
    public void testConstructor() {
        boolean isCurrentTab = tabName.parent().attr("class").contains("current");

        assertTrue(isCurrentTab);
    }
}
