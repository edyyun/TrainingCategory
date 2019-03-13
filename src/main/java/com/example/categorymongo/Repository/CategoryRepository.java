package com.example.categorymongo.Repository;

import com.example.categorymongo.Entities.Category;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface CategoryRepository extends ReactiveMongoRepository<Category,String> {
}
