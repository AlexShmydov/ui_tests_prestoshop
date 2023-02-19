package broit.homework.core.pages.main;

import java.time.Duration;

import broit.homework.core.pages.product.ProductPage;
import broit.homework.core.utils.StringHelper;
import broit.homework.core.utils.Waiter;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.actions;
import static com.codeborne.selenide.Selenide.switchTo;

@Slf4j
@AllArgsConstructor
public class MainPage {

    private static final By PRODUCT_QUICK_VIEW_BUTTON_SELECTOR = By.cssSelector("a.quick-view");
    private static final SelenideElement CONTENT_WRAPPER = $(By.id("wrapper"));
    private static final SelenideElement BLOCK_CART_PRODUCT_QUANTITY = $("div.blockcart span.cart-products-count");
    private static final SelenideElement PRODUCTS_AREA = $(".products.row");
    private static final ElementsCollection PRODUCTS = $$(".products.row>.js-product.product");
    private static final String CSS_SELECTOR_ACTIVE_DEVICE_TEMPLATE = "a.change-device.%s.active";
    private static final String CSS_SELECTOR_DEVICE_TEMPLATE = "a.change-device.%s";
    private static final String FRAME_ID = "framelive";

    private final String device;

    @Step("Wait fo load Main Page")
    public void waitForLoad() {
        log.info("Start to wait until Main Page will be ready");
        selectDevice();
        switchToFrame();
        CONTENT_WRAPPER.shouldBe(Condition.visible, Duration.ofSeconds(10));
        Waiter.makeDelay(Duration.ofSeconds(5));
    }

    @Step("Select device")
    private void selectDevice() {
        log.info("Check that selected device is {}", device);
        if (!$(String.format(CSS_SELECTOR_ACTIVE_DEVICE_TEMPLATE, device)).exists()) {
            log.info("Need to change device. Try to select {} device", device);
            $(String.format(CSS_SELECTOR_DEVICE_TEMPLATE, device)).shouldBe(Condition.visible)
                                                                  .click();
        }
    }

    @Step("Switch to frame")
    private void switchToFrame() {
        log.info("Try to swatch to frame with {} id", FRAME_ID);
        switchTo().defaultContent();
        switchTo().frame(FRAME_ID);
    }

    @Step("Check is Products available")
    public MainPage isProductsAvailable() {
        log.info("Check that list of products is available");
        PRODUCTS_AREA.shouldBe(Condition.visible);
        return this;
    }

    @Step("Click on QuickView of product with {productIndex} index")
    public QuickViewProductCart clickOnQuickViewOfProduct(int productIndex) {
        log.info("Try to show Quick View of product with {} index", productIndex);
        PRODUCTS.shouldBe(CollectionCondition.sizeGreaterThanOrEqual(productIndex));
        SelenideElement targetProduct = PRODUCTS.get(productIndex);
        actions().moveToElement(targetProduct).perform();
        log.info("Click on Quick View of product");
        SelenideElement quickViewButton = targetProduct.find(PRODUCT_QUICK_VIEW_BUTTON_SELECTOR);
        quickViewButton.shouldBe(Condition.visible);
        quickViewButton.click();
        return new QuickViewProductCart();
    }

    @Step("Click on product with {productIndex} index")
    public ProductPage clickOnProduct(int productIndex) {
        log.info("Click on product with {} index", productIndex);
        PRODUCTS.shouldBe(CollectionCondition.sizeGreaterThanOrEqual(productIndex));
        PRODUCTS.get(productIndex).click();
        return new ProductPage();
    }

    @Step("Check amount of products in Block card icon is equal to {expectedQuantity} value")
    public MainPage checkAmountOfProductsInCartIcon(int expectedQuantity) {
        log.info("Check amount of products in Block card icon is equal to {} value", expectedQuantity);
        BLOCK_CART_PRODUCT_QUANTITY.shouldBe(Condition.visible)
                                   .shouldHave(Condition.match("Amount of products", (e) -> StringHelper.getOnlyNumbers(e.getText()).equals(expectedQuantity)));
        return this;
    }
}
