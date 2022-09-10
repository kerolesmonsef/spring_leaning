package com.example.qgame.controllers;

import com.example.qgame.Models.Product;
import com.example.qgame.Models.Test;
import com.example.qgame.Models.User;
import com.example.qgame.helpers.database.seeders.ISeeder;
import com.example.qgame.helpers.database.seeders.SeederExecuter;
import com.example.qgame.helpers.database.seeders.impls.BlogSeeder;
import com.example.qgame.helpers.database.seeders.impls.CategorySeeder;
import com.example.qgame.helpers.database.seeders.impls.OptionSeeder;
import com.example.qgame.helpers.database.seeders.impls.ProductSeeder;
import com.example.qgame.helpers.entityembadable.ITestInter;
import com.example.qgame.helpers.entityembadable.TestFile;
import com.example.qgame.helpers.enums.OperationType;
import com.example.qgame.helpers.filters.FilterBuilder;
import com.example.qgame.helpers.filters.impls.TestWhere;
import com.example.qgame.helpers.filters.impls.Where;
import com.example.qgame.helpers.filters.impls.WhereLike;
import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/test")
public class TestController {

    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    //    @ResponseBody
    @GetMapping("/test")
    @Transactional
    @ResponseBody
    public Object test() {


        Test test = entityManager.find(Test.class,4L);
return test;
//        Test test = new Test();
//        ITestInter inter = new TestFile("name");
//        test.setImages(inter);
//        entityManager.persist(test);
//        return test;
//        return "/task/list.html";
//        HttpServletRequest request;
//
//
//        CriteriaQuery<Object> criteria = entityManager.getCriteriaBuilder().createQuery(Object.class);
//        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//        Root<Product> p = criteria.from(Product.class);
//        criteria.select(p);
////                .where(
////                cb.and(
////                        cb.equal(p.get("price"),23),
////                        cb.or(
////                              cb.gt(p.get("price"),33),
////                              cb.gt(p.get("price"),33)
////                        )
////                )
////        );
//
//
//        Query query = new FilterBuilder(criteria, entityManager)
//                .addFilter(new WhereLike(p.get("title"), "%entat%", true))
//                .addFilter(new Where(p.get("price"), "23", true))
//                .addFilter(new Where(p.get("price"), OperationType.GREATER_THAN, "23", true))
//                .addFilter(new Where(p.get("price"), "23", true))
//                .addFilter(new Where(p.get("price"), "23", true))
//                .addFilter(new Where(p.get("price"), "23", true))
//                .addFilter(new TestWhere(true).setR(p))
//                .buildQuery();
//
//
//        List<Product> items = query.getResultList();
//
//        return items;


//        return ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
    }

    @ResponseBody
    @GetMapping("/seed")
    public void seed() {
        List<ISeeder> seeders = Arrays.asList(
                new CategorySeeder(),
                new OptionSeeder(),
                new ProductSeeder(),
                new BlogSeeder()
        );


        new SeederExecuter(seeders).execute();
    }


}
