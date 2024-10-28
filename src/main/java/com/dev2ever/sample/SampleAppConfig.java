package com.dev2ever.sample;

import com.dev2ever.BeanInfo;
import com.dev2ever.sample.component.SampleComponentA;
import com.dev2ever.sample.component.SampleComponentB;
import com.dev2ever.sample.component.SampleComponentC;
import com.dev2ever.sample.component.SampleComponentD;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;

@Configuration
@Profile("test")
@Import(JpaConfig.class)
public class SampleAppConfig extends BeanInfo {

    @Bean
    public SampleComponentA sampleComponentA(ApplicationContext applicationContext){
        return new SampleComponentA(sampleComponentB(), applicationContext);
    }

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
