package com.dev2ever.sample;

import com.dev2ever.BeanInfo;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
@Profile("test")
public class JpaConfig extends BeanInfo {

    @Bean
    public DataSource dataSource(JpaProperties jpaProperties) {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(jpaProperties.getDriver());
        dataSource.setUrl(jpaProperties.getUrl());
        dataSource.setUsername(jpaProperties.getUsername());
        dataSource.setPassword(jpaProperties.getPassword());
        dataSource.setInitialSize(5);
        dataSource.setMinIdle(5);
        dataSource.setMaxIdle(5);
        dataSource.setMaxTotal(10);
        return dataSource;
    }


}
