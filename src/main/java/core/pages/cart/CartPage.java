package core.pages.cart;

import java.time.Duration;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import core.pages.order.OrderPage;
import core.utils.Waiter;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CartPage {

    private static final SelenideElement CONTENT_WRAPPER = $(By.id("wrapper"));
    private static final SelenideElement PROCEED_TO_CHECKOUT_BUTTON = $("div.checkout a.btn");
    private static final ElementsCollection CART_ITEMS = $$("li.cart-item");
    private static final By PRODUCT_QUANTITY_SELECTOR = By.cssSelector("input.js-cart-line-product-quantity.form-control");

    public CartPage() {
        CONTENT_WRAPPER.shouldBe(Condition.visible, Duration.ofSeconds(10));
        Waiter.makeDelay(Duration.ofSeconds(5));
    }

    public CartPage checkAmountOfItemsInCart(int expectedValue) {
        CART_ITEMS.shouldBe(CollectionCondition.size(expectedValue));
        return this;
    }

    public CartPage checkAmountOfProductsInItem(int expectedValue, int itemIndex) {
        SelenideElement cardItem = CART_ITEMS.get(itemIndex).shouldBe(Condition.visible);
        SelenideElement productQuantity = cardItem.find(PRODUCT_QUANTITY_SELECTOR).shouldBe(Condition.visible);
        productQuantity.shouldHave(Condition.value(String.valueOf(expectedValue)));
        return this;
    }

    public OrderPage clickOnProceedToCheckout() {
        PROCEED_TO_CHECKOUT_BUTTON.shouldBe(Condition.visible)
                                  .click();
        return new OrderPage();
    }
}
