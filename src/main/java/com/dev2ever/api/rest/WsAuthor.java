package com.dev2ever.api.rest;

import com.dev2ever.model.Author;
import com.dev2ever.service.ServiceAuthor;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("author/")
@Profile("production")
public class WsAuthor {

    private final ServiceAuthor serviceAuthor;

    public WsAuthor(ServiceAuthor serviceAuthor) {
        this.serviceAuthor = serviceAuthor;
    }

    @GetMapping("find/id")
    public Author getAuthorById(@RequestParam("id") Long id) {
        return serviceAuthor.getById(id);
    }

    @GetMapping("find/all")
    public List<Author> getAllAuthors() {
        return serviceAuthor.getAll();
    }

    @PostMapping(consumes = "application/json", value = "save")
    public void saveAuthor(@RequestBody Author author) {
        serviceAuthor.save(author);
    }

    @DeleteMapping("delete")
    public void deleteAuthorById(@RequestParam("id") Long id) {
        serviceAuthor.deleteById(id);
    }
}
