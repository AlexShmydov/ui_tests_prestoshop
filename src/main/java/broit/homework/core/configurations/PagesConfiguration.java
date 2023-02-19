package broit.homework.core.configurations;

import broit.homework.core.pages.main.MainPage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PagesConfiguration {

    @Bean
    public MainPage mainPage(@Value("${device}") String device) {
        return new MainPage(device);
    }
}
