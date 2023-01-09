package com.example.qgame.controllers;

import com.example.qgame.Models.Product;
import com.example.qgame.Models.Test;
import com.example.qgame.Models.User;
import com.example.qgame.helpers.database.seeders.ISeeder;
import com.example.qgame.helpers.database.seeders.SeederExecuter;
import com.example.qgame.helpers.database.seeders.impls.*;
import com.example.qgame.helpers.entityembadable.ITestInter;
import com.example.qgame.helpers.entityembadable.TestFile;
import com.example.qgame.helpers.enums.OperationType;
import com.example.qgame.helpers.filters.FilterBuilder;
import com.example.qgame.helpers.filters.impls.TestWhere;
import com.example.qgame.helpers.filters.impls.Where;
import com.example.qgame.helpers.filters.impls.WhereLike;
import com.example.qgame.repositories.ProductRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpUtils;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/test")
@ControllerAdvice
public class TestController {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    ProductRepository repository;


    @GetMapping("/index")
    public String index() {
        return "index";
    }

    //    @ResponseBody
    @GetMapping("/test")
    @Transactional
    @ResponseBody
    public Object test() {
        return repository.findById(1L);

    }

}
