package broit.homework.core.managers;

import broit.homework.core.configurations.DriverConfigurationProperties;
import com.codeborne.selenide.Configuration;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DefaultDriverConfigManager implements DriverConfigManager {

    private final DriverConfigurationProperties properties;

    @Override
    public void setUp() {
        Configuration.browser = properties.getBrowser();
        Configuration.driverManagerEnabled = properties.isDriverManagerEnabled();
        Configuration.webdriverLogsEnabled = properties.isWebDriverLogsEnabled();
        Configuration.headless = properties.isDriverHeadless();
        Configuration.browserSize = properties.getBrowserSize();
    }
}