package com.dev2ever;


import com.dev2ever.sample.SampleAppConfig;
import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.dev2ever")
@Import({SampleAppConfig.class, ProductionAppConfig.class, AspectConfig.class})
@PropertySource("classpath:app.properties")
public class AppConfig extends BeanInfo implements WebMvcConfigurer {


}


