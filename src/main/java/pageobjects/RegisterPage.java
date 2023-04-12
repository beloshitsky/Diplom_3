package pageobjects;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.By.xpath;

public class RegisterPage {

    public static final String URL = "https://stellarburgers.nomoreparties.site/register";

    // поле имя
    private SelenideElement nameField =
            $(xpath("//label[contains(text(),'Имя')]/following-sibling::input"));

    // поле email
    private SelenideElement emailField =
            $(xpath("//label[contains(text(),'Email')]/following-sibling::input"));

    // поле пароль
    private SelenideElement passwordField = $(xpath("//input[@type='password']"));

    // некорректный пароль
    public static SelenideElement passwordError = $(cssSelector(".input__error"));

    // кнопка Зарегистрироваться
    private SelenideElement signInButton = $(byText("Зарегистрироваться"));

    // Войти
    private SelenideElement login = $(".Auth_link__1fOlj");

    public void register(String name, String email, String password) {
        setName(name);
        setEmail(email);
        setPassword(password);
        clickSignInButton();
    }

    public void setName(String name) {
        nameField.setValue(name);
    }

    public void setEmail(String email) {
        emailField.setValue(email);
    }

    public void setPassword(String password) {
        passwordField.setValue(password);
    }

    public void clickSignInButton() {
        signInButton.click();
    }

    public void clickLogin() {
        login.click();
    }
}
