package de.tvbr.configuration.api1;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        basePackages = "de.tvbr.entities.repositories.api1", //repository package
        entityManagerFactoryRef = "api1EntityManager", //
        transactionManagerRef = "api1TransactionManager"
)
public class Api1DbConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.api1-db")
    public DataSource api1DataSource() {

        return DataSourceBuilder.create().build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean api1EntityManager(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(api1DataSource())
                .packages("de.tvbr.entities.api1")
                .persistenceUnit("api1")
                .build();
    }

    @Bean
    public PlatformTransactionManager api1TransactionManager(
            @Qualifier("api1EntityManager")EntityManagerFactory entityManagerFactory
            )   {
        return new JpaTransactionManager(entityManagerFactory);
    }

}
