package broit.homework.core.pages.order;

import java.time.Duration;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import broit.homework.core.utils.Waiter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

@Slf4j
public class OrderPage {

    private static final SelenideElement CONTENT_WRAPPER = $(By.id("wrapper"));

    public OrderPage() {
        log.info("Try to open Order Page");
        CONTENT_WRAPPER.shouldBe(Condition.visible, Duration.ofSeconds(10));
        Waiter.makeDelay(Duration.ofSeconds(5));
    }
}
