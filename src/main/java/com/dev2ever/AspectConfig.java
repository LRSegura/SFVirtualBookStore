package com.dev2ever;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
//@EnableAspectJAutoProxy(proxyTargetClass=true)
@EnableAspectJAutoProxy()
@ComponentScan(basePackages="com.dev2ever.component.aspects")
public class AspectConfig {
}
