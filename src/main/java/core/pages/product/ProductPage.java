package core.pages.product;

import java.time.Duration;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import core.pages.main.BlockCard;
import core.utils.Waiter;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class ProductPage {

    private static final SelenideElement CONTENT_WRAPPER = $(By.id("wrapper"));
    private static final SelenideElement QUANTITY_INPUT = $(By.id("quantity_wanted"));
    private static final SelenideElement QUANTITY_UP = $(".bootstrap-touchspin-up");
    private static final SelenideElement ADD_TO_CARD_BUTTON = $(By.cssSelector(".add button.btn.add-to-cart"));

    public ProductPage() {
        CONTENT_WRAPPER.shouldBe(Condition.visible, Duration.ofSeconds(10));
        Waiter.makeDelay(Duration.ofSeconds(5));
    }

    public ProductPage clickOnQuantityUp() {
        QUANTITY_UP.shouldBe(Condition.visible)
                   .click();
        return this;
    }

    public ProductPage checkQuantityOfProductToAdd(int expectedQuantity) {
        QUANTITY_INPUT.shouldBe(Condition.visible)
                      .shouldHave(Condition.value(String.valueOf(expectedQuantity)));
        return this;
    }

    public BlockCard clickOnAddToCart() {
        ADD_TO_CARD_BUTTON.shouldBe(Condition.visible).click();
        return new BlockCard();
    }
}
