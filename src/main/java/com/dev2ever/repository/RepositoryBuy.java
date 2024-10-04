package com.dev2ever.repository;

import com.dev2ever.model.Buy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryBuy extends JpaRepository<Buy, Long> {
}
