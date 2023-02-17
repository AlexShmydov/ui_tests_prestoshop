package core.pages.order;

import java.time.Duration;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import core.utils.Waiter;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class OrderPage {

    private static final SelenideElement CONTENT_WRAPPER = $(By.id("wrapper"));

    public OrderPage() {
        CONTENT_WRAPPER.shouldBe(Condition.visible, Duration.ofSeconds(10));
        Waiter.makeDelay(Duration.ofSeconds(5));
    }
}
