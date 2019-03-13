package com.example.categorymongo.Controller;

import com.example.categorymongo.Entities.ApiKey;
import com.example.categorymongo.Entities.Category;
import com.example.categorymongo.Service.CategoryService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@RestController
public class CategoryController {
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @RequestMapping(
            value = "/category",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public Mono<Category> create(@RequestBody Category category){
        return categoryService.create(category).subscribeOn(Schedulers.elastic());
    }
    @RequestMapping(
            value = "/categories",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Flux<Category> findAll(ApiKey apiKey){
        return categoryService.findAll().subscribeOn(Schedulers.elastic());
    }
    @RequestMapping(
            value = "/categories/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Mono<Category> findById(@PathVariable("id")String id){
        return categoryService.findById(id).subscribeOn(Schedulers.elastic());
    }
    @RequestMapping(
            value = "/categories/update/{id}",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public Mono<Category> update(@RequestBody Category category, @PathVariable("id")String id) {
        return categoryService.update(category,id).subscribeOn(Schedulers.elastic());
    }
    @RequestMapping(
            value = "/categories/delete/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Mono<Category> delete(@PathVariable("id") String id){

        return categoryService.delete(id).subscribeOn(Schedulers.elastic());
    }


}
