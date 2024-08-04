package com.dev2ever.sample.controller;

import com.dev2ever.sample.component.SampleComponentA;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class SampleRestController {
    private final SampleComponentA sampleComponentA;

    public SampleRestController(SampleComponentA sampleComponentA) {
        this.sampleComponentA = sampleComponentA;
    }

    @GetMapping()
    public String sayHello() {
        sampleComponentA.print();
        return "Hello, Spring Framework!";
    }
}


