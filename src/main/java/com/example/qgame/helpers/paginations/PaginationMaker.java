package com.example.qgame.helpers.paginations;

import com.example.qgame.Models.Product;
import com.example.qgame.helpers.dto.MinMaxResult;
import com.example.qgame.helpers.filters.products.FilterQueryBuilderResult;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

@Component
public class PaginationMaker<T> {

    @Autowired
    HttpServletRequest servletRequest;

    @PersistenceContext
    private EntityManager entityManager;


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


    public Pagination<T> makeFromQueryBuilderResult(FilterQueryBuilderResult result) {
        final int MAX_SIZE = 12;
        String pageString = Optional.ofNullable(servletRequest.getParameter("page")).orElse("0");
        int pageNumber = Integer.parseInt(pageString);
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        Pageable pageable = PageRequest.of(pageNumber, MAX_SIZE);


        result.getCriteria().select(cb.count(result.getProductRoot()));
        TypedQuery<Long> query = entityManager.createQuery(result.getCriteria());
        Long count = query.getSingleResult();


        result.getCriteria().select(result.getProductRoot());
        Query<T> productTypedQuery = entityManager
                .createQuery(result.getCriteria())
                .unwrap(Query.class)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize());

        List<T> products = productTypedQuery.list();

        Page<T> page = new PageImpl<>(products, pageable, count);

        return new Pagination<T>(page, "/products/shop");
    }
}
