package com.dev2ever;

import jakarta.annotation.PostConstruct;

import java.util.logging.Logger;

public abstract class BeanInfo {
    Logger logger = Logger.getLogger(BeanInfo.class.getName());

    @PostConstruct
    public void printInfo(){
        logger.info("BeanInfo: Loaded bean " + this.getClass().getName());
    }
}
