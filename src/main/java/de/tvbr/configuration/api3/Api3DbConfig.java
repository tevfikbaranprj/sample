package de.tvbr.configuration.api3;

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
        basePackages = "de.tvbr.entities.repositories.api3",
        entityManagerFactoryRef = "api3EntityManager",
        transactionManagerRef = "api3TransactionManager"
)
public class Api3DbConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.api3-db")
    public DataSource api3DataSource() {

        return DataSourceBuilder.create().build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean api3EntityManager(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(api3DataSource())
                .packages("de.tvbr.entities.api3")
                .persistenceUnit("api3")
                .build();
    }

    @Bean
    public PlatformTransactionManager api3TransactionManager(
            @Qualifier("api3EntityManager") EntityManagerFactory entityManagerFactory
    )   {
        return new JpaTransactionManager(entityManagerFactory);
    }

}
