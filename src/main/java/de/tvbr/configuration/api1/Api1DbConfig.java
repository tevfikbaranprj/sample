package de.tvbr.configuration.api1;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(
        basePackages = "de.tvbr.entities.repositories.api1.repository1",
        entityManagerFactoryRef = "api1EntityManager",
        transactionManagerRef = "api1TransactionManager"
)
public class Api1DbConfig {



}
