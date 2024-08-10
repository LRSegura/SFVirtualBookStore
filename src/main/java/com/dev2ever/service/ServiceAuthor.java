package com.dev2ever.service;

import com.dev2ever.model.Author;
import com.dev2ever.repository.RepositoryAuthor;
import org.springframework.stereotype.Service;

@Service
public class ServiceAuthor {

    private RepositoryAuthor repositoryAuthor;

    public ServiceAuthor(RepositoryAuthor repositoryAuthor) {
        this.repositoryAuthor = repositoryAuthor;
    }

    public void save(Author author) {
        repositoryAuthor.save(author);
    }
}
