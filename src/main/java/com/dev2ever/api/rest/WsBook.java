package com.dev2ever.api.rest;

import com.dev2ever.model.Book;
import com.dev2ever.service.ServiceBook;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("book/")
public class WsBook {

    private final ServiceBook serviceBook;

    public WsBook(ServiceBook serviceBook) {
        this.serviceBook = serviceBook;
    }

    @GetMapping("find/id")
    public Book getBookById(@RequestParam("id") Long id) {
        return serviceBook.findById(id);
    }

    @GetMapping("find/all")
    public List<Book> getBooks() {
        return serviceBook.findAll();
    }

    @PostMapping(consumes = "application/json", value = "save")
    public void saveBook(@RequestBody JsonBook book) {
        serviceBook.save(book);
    }

    @DeleteMapping("delete")
    public void deleteBookById(@RequestParam("id") Long id) {
        serviceBook.deleteById(id);
    }
}
