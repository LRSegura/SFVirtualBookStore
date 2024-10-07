package com.dev2ever.service;

import com.dev2ever.model.Author;
import com.dev2ever.repository.RepositoryAuthor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Profile("production")
public class ServiceAuthor {

    private final RepositoryAuthor repositoryAuthor;

    public ServiceAuthor(RepositoryAuthor repositoryAuthor) {
        this.repositoryAuthor = repositoryAuthor;
    }

    public void save(Author author) {
        repositoryAuthor.save(author);
    }

    public void deleteById(Long id) {
        repositoryAuthor.deleteById(id);
    }

    public void update(Author author) {
        repositoryAuthor.save(author);
    }

    public Author getById(Long id) {
        return repositoryAuthor.findById(id).orElse(null);
    }
    public List<Author> getAll() {
        return repositoryAuthor.findAll();
    }
}
