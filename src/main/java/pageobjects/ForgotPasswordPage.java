package pageobjects;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class ForgotPasswordPage {

    public static final String URL = "https://stellarburgers.nomoreparties.site/forgot-password";

    private SelenideElement login = $(".Auth_link__1fOlj");

    public void clickLogin() {
        login.click();
    }
}
