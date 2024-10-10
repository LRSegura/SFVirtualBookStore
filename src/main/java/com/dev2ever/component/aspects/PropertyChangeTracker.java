package com.dev2ever.component.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class PropertyChangeTracker {

    Logger logger = Logger.getLogger(PropertyChangeTracker.class.getName());

    @Before("execution(void set*(*))")
    public void trackChange(){
        logger.info("Tracking changes");
    }
}
