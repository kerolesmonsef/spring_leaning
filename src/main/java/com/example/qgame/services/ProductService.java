package com.example.qgame.services;

import com.example.qgame.Models.Product;
import com.example.qgame.repositories.ProductRepository;
import javassist.runtime.Desc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    @Autowired
    private HttpServletRequest servletRequest;

    public Page<Product> getPageable() {
        String sortColumn = Optional.ofNullable(servletRequest.getParameter("sort")).orElse("id");
        String pageString = Optional.ofNullable(servletRequest.getParameter("page")).orElse("1");
        String directionString = Optional.ofNullable(servletRequest.getParameter("direction")).orElse("desc");

        Direction direction = Direction.fromString(directionString);

        int page = Integer.parseInt(pageString);

        Pageable pageable = PageRequest.of(page, 10, Sort.by(direction, sortColumn));

        return repository.findAll(pageable);
    }
}
