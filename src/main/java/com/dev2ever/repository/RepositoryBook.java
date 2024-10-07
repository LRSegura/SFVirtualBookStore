package com.dev2ever.repository;

import com.dev2ever.model.Book;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Profile("production")
public interface RepositoryBook extends JpaRepository<Book, Long> {
}
