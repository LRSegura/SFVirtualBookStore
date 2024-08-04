package com.dev2ever.sample;

import com.dev2ever.sample.component.SampleComponentA;
import com.dev2ever.sample.component.SampleComponentB;
import com.dev2ever.sample.component.SampleComponentC;
import com.dev2ever.sample.component.SampleComponentD;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class SampleAppConfig {

    @Bean
    public SampleComponentA sampleComponentA(ApplicationContext applicationContext){
        return new SampleComponentA(sampleComponentB(), applicationContext);
    }

//    @Bean
//    public SampleComponentA sampleComponentAA(SampleComponentB sampleComponentB){
//        return new SampleComponentA(sampleComponentB);
//    }

    @Bean
    @Scope("prototype")
    public SampleComponentB sampleComponentB(){
        return new SampleComponentB();
    }

    @Bean
    public SampleComponentC sampleComponentC(SampleComponentD sampleComponentD){
        return new SampleComponentC(sampleComponentD);
    }

    @Bean
    public SampleComponentD sampleComponentD(){
        return new SampleComponentD();
    }
}
