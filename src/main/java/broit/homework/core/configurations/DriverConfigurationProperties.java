package broit.homework.core.configurations;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties
public class DriverConfigurationProperties {

    private String defaultUrl;
    private String remoteUrl;
    private String browser;
    private String browserSize;
    private boolean enableVNC;
    private boolean enableVideo;
    private boolean driverManagerEnabled;
    private boolean driverHeadless;
    private boolean webDriverLogsEnabled;
}
