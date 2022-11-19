package tests.web;

import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.TestBase;
import tests.pages.DepositoryPage;
import tests.pages.MainPage;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.image;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.qameta.allure.Allure.step;

public class NewtonInvestmentsWebTests extends TestBase {

    private static final String CORRECT_QUERY = "US4470111075";
    private static final String INCORRECT_QUERY = "gh907jdnhd559lk";
    private static final String PHONE = "+7 800 333-17-47";

    MainPage mainPage = new MainPage();
    DepositoryPage depositoryPage = new DepositoryPage();

    @Owner("BuchIrina")
    @DisplayName("Main page should has company logo")
    @Test
    void logoTest() {
        mainPage.openMainPage("");
        step("Check that main page has logo", () -> {
            $(".header-logo img").shouldBe(image);
        });
    }

    @Owner("BuchIrina")
    @DisplayName("Clicking on company logo should return to main page")
    @Test
    void returnToMainPageByClickingOnLogo() {
        mainPage.openMainPage("");
        step("Open page \"О компании\"", () -> {
            $(byText("О компании")).click();
        });
        step("Click on logo", () -> {
            $(".header-logo").click();
        });
        step("Check that open main page", () -> {
            $(".tittle").shouldHave(text("Ньютон Инвестиции"));
        });
    }

    @Owner("BuchIrina")
    @DisplayName("Navigation bar menu check")
    @Test
    void navBarTest() {
        mainPage.openMainPage("");
        step("Check that Navigation bar has expected titles", () -> {
            $$(".topMenu li").shouldHave(texts("О компании", "Раскрытие информации",
                    "Информация депозитария", "Новости", "Контакты"));
        });
    }

    @Owner("BuchIrina")
    @DisplayName("Button menu check")
    @Test
    void buttonMenuTest() {
        mainPage.openMainPage("");
        step("Check that button menu has buttons", () -> {
            $$(".bottomMenu li").shouldHave(texts("Соглашение", "Получателям финансовых услуг",
                    "Контактная информация", "Информация депозитария", "О компании", "Раскрытие информации"));
        });
    }

    @Owner("BuchIrina")
    @DisplayName("Menu \"О компании\" should has description")
    @Test
    void menuAboutCompanyTest() {
        mainPage.openMainPage("");
        step("Open page \"О компании\"", () -> {
            $(byText("О компании")).click();
        });
        step("Check that page has text", () -> {
            $(".layout").shouldHave(text("ООО «Ньютон Инвестиции» – брокер для частных инвесторов " +
                    "на фондовом и валютном рынках, развивающий приложение Газпромбанк Инвестиции."));
        });
    }

    @Owner("BuchIrina")
    @DisplayName("Menu \"Раскрытие информации\" should has \"Лицензии\"")
    @Test
    void pageDisclosureShouldHaveLicenseTest() {
        mainPage.openMainPage("");
        step("Open page \"Раскрытие информации\"", () -> $(byText("Раскрытие информации")).click());
        step("Check that the page has a section \"Лицензии\"", () -> {
            $(".js_accordion").shouldHave(text("Лицензии")).click();
        });
        step("Check that section \"Лицензии\" has specific text", () -> {
            $(".cntAccordion-content", 2).shouldHave(text("Лицензия на осуществление брокерской деятельности" +
                    " №045‑14007‑100000 (решение Банка России от 22.10.2019 № РБ‑14/1064)"));
        });
    }

    @Owner("BuchIrina")
    @DisplayName("Check search result with correct query")
    @Test
    void searchOnDepositoryPageWithCorrectQueryTest() {
        mainPage.openMainPage("");
        depositoryPage.openPage()
                .searchOnDepositoryPage(CORRECT_QUERY)
                .checkCorrectResult(CORRECT_QUERY);
    }

    @Owner("BuchIrina")
    @DisplayName("Check search result with incorrect query")
    @Test
    void searchOnDepositoryPageWithIncorrectQueryTest() {
        mainPage.openMainPage("");
        depositoryPage.openPage()
                .searchOnDepositoryPage(INCORRECT_QUERY)
                .checkIncorrectResult(INCORRECT_QUERY);
    }

    @Owner("BuchIrina")
    @DisplayName("Check that links of search results clickable")
    @Test
    void onDepositoryPageLinksInSearchResultsShouldBeClickableTest() {
        mainPage.openMainPage("");
        depositoryPage.openPage()
                .searchOnDepositoryPage(CORRECT_QUERY)
                .checkCorrectResult(CORRECT_QUERY)
                .checkThatResultIsClickable();
    }

    @Owner("BuchIrina")
    @DisplayName("Check that main contacts include phone number")
    @Test
    void menuContactsTest() {
        mainPage.openMainPage("");
        step("Open page \"Контакты\"", () -> {
            $(byText("Контакты")).click();
        });
        step("Check that page has text " + PHONE, () -> {
            $(".row-surround").shouldHave(text(PHONE));
        });
    }
}

