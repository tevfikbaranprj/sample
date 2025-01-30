package de.tvbr.configuration.api3;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(
        basePackages = "de.tvbr.entities.repositories.api3.repository3",
        entityManagerFactoryRef = "api1EntityManager",
        transactionManagerRef = "api1TransactionManager"
)
public class Api3DbConfig {



}
