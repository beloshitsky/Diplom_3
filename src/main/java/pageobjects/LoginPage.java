package pageobjects;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static org.openqa.selenium.By.linkText;
import static org.openqa.selenium.By.xpath;

public class LoginPage {

    // поле email
    private SelenideElement emailField = $(xpath("//input[@name='name']"));

    // поле пароль
    private SelenideElement passwordField = $(xpath("//input[@type='password']"));

    // кнопка Войти
    public static SelenideElement loginButton = $(".button_button_size_medium__3zxIa");

    // зарегистрироваться
    private SelenideElement signIn = $(linkText("Зарегистрироваться"));

    // восстановить пароль
    private SelenideElement forgotPassword = $(linkText("Восстановить пароль"));

    public void login(String email, String password) {
        setEmail(email);
        setPassword(password);
        clickLoginButton();
    }

    public void setEmail(String email) {
        emailField.setValue(email);
    }

    public void setPassword(String password) {
        passwordField.setValue(password);
    }

    public void clickLoginButton() {
        loginButton.click();
    }
}
