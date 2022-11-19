package tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import config.WebDriverProvider;
import helpers.AllureAttachments;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import static com.codeborne.selenide.Selenide.open;

public class TestBase {

    @BeforeAll
    static void setUp() {
//        System.setProperty("env", "local");
        WebDriverProvider.configure();
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

//    @Step("Open www.newton.investments")
//    @BeforeEach
//    void openMainPage() {
//        open("https://newton.investments/");
//    }

    @AfterEach
    void addedAttachments() {
        AllureAttachments.screenshotAs("Last screenshot");
        AllureAttachments.addVideo();
    }
}
