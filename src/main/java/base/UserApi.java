package base;

import com.codeborne.selenide.Selenide;
import io.restassured.http.ContentType;
import pageobjects.LoginPage;
import pageobjects.RegisterPage;

import static com.codeborne.selenide.Selenide.open;
import static io.restassured.RestAssured.given;

public class UserApi {
    private static LoginPage loginPage = new LoginPage();
    private static RegisterPage registerPage = new RegisterPage();
    private static final String MAIN_PAGE = "https://stellarburgers.nomoreparties.site";
    private static final String REGISTER_PAGE = MAIN_PAGE + "/register";
    private static final String LOGIN_PAGE = MAIN_PAGE + "/login";
    private static final String DELETE_URI = MAIN_PAGE + "/api/auth/user";

    public static void register(User user) {
        open(REGISTER_PAGE);
        registerPage.register(user.getName(), user.getEmail(), user.getPassword());
    }

    public static void delete(User user) {
        open(LOGIN_PAGE);
        loginPage.login(user.getEmail(), user.getPassword());

        Selenide.sleep(1000);
        String token = Selenide.localStorage().getItem("accessToken");

        given()
                .header("Authorization", token)
                .contentType(ContentType.JSON)
                .body(user)
                .delete(DELETE_URI);
    }
}
