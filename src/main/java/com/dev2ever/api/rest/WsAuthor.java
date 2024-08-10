package com.dev2ever.api.rest;

import com.dev2ever.model.Author;
import com.dev2ever.service.ServiceAuthor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("author")
public class WsAuthor {

    private final ServiceAuthor serviceAuthor;

    public WsAuthor(ServiceAuthor serviceAuthor) {
        this.serviceAuthor = serviceAuthor;
    }

    @GetMapping
    public Author getAuthor() {
        Author author = new Author();
        author.setName("Luis");
        return author;
    }

    @PostMapping(consumes = "application/json")
    public void postAuthor(@RequestBody Author author) {
        serviceAuthor.save(author);
    }
}
