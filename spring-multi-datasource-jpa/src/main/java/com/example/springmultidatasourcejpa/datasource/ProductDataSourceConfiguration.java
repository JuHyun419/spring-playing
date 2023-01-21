package com.example.springmultidatasourcejpa.datasource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@EnableJpaRepositories(
        basePackages = "com.example.springmultidatasourcejpa.product.repository",
        entityManagerFactoryRef = "secondEntityManager",
        transactionManagerRef = "secondTransactionManager"
)
@Configuration
public class ProductDataSourceConfiguration {

    @Bean
    public PlatformTransactionManager secondTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(secondEntityManager().getObject());

        return transactionManager;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean secondEntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(secondDataSource());
        em.setPackagesToScan("com.example.springmultidatasourcejpa.product.entity");
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        return em;
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource-second.hikari")
    public DataSource secondDataSource() {
        return DataSourceBuilder.create().build();
    }

}
