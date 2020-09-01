package com.tru.spring5.films.repositories;

import com.tru.spring5.films.POJO.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, Long> {
   Optional<Category> findByDescription (String description);
}
