package com.example.qgame.validations.impls;

import com.example.qgame.validations.Exists;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class ExistsValidator implements ConstraintValidator<Exists, Object> {

    @PersistenceContext
    private EntityManager entityManager;

    private String entity;
    private String column;


    @Override
    public void initialize(Exists exists) {
        entity = exists.entity();
        column = exists.column();

    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        String stringValue = value.toString();

        String sql = "SELECT COUNT(e) > 0  FROM %s e WHERE e.%s = '%s'".formatted(entity, column, stringValue);
        TypedQuery<Boolean> query = entityManager.createQuery(sql).unwrap(Query.class);

        return query.getSingleResult();
    }
}
