package com.example.categorymongo.Service;

import com.example.categorymongo.Entities.Category;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CategoryService {

    Mono<Category> create(Category category);
    Mono<Category> findById(String id);
    Flux<Category> findAll();
    Mono<Category> update(Category category, String id);
    Mono<Category> delete(String id);
}
