package com.example.categorymongo.Service;

import com.example.categorymongo.Entities.Category;
import com.example.categorymongo.Repository.CategoryRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CategoryServiceImpl implements  CategoryService{

    private CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Mono<Category> create(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Mono<Category> findById(String id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Flux<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Mono<Category> update(Category category, String id) {
        return categoryRepository.findById(id).map(value->new Category(value.getCategoryId(),category.getCategoryName()))
                .flatMap(value->categoryRepository.save(value)
                .thenReturn(value));
    }

    @Override
    public Mono<Category> delete(String id) {
        return categoryRepository
                .findById(id)
                .flatMap(value->categoryRepository
                        .deleteById(value.getCategoryId())
                        .thenReturn(value));
    }
}
