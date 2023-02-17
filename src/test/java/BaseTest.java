import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import core.configurations.PagesConfiguration;
import core.pages.main.MainPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static com.codeborne.selenide.Selenide.open;

@SpringBootTest(classes = PagesConfiguration.class)
public abstract class BaseTest {

    @Value("${browser}")
    private String browser;
    @Value("${browser.size}")
    private String browserSize;
    @Value("${default.url}")
    private String url;
    @Value("${driver.manager.enabled}")
    private boolean driverManagerEnabled;
    @Value("${webDriver.logs.enabled}")
    private boolean webDriverLogsEnabled;
    @Value("${driver.headless}")
    private boolean headless;

    @Autowired
    private MainPage mainPage;

    @BeforeEach
    void setUp() {
        Configuration.browser = browser;
        Configuration.driverManagerEnabled = driverManagerEnabled;
        Configuration.webdriverLogsEnabled = webDriverLogsEnabled;
        Configuration.headless = headless;
        Configuration.browserSize = browserSize;
        open(url);
        mainPage.waitForLoad();
    }

    @AfterEach
    void tearDown() {
        Selenide.closeWebDriver();
    }
}
