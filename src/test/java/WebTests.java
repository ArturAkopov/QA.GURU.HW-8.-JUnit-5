import data.ElementButtons;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.CollectionCondition.itemWithText;
import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;

public class WebTests extends TestBase {

    @CsvFileSource(resources = "/test_data/demoqaCheckingUrlAfterOpenCardCategory.csv", delimiterString = "|")
    @DisplayName("Проверка соответствия Url для карточек-категорий после открытия")
    @ParameterizedTest(name = "Проверка соответствия Url для категории {0} после открытия карточки")
    void demoqaCheckingUrlAfterOpenCardCategory(String cardCategory, String url) {
        $$(".card-body").filterBy(text(cardCategory)).first().parent().click();
        webdriver().shouldHave(url(url));
    }

    @EnumSource(ElementButtons.class)
    @DisplayName("Проверка соответствия названий для кнопок в разделе Elements")
    @ParameterizedTest(name = "Проверка соответствия названия для кнопки {0} в разделе Elements")
    void demoqaCheckingNameButtonCategoryElement(ElementButtons elementButtons) {
        $$(".card-body").filterBy(text("Elements")).first().parent().click();
        $(".element-group").$$(".btn-light ")
                .shouldHave(itemWithText(elementButtons.description));
    }

    static Stream<Arguments> demoqaCheckingNameForCategories() {
        return Stream.of(
                Arguments.of(List.of("Elements", "Forms", "Alerts, Frame & Windows",
                        "Widgets", "Interactions", "Book Store Application")
                ));
    }

    @MethodSource
    @DisplayName("Проверка соответствия названий категорий на странице https://demoqa.com")
    @ParameterizedTest(name = "Проверка соответствия названий категорий {0}")
    void demoqaCheckingNameForCategories(List<String> cardCategory) {
        $$(".card-body").filter(visible).shouldHave(texts(cardCategory));
    }
}

