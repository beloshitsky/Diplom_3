package pageobjects;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static org.openqa.selenium.By.className;
import static org.openqa.selenium.By.xpath;

public class HomePage {

    public static final String URL = "https://stellarburgers.nomoreparties.site";

    // личный кабинет
    public static SelenideElement accountButton = $("a[href='/account']");

    // кнопка конструктор
    public static SelenideElement constructorButton = $(".AppHeader_header__linkText__3q_va");

    // соберите бургер
    public static SelenideElement constructorHeader = $("h1.text_type_main-large");

    // лого
    private SelenideElement logo = $("a[href='/']");

    // вкладки булки, соусы, начинки
    public static SelenideElement bunsTab = $(xpath("//span[text()='Булки']"));
    public static SelenideElement sauceTab = $(xpath("//span[text()='Соусы']"));
    public static SelenideElement fillingTab = $(xpath("//span[text()='Начинки']"));

    // конструктор
    private SelenideElement constructorPart = $(className("BurgerIngredient_ingredient__text__yp3dH"));

    // войти в аккаунт
    private SelenideElement loginButton = $(xpath("//button[text()='Войти в аккаунт']"));

    // оформить заказ
    private SelenideElement orderButton = $("button.button_button__33qZ0");

    public void clickAccountButton() {
        accountButton.click();
    }

    public void clickConstructorButton() {
        constructorButton.click();
    }

    public void clickLogo() {
        logo.click();
    }

    public void clickLoginButton() {
        loginButton.click();
    }

    public boolean isOrderButtonDisplayed() {
        return orderButton.isDisplayed();
    }
}
