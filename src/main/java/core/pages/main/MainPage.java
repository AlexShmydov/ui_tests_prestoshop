package core.pages.main;

import java.time.Duration;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import core.pages.product.ProductPage;
import core.utils.Waiter;
import lombok.AllArgsConstructor;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.actions;
import static com.codeborne.selenide.Selenide.switchTo;

@AllArgsConstructor
public class MainPage {

    private static final By PRODUCT_QUICK_VIEW_BUTTON_SELECTOR = By.cssSelector("a.quick-view");
    private static final SelenideElement CONTENT_WRAPPER = $(By.id("wrapper"));
    private static final SelenideElement PRODUCTS_AREA = $(".products.row");
    private static final ElementsCollection PRODUCTS = $$(".products.row>.js-product.product");
    private static final String CSS_SELECTOR_ACTIVE_DEVICE_TEMPLATE = "a.change-device.%s.active";
    private static final String CSS_SELECTOR_DEVICE_TEMPLATE = "a.change-device.%s";
    private static final String FRAME_ID = "framelive";

    private final String device;

    public void waitForLoad() {
        selectDevice();
        switchToFrame();
        CONTENT_WRAPPER.shouldBe(Condition.visible, Duration.ofSeconds(10));
        Waiter.makeDelay(Duration.ofSeconds(5));
    }

    private void selectDevice() {
        if (!$(String.format(CSS_SELECTOR_ACTIVE_DEVICE_TEMPLATE, device)).exists()) {
            $(String.format(CSS_SELECTOR_DEVICE_TEMPLATE, device)).shouldBe(Condition.visible).click();
        }
    }

    private void switchToFrame() {
        switchTo().defaultContent();
        switchTo().frame(FRAME_ID);
    }

    public MainPage isProductsAvailable() {
        PRODUCTS_AREA.shouldBe(Condition.visible);
        return this;
    }

    public QuickViewProductCart clickOnQuickViewOfProduct(int productIndex) {
        PRODUCTS.shouldBe(CollectionCondition.sizeGreaterThanOrEqual(productIndex));
        SelenideElement targetProduct = PRODUCTS.get(productIndex);
        actions().moveToElement(targetProduct).perform();
        SelenideElement quickViewButton = targetProduct.find(PRODUCT_QUICK_VIEW_BUTTON_SELECTOR);
        quickViewButton.shouldBe(Condition.visible);
        quickViewButton.click();
        return new QuickViewProductCart();
    }

    public ProductPage clickOnProduct(int productIndex) {
        PRODUCTS.shouldBe(CollectionCondition.sizeGreaterThanOrEqual(productIndex));
        PRODUCTS.get(productIndex).click();
        return new ProductPage();
    }
}
