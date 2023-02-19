package broit.homework.core.pages.main;

import java.time.Duration;

import broit.homework.core.utils.Waiter;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

@Slf4j
public class QuickViewProductCart {

    private static final SelenideElement QUICK_VIEW_MODAL = $(".modal.fade.quickview.in");
    private static final SelenideElement ADD_TO_CARD_BUTTON = $(".add>button.btn.add-to-cart");
    private static final SelenideElement QUANTITY_INPUT = $(By.id("quantity_wanted"));
    private static final SelenideElement QUANTITY_UP = $(".bootstrap-touchspin-up");
    private static final SelenideElement QUANTITY_DOWN = $(".bootstrap-touchspin-down");

    public QuickViewProductCart() {
        log.info("Try to view Quick View of Product area");
        QUICK_VIEW_MODAL.shouldBe(Condition.visible, Duration.ofSeconds(5));
        Waiter.makeDelay(Duration.ofSeconds(2));
    }

    @Step("Click on Quantity Up button to increase products")
    public QuickViewProductCart clickOnQuantityUp() {
        log.info("Try to increase quantity of product");
        QUANTITY_UP.shouldBe(Condition.visible)
                   .click();
        return this;
    }

    @Step("Click on Quantity Down button to decrease products")
    public QuickViewProductCart clickOnQuantityDown() {
        log.info("Try to decrease quantity of product");
        QUANTITY_DOWN.shouldBe(Condition.visible)
                     .click();
        return this;
    }

    @Step("Check quantity of products to add is equal to {expectedQuantity} value")
    public QuickViewProductCart checkQuantityOfProductToAdd(int expectedQuantity) {
        log.info("Check that quantity of product is equal to {}", expectedQuantity);
        QUANTITY_INPUT.shouldBe(Condition.visible)
                      .shouldHave(Condition.value(String.valueOf(expectedQuantity)));
        return this;
    }

    @Step("Click on Add To Cart button on Quick View Product Cart")
    public BlockCard clickOnAddToCart() {
        log.info("Click on Add To Cart button");
        ADD_TO_CARD_BUTTON.shouldBe(Condition.visible)
                          .click();
        Waiter.makeDelay(Duration.ofSeconds(2));
        log.info("Check that Quick View of Product are is hidden");
        QUICK_VIEW_MODAL.shouldBe(Condition.hidden, Duration.ofSeconds(2));
        return new BlockCard();
    }
}
