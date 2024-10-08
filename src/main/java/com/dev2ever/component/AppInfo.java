package com.dev2ever.component;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@ToString
@Getter
@Component
public class AppInfo {
    Logger logger = Logger.getLogger(AppInfo.class.getName());

    @Value("${app.name}")
    private String appName;

    @Value("${app.version}")
    private String appVersion;

    @Value("${app.programmer:Luis}")
    private String appDescription;

    @Value("${HOME: Home directory not defined}")
    private String homeDirectory;

    @Value("#{systemProperties['user.name']}")
    private String userName;

    @PostConstruct
    public void printAppInfo() {
        logger.info(this::toString);
    }
}
