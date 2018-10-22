package com.mbsoft.activemq.administrator.infrastructure.spring.configuration;

import com.mbsoft.activemq.administrator.core.Administrator;
import com.mbsoft.activemq.administrator.infrastructure.activemq.ActiveMQAdministrator;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfig {
    @Bean
    ActiveMQConnectionFactory activeMQConnectionFactory() {
        return new ActiveMQConnectionFactory("admin", "admin",
                "tcp://DESKTOP-V9B0AR7:61616");
    }

    @Bean
    Administrator administrator(ActiveMQConnectionFactory activeMQConnectionFactory) {
        return new ActiveMQAdministrator(activeMQConnectionFactory);
    }
}
