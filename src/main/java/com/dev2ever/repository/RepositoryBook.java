package com.dev2ever.repository;

import com.dev2ever.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryBook extends JpaRepository<Book, Long> {
}
