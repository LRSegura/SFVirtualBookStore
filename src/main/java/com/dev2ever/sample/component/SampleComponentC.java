package com.dev2ever.sample.component;

import jakarta.annotation.PostConstruct;

import java.util.logging.Logger;

public class SampleComponentC {

    Logger logger = Logger.getLogger(SampleComponentC.class.getName());
    private SampleComponentD sampleComponentD;

    public SampleComponentC(SampleComponentD sampleComponentD) {
        this.sampleComponentD = sampleComponentD;
    }

    @PostConstruct
    public void postConstruct(){
        logger.warning("SampleComponentC postConstruct");
    }

    public void print(){
        logger.info("Print SampleComponentC");
    }
}
