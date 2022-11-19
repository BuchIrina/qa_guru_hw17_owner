package tests.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;
import static io.qameta.allure.Allure.step;

public class DepositoryPage {

    SelenideElement searchField = $("#form-text-input"),
            searchResults = $(".cnt-itemLink"),
            emptySearchResult = $(".result-empty-title");


    public DepositoryPage openPage() {
        step("Open page \"Информация депозитария\"", () -> {
            $(byText("Информация депозитария")).click();
        });
        return this;
    }

    public DepositoryPage searchOnDepositoryPage(String value) {
        step("Enter the correct search query " + value + " in the search field", () -> {
            searchField.setValue(value).pressEnter();
        });
        return this;
    }

    public DepositoryPage checkCorrectResult(String value) {
        step("Check that search results have " + value, () -> {
            searchResults.shouldHave(text(value));
        });
        return this;
    }

    public DepositoryPage checkIncorrectResult(String value) {
        step("Check that search results have " + value, () -> {
            emptySearchResult.shouldHave(text("Ничего не найдено"));
        });
        return this;
    }

    public DepositoryPage checkThatResultIsClickable() {
        step("Check that search result is clickable", () -> {
            searchResults.click();
            switchTo().window(1);
            webdriver().shouldHave(url("https://newton.investments/broker/depository/corp_568_DVCA_ANB00645_US4470111075_5ECDD058DB1220E2_1_20221110.html"));
            closeWindow();
            switchTo().window(0);
        });
        return this;
    }
}
