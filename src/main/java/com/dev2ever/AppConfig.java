package com.dev2ever;


import com.dev2ever.sample.SampleAppConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.dev2ever")
@Import({SampleAppConfig.class, JpaConfig.class})
@PropertySource("classpath:app.properties")
public class AppConfig implements WebMvcConfigurer {


}


