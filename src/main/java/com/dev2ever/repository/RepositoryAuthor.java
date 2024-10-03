package com.dev2ever.repository;

import com.dev2ever.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryAuthor extends JpaRepository<Author, Long> {
}
