package com.dev2ever;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("production")
@Import(JpaConfig.class)
public class ProductionAppConfig extends BeanInfo{
}
