package com.example.categorymongo;

import com.example.categorymongo.Entities.Category;
import com.example.categorymongo.Repository.CategoryRepository;
import com.example.categorymongo.Service.CategoryService;
import com.example.categorymongo.Service.CategoryServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceTest {

    private CategoryService categoryService;
    private CategoryRepository categoryRepository;

    @Before
    public void setUp() throws Exception {
        categoryRepository = Mockito.mock(CategoryRepository.class);
        categoryService = Mono.fromSupplier(()-> new CategoryServiceImpl(categoryRepository))
                .subscribeOn(Schedulers.elastic())
                .block();

    }
    @Test
    public void CreateTest(){
        Category a = Mono.fromSupplier(()->getCategory())
                .subscribeOn(Schedulers.elastic())
                .block();
        Mockito.when(categoryRepository.save(a).thenReturn(a));
        Category b = categoryService.create(a).block();
        Assert.assertTrue(b !=null);
        Assert.assertTrue(a.getCategoryId()==b.getCategoryId());
        Mockito.verify(categoryRepository, Mockito.times(1)).save(a);
    }
    public Category getCategory(){
        return new Category("1","Makanan");
    }
//  map string -> int
//  flat map string->flux or shomething
//    @Test
//    public void FindByIdTest(){
//        Member a = new Member();
//        a.setMemberAddress("jkl;");
//        a.setMemberEmail("asdf");
//        a.setMemberGender("Perempuan");
//        a.setMemberName("Roka");
//        a.setMemberPassword("Coklat bulat");
//        Mockito.when(memberRepository.findById(1L)).thenReturn(Optional.of(a));
//        Mockito.when(memberRepository.findById(2L)).thenReturn(Optional.empty());
//
//        Member result1 = service.findById(1L);
//        Assert.assertTrue(result1!=null);
//
//        Member result2 = service.findById(2L);
//        Assert.assertTrue(result2 ==null );
//
//        Mockito.verify(memberRepository,Mockito.times(1)).findById(1L);
//        Mockito.verify(memberRepository,Mockito.times(1)).findById(2L);
//    }
//    @Test
//    public void FindAllTest(){
//        Member a = new Member();
//        a.setMemberAddress("jkl;");
//        a.setMemberEmail("asdf");
//        a.setMemberGender("Perempuan");
//        a.setMemberName("Roka");
//        a.setMemberPassword("Coklat bulat");
//        service.create(a);
//        Member b = new Member();
//        b.setMemberAddress("zcxv");
//        b.setMemberEmail("qewr");
//        b.setMemberGender("Laki Laki");
//        b.setMemberName("Roti");
//        b.setMemberPassword("Coklat pisang");
//        service.create(b);
//        Assert.assertTrue(service.findAll().size()==2);
//    }
//    @Test
//    public void UpdateTest(){
//        Member a = new Member();
//        a.setMemberAddress("jkl;");
//        a.setMemberEmail("asdf");
//        a.setMemberGender("Perempuan");
//        a.setMemberName("Roka");
//        a.setMemberPassword("Coklat bulat");
//        service.create(a);
//        Member b = new Member();
//        b.setMemberAddress("zcxv");
//        b.setMemberEmail("qewr");
//        b.setMemberGender("Laki Laki");
//        b.setMemberName("Roti");
//        b.setMemberPassword("Coklat pisang");
//        Member c= service.update(b,b.getMemberId());
//        Assert.assertTrue(c!=null);
//        Assert.assertTrue(b==c);
//    }
//
//    @Test
//    public void DeleteTest() {
//
//        Member a = new Member();
//        a.setMemberAddress("jkl;");
//        a.setMemberEmail("asdf");
//        a.setMemberGender("Perempuan");
//        a.setMemberName("Roka");
//        a.setMemberPassword("Coklat bulat");
//        service.create(a);
//        Member b = new Member();
//        b.setMemberAddress("zcxv");
//        b.setMemberEmail("qewr");
//        b.setMemberGender("Laki Laki");
//        b.setMemberName("Roti");
//        b.setMemberPassword("Coklat pisang");
//        service.create(b);
//        service.delete(3L);
//        service.delete(1L);
//        Assert.assertTrue(service.findAll().size() == 1);
//    }
}
