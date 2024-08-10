package com.dev2ever.service;

import com.dev2ever.model.Book;
import com.dev2ever.repository.RepositoryBook;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceBook {

    private final RepositoryBook repositoryBook;

    public ServiceBook(RepositoryBook repositoryBook) {
        this.repositoryBook = repositoryBook;
    }

    public void save(Book book) {
        repositoryBook.save(book);
    }

    public void deleteById(Long id) {
        repositoryBook.deleteById(id);
    }
    public Book findById(Long id) {
        return repositoryBook.findById(id).orElse(null);
    }
    public List<Book> findAll() {
        return repositoryBook.findAll();
    }
}
