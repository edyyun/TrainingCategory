package com.example.categorymongo;

import com.example.categorymongo.Entities.Category;
import com.example.categorymongo.Repository.CategoryRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryRepositoryTest {
    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void testSaveUpdate(){
        categoryRepository.deleteAll().block();
        Category category = new Category("1","Minuman");

        Category result = categoryRepository.save(category).block();
        Assert.assertTrue(result ==category);

        category.setCategoryName("Makanan");
        result = categoryRepository.save(category).block();
        Assert.assertTrue(result==category);
    }

    @Test
    public void testFindAll() {
        categoryRepository.deleteAll().block();

        categoryRepository.save(new Category("1","Minuman")).block();
        categoryRepository.save(new Category("2","Makanan")).block();
        List<Category> result = categoryRepository.findAll().collectList().block();

        Assert.assertTrue(result.size()==2);
    }

    @Test
    public void testDelete() {
        categoryRepository.deleteAll().block();

        categoryRepository.save(new Category("1","Minuman")).block();
        categoryRepository.save(new Category("2","Makanan")).block();
        categoryRepository.save(new Category("3","Obat-obatan")).block();
        categoryRepository.delete(new Category("3","Obat-obatan")).block();
        List<Category> result = categoryRepository.findAll().collectList().block();

        Assert.assertTrue(result.size()==2);
    }
}
