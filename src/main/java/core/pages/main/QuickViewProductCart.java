package core.pages.main;

import java.time.Duration;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import core.utils.Waiter;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class QuickViewProductCart {

    private static final SelenideElement QUICK_VIEW_MODAL = $(".modal.fade.quickview.in");
    private static final SelenideElement ADD_TO_CARD_BUTTON = $(".add>button.btn.add-to-cart");
    private static final SelenideElement QUANTITY_INPUT = $(By.id("quantity_wanted"));
    private static final SelenideElement QUANTITY_UP = $(".bootstrap-touchspin-up");
    private static final SelenideElement QUANTITY_DOWN = $(".bootstrap-touchspin-down");

    public QuickViewProductCart() {
        QUICK_VIEW_MODAL.shouldBe(Condition.visible, Duration.ofSeconds(5));
        Waiter.makeDelay(Duration.ofSeconds(2));
    }

    public QuickViewProductCart clickOnQuantityUp() {
        QUANTITY_UP.shouldBe(Condition.visible)
                   .click();
        return this;
    }

    public QuickViewProductCart clickOnQuantityDown() {
        QUANTITY_DOWN.shouldBe(Condition.visible)
                   .click();
        return this;
    }

    public QuickViewProductCart checkQuantityOfProductToAdd(int expectedQuantity) {
        QUANTITY_INPUT.shouldBe(Condition.visible)
                      .shouldHave(Condition.value(String.valueOf(expectedQuantity)));
        return this;
    }

    public BlockCard clickOnAddToCart() {
        ADD_TO_CARD_BUTTON.shouldBe(Condition.visible)
                          .click();
        Waiter.makeDelay(Duration.ofSeconds(2));
        QUICK_VIEW_MODAL.shouldBe(Condition.hidden, Duration.ofSeconds(2));
        return new BlockCard();
    }
}
