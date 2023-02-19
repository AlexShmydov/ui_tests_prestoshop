package broit.homework;

import broit.homework.core.configurations.DriverConfigManagerConfigurations;
import broit.homework.core.configurations.PagesConfiguration;
import broit.homework.core.managers.DriverConfigManager;
import broit.homework.core.pages.main.MainPage;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static com.codeborne.selenide.Selenide.open;

@SpringBootTest(classes = {
        PagesConfiguration.class,
        DriverConfigManagerConfigurations.class
})
public abstract class BaseTest {

    @Value("${default-url}")
    private String url;

    @Autowired
    private MainPage mainPage;
    @Autowired
    private DriverConfigManager driverConfigManager;

    @BeforeEach
    void setUp() {
        initAllure();
        driverConfigManager.setUp();
        open(url);
        mainPage.waitForLoad();
    }

    private void initAllure() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(true));
    }

    @AfterEach
    void tearDown() {
        Selenide.closeWebDriver();
    }
}
