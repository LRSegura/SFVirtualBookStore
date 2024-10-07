package com.dev2ever.sample.component;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
@Profile("test")
public class SampleComponentX {

    Logger logger = Logger.getLogger(SampleComponentX.class.getName());

    @PostConstruct
    public void postConstruct(){
        logger.warning("SampleComponentX postConstruct");
    }
}
