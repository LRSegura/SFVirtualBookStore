package com.dev2ever.component.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class PropertyChangeTracker {

    Logger logger = Logger.getLogger(PropertyChangeTracker.class.getName());

    @Before("execution(void com.dev2ever.service.*.*(..))")
    public void trackChange(){
        logger.info("Tracking call method");
    }

    @Before("execution(void com.dev2ever.model.*.set(..))")
    public void trackChangeWithParam(JoinPoint joinPoint){
        String methodName = joinPoint.getSignature().getName();
        Object value = joinPoint.getArgs()[0];
        String message = String.format("method name %s. Value %s", methodName, value);
        logger.info(message);
    }
}
