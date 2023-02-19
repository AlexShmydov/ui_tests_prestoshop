package broit.homework.core.configurations;

import broit.homework.core.managers.DefaultDriverConfigManager;
import broit.homework.core.managers.DockerDriverConfigManager;
import broit.homework.core.managers.DriverConfigManager;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@EnableConfigurationProperties(DriverConfigurationProperties.class)
@Configuration
public class DriverConfigManagerConfigurations {

    @Bean
    @Profile("docker")
    public DriverConfigManager dockerDriverConfigManager(DriverConfigurationProperties properties) {
        return new DockerDriverConfigManager(properties);
    }

    @Bean
    @ConditionalOnMissingBean(DriverConfigManager.class)
    public DriverConfigManager defaultDriverConfigManager(DriverConfigurationProperties properties) {
        return new DefaultDriverConfigManager(properties);
    }
}