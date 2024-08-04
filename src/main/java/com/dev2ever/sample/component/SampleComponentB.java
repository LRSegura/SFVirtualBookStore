package com.dev2ever.sample.component;

import jakarta.annotation.PostConstruct;

import java.util.logging.Logger;

public class SampleComponentB {

    Logger logger = Logger.getLogger(SampleComponentB.class.getName());

    @PostConstruct
    public void postConstruct(){
        logger.warning("SampleComponentB postConstruct");
    }

    public void print(){
        logger.warning("SampleComponentB print");
    }
}
