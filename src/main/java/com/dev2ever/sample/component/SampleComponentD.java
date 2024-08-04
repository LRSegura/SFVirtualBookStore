package com.dev2ever.sample.component;

import jakarta.annotation.PostConstruct;

import java.util.logging.Logger;

public class SampleComponentD {

    Logger logger = Logger.getLogger(SampleComponentD.class.getName());

    @PostConstruct
    public void postConstruct(){
        logger.warning("SampleComponentD postConstruct");
    }
}
