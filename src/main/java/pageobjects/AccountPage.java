package pageobjects;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class AccountPage {

    // профиль
    private SelenideElement account = $("a[href='/account/profile'][aria-current='page']");

    // выход
    public static SelenideElement logout = $(".Account_button__14Yp3");

    // кнопка Конструктор
    private SelenideElement constructorButton = $(".AppHeader_header__linkText__3q_va");

    public void clickConstructorButton() {
        constructorButton.click();
    }

    public static void logout() {
        logout.click();
    }
}
