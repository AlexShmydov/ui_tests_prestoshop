package broit.homework.core.pages.main;

import java.time.Duration;

import broit.homework.core.pages.cart.CartPage;
import broit.homework.core.utils.Waiter;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

@Slf4j
public class BlockCard {

    private static final SelenideElement BLOCK_CART_MODAL = $(By.id("blockcart-modal"));
    private static final SelenideElement QUANTITY_FIELD = $(".product-quantity>strong");
    private static final SelenideElement PROCEED_TO_CHECKOUT_BUTTON = $(".cart-content-btn>a.btn.btn-primary");
    private static final SelenideElement CONTINUE_SHOPPING_BUTTON = $(".cart-content-btn>button.btn.btn-secondary");

    public BlockCard() {
        log.info("Try to view Block Cart area");
        BLOCK_CART_MODAL.shouldBe(Condition.visible);
        Waiter.makeDelay(Duration.ofSeconds(2));
    }

    @Step("Check quantity in Block card is equal to {expectedQuantity} value")
    public BlockCard checkQuantityFieldValue(int expectedQuantity) {
        log.info("Check quantity of product field has {} value", expectedQuantity);
        QUANTITY_FIELD.shouldBe(Condition.visible)
                      .shouldHave(Condition.text(String.valueOf(expectedQuantity)));
        return this;
    }

    @Step("Click on Proceed To Checkout button on Black Card")
    public CartPage clickOnProceedToCheckout() {
        log.info("Click on Proceed Checkout button");
        PROCEED_TO_CHECKOUT_BUTTON.shouldBe(Condition.visible)
                                  .click();
        return new CartPage();
    }

    @Step("Click on Continue Shopping")
    public void clickOnContinueShopping() {
        log.info("Click on Continue Shopping");
        CONTINUE_SHOPPING_BUTTON.shouldBe(Condition.visible)
                                .click();
        log.info("Check that Block Cart area is hidden");
        BLOCK_CART_MODAL.shouldBe(Condition.hidden);
    }
}