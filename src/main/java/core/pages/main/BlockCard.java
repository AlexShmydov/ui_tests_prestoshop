package core.pages.main;

import java.time.Duration;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import core.pages.cart.CartPage;
import core.utils.Waiter;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class BlockCard {

    private static final SelenideElement BLOCK_CART_MODAL = $(By.id("blockcart-modal"));
    private static final SelenideElement QUANTITY_FIELD = $(".product-quantity>strong");
    private static final SelenideElement PROCEED_TO_CHECKOUT_BUTTON = $(".cart-content-btn>a.btn.btn-primary");
    private static final SelenideElement CONTINUE_SHOPPING_BUTTON = $(".cart-content-btn>button.btn.btn-secondary");

    public BlockCard() {
        BLOCK_CART_MODAL.shouldBe(Condition.visible);
        Waiter.makeDelay(Duration.ofSeconds(2));
    }

    public BlockCard checkQuantityFieldValue(int expectedQuantity) {
        QUANTITY_FIELD.shouldBe(Condition.visible)
                      .shouldHave(Condition.text(String.valueOf(expectedQuantity)));
        return this;
    }

    public CartPage clickOnProceedToCheckout() {
        PROCEED_TO_CHECKOUT_BUTTON.shouldBe(Condition.visible)
                                  .click();
        return new CartPage();
    }

    public void clickOnContinueShopping() {
        CONTINUE_SHOPPING_BUTTON.shouldBe(Condition.visible)
                                .click();
    }
}