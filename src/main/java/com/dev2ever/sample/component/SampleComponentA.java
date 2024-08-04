package com.dev2ever.sample.component;

import jakarta.annotation.PostConstruct;
import org.springframework.context.ApplicationContext;

import java.util.logging.Level;
import java.util.logging.Logger;

public class SampleComponentA {

    private SampleComponentB sampleComponentB;
    private final ApplicationContext applicationContext;

    Logger logger = Logger.getLogger(SampleComponentA.class.getName());

    public SampleComponentA(SampleComponentB sampleComponentB, ApplicationContext applicationContext) {
        this.sampleComponentB = sampleComponentB;
        this.applicationContext = applicationContext;
    }

    @PostConstruct
    public void postConstruct(){
        logger.warning("SampleComponentA postConstruct");
    }

    public void print(){
        logger.info("Print SampleComponentA");
        SampleComponentC componentC = applicationContext.getBean(SampleComponentC.class);
        SampleComponentC componentCc = applicationContext.getBean(SampleComponentC.class);
        componentC.print();
        logger.log(Level.INFO,"Testing singleton scope in componentC {0}", componentC.equals(componentCc));
        SampleComponentB componentBB = applicationContext.getBean(SampleComponentB.class);
        logger.log(Level.INFO,"Testing prototype scope in componentB. {0} ", sampleComponentB.equals(componentBB));
        logger.log(Level.INFO,"--------------------------------------------------------------------------------");
    }
}
