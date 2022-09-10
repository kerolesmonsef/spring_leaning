package com.example.qgame.helpers.paginations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Component
public class PaginationMaker<T> {

    @Autowired
    HttpServletRequest servletRequest;

    public <ID> Pagination<T> makeFromJpaRepository(JpaRepository<T, ID> jpaRepository, String url) {

        String sortColumn = Optional.ofNullable(servletRequest.getParameter("sort")).orElse("id");
        String pageString = Optional.ofNullable(servletRequest.getParameter("page")).orElse("0");
        String directionString = Optional.ofNullable(servletRequest.getParameter("direction")).orElse("desc");
        Sort.Direction direction = Sort.Direction.fromString(directionString);
        int pageNumber = Integer.parseInt(pageString);
        Pageable pageable = PageRequest.of(pageNumber, 15, Sort.by(direction, sortColumn));

        Page<T> page = jpaRepository.findAll(pageable);

        return new Pagination<T>(page, url);
    }

}
