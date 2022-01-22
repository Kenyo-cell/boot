package ru.netology.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.netology.profile.DevProfile;
import ru.netology.profile.ProductionProfile;
import ru.netology.profile.SystemProfile;

@Configuration
public class AppConfig {
    @Bean
    @ConditionalOnProperty(prefix = "system", name = "profile", havingValue = "dev")
    public SystemProfile devProfile() {
        return new DevProfile();
    }

    @Bean
    @ConditionalOnProperty(prefix = "system", name = "profile", havingValue = "production")
    public SystemProfile productionProfile() {
        return new ProductionProfile();
    }
}
