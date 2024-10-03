package com.dev2ever.service;

import org.springframework.data.jpa.repository.JpaRepository;

public class AbstractService<T extends JpaRepository<T, ID>, ID> {
}
