package com.example.qgame.validations.impls;

import com.example.qgame.validations.Exists;
import com.example.qgame.validations.Unique;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class UniqueValidator implements ConstraintValidator<Unique, Object> {

    @PersistenceContext
    private EntityManager entityManager;

    private String entity;
    private String column;


    @Override
    public void initialize(Unique exists) {
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

        changeMessage(context, "the value '%s' is exists in %s".formatted(stringValue,entity));

        return !query.getSingleResult();
    }

    private void changeMessage(ConstraintValidatorContext context, String message) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
    }
}
