package broit.homework.core.pages.cart;

import java.time.Duration;

import broit.homework.core.pages.order.OrderPage;
import broit.homework.core.utils.Waiter;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

@Slf4j
public class CartPage {

    private static final SelenideElement CONTENT_WRAPPER = $(By.id("wrapper"));
    private static final SelenideElement PROCEED_TO_CHECKOUT_BUTTON = $("div.checkout a.btn");
    private static final ElementsCollection CART_ITEMS = $$("li.cart-item");
    private static final By PRODUCT_QUANTITY_SELECTOR = By.cssSelector("input.js-cart-line-product-quantity.form-control");

    public CartPage() {
        log.info("Try to open Cart Page");
        CONTENT_WRAPPER.shouldBe(Condition.visible, Duration.ofSeconds(10));
        Waiter.makeDelay(Duration.ofSeconds(5));
    }

    @Step("Check amount of items in cart is equal to {expectedValue} value")
    public CartPage checkAmountOfItemsInCart(int expectedValue) {
        log.info("Check that amount of items in cart should be equal with {}", expectedValue);
        CART_ITEMS.shouldBe(CollectionCondition.size(expectedValue));
        return this;
    }

    @Step("Check amount of products is equal to {expectedValue} in item with {itemIndex} index")
    public CartPage checkAmountOfProductsInItem(int expectedValue, int itemIndex) {
        log.info("Check that amount of products in cart item with {} index should be equal with {}", itemIndex, expectedValue);
        SelenideElement cardItem = CART_ITEMS.get(itemIndex).shouldBe(Condition.visible);
        SelenideElement productQuantity = cardItem.find(PRODUCT_QUANTITY_SELECTOR).shouldBe(Condition.visible);
        productQuantity.shouldHave(Condition.value(String.valueOf(expectedValue)));
        return this;
    }

    @Step("Click on Proceed To Checkout button")
    public OrderPage clickOnProceedToCheckout() {
        log.info("Click on Proceed Checkout button");
        PROCEED_TO_CHECKOUT_BUTTON.shouldBe(Condition.visible)
                                  .click();
        return new OrderPage();
    }
}
