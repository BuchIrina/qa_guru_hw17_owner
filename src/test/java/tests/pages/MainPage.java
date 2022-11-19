package tests.pages;

import java.net.URL;

import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class MainPage {

    public MainPage openMainPage(String url) {
            step("Open main page", () -> {
            open(url);
        });
        return this;
    }
}
