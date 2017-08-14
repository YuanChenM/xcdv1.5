package com.msk.common.data.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;
import java.util.Map;

@EntityScan(basePackages="com.msk.*.entity")
@EnableJpaRepositories(
        repositoryFactoryBeanClass = RepositoryFactory.class,
        basePackages= "com.msk.*.repository",
        entityManagerFactoryRef="entityManagerFactoryPrimary")
public class JpaConfig {
    @Autowired
    private JpaProperties jpaProperties;
    @Autowired
    private DataSource dataSource;

    @Bean(name="entityManagerFactoryPrimary")
    @Primary
    public LocalContainerEntityManagerFactoryBean customerEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder.dataSource(dataSource)
                .properties(getVendorProperties(dataSource))
                .build();
    }

    private Map<String, String> getVendorProperties(DataSource dataSource) {
        return jpaProperties.getHibernateProperties(dataSource);
    }
}
