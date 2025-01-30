package de.tvbr.configuration.api2;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(
        basePackages = "de.tvbr.entities.repositories.api2.repository2",
        entityManagerFactoryRef = "api1EntityManager",
        transactionManagerRef = "api1TransactionManager"
)
public class Api2DbConfig {

    

}
