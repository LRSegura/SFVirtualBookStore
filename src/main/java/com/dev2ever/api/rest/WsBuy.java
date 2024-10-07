package com.dev2ever.api.rest;


import com.dev2ever.model.Buy;
import com.dev2ever.service.ServiceBuy;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("buy/")
@Profile("production")
public class WsBuy {

    private final ServiceBuy serviceBuy;

    public WsBuy(ServiceBuy ServiceBuy) {
        this.serviceBuy = ServiceBuy;
    }

    @GetMapping("find/id")
    public Buy getBuyById(@RequestParam("id") Long id) {
        return serviceBuy.findBuy(id);
    }

    @GetMapping("find/all")
    public List<Buy> getBooks() {
        return serviceBuy.findAll();
    }

    @PostMapping(consumes = "application/json", value = "save")
    public void saveBuy(@RequestBody JsonBuy buy) {
        serviceBuy.createBuy(buy);
    }

    @DeleteMapping("delete")
    public void deleteBookById(@RequestParam("id") Long id) {
        serviceBuy.deleteBuy(id);
    }
}
