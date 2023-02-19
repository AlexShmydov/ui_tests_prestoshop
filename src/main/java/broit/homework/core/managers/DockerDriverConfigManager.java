package broit.homework.core.managers;

import broit.homework.core.configurations.DriverConfigurationProperties;
import com.codeborne.selenide.Configuration;
import lombok.AllArgsConstructor;
import org.openqa.selenium.remote.DesiredCapabilities;

@AllArgsConstructor
public class DockerDriverConfigManager implements DriverConfigManager {

    private final DriverConfigurationProperties properties;

    @Override
    public void setUp() {
        Configuration.remote = properties.getRemoteUrl();
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", properties.isEnableVNC());
        capabilities.setCapability("enableVideo", properties.isEnableVideo());
        Configuration.browserCapabilities = capabilities;

        Configuration.browser = properties.getBrowser();
        Configuration.driverManagerEnabled = properties.isDriverManagerEnabled();
        Configuration.webdriverLogsEnabled = properties.isWebDriverLogsEnabled();
        Configuration.headless = properties.isDriverHeadless();
        Configuration.browserSize = properties.getBrowserSize();
    }
}
