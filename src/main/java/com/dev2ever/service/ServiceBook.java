package com.dev2ever.service;

import com.dev2ever.api.rest.JsonBook;
import com.dev2ever.model.Author;
import com.dev2ever.model.Book;
import com.dev2ever.repository.RepositoryAuthor;
import com.dev2ever.repository.RepositoryBook;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceBook {

    private final RepositoryBook repositoryBook;
    private final RepositoryAuthor repositoryAuthor;

    public ServiceBook(RepositoryBook repositoryBook, RepositoryAuthor repositoryAuthor) {
        this.repositoryBook = repositoryBook;
        this.repositoryAuthor = repositoryAuthor;
    }

    public void save(JsonBook jsonBook) {
        Book book = new Book();
        book.setTitle(jsonBook.title());
        book.setPublisher(jsonBook.publisher());
        book.setYear(jsonBook.year());
        Author author = repositoryAuthor.findById(jsonBook.idAuthor()).orElseThrow(() -> new RuntimeException("Author not found"));
        book.setAuthor(author);
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
