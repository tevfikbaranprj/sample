package de.tvbr.configuration.api2;

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
        basePackages = "de.tvbr.entities.repositories.api2",
        entityManagerFactoryRef = "api2EntityManager",
        transactionManagerRef = "api2TransactionManager"
)
public class Api2DbConfig {


    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.api2-db")
    public DataSource api2DataSource() {

        return DataSourceBuilder.create().build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean api2EntityManager(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(api2DataSource())
                .packages("de.tvbr.entities.api2")
                .persistenceUnit("api2")
                .build();
    }

    @Bean
    public PlatformTransactionManager api2TransactionManager(
            @Qualifier("api2EntityManager") EntityManagerFactory entityManagerFactory
    )   {
        return new JpaTransactionManager(entityManagerFactory);
    }

}
