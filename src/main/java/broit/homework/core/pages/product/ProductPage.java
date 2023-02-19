package broit.homework.core.pages.product;

import java.time.Duration;

import broit.homework.core.pages.main.BlockCard;
import broit.homework.core.utils.Waiter;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

@Slf4j
public class ProductPage {

    private static final SelenideElement CONTENT_WRAPPER = $(By.id("wrapper"));
    private static final SelenideElement QUANTITY_INPUT = $(By.id("quantity_wanted"));
    private static final SelenideElement QUANTITY_UP = $(".bootstrap-touchspin-up");
    private static final SelenideElement ADD_TO_CARD_BUTTON = $(By.cssSelector(".add button.btn.add-to-cart"));

    public ProductPage() {
        log.info("Try to open Product Page");
        CONTENT_WRAPPER.shouldBe(Condition.visible, Duration.ofSeconds(10));
        Waiter.makeDelay(Duration.ofSeconds(5));
    }

    @Step("Click on Quantity Up button to increase products")
    public ProductPage clickOnQuantityUp() {
        log.info("Try to increase quantity of product");
        QUANTITY_UP.shouldBe(Condition.visible)
                   .click();
        return this;
    }

    @Step("Check quantity of products to add is equal to {expectedQuantity} value")
    public ProductPage checkQuantityOfProductToAdd(int expectedQuantity) {
        log.info("Check that quantity of product is equal to {}", expectedQuantity);
        QUANTITY_INPUT.shouldBe(Condition.visible)
                      .shouldHave(Condition.value(String.valueOf(expectedQuantity)));
        return this;
    }

    @Step("Click on Add To Cart button on Product Page")
    public BlockCard clickOnAddToCart() {
        log.info("Try to click on Add To Cart button");
        ADD_TO_CARD_BUTTON.shouldBe(Condition.visible).click();
        return new BlockCard();
    }
}
