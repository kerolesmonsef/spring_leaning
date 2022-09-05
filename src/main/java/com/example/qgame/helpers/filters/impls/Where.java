package com.example.qgame.helpers.filters.impls;

import com.example.qgame.helpers.enums.OperationType;
import com.example.qgame.helpers.filters.IFilter;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;

public class Where extends IFilter {

    private Path column;
    private String value;
    private OperationType operationType = OperationType.EQUAL;

    public Where(Path column, String value, boolean check) {
        super(check);

        this.column = column;
        this.value = value;
    }

    public Where(Path column, OperationType operationType, String value, boolean check) {
        this(column, value, check);
        this.operationType = operationType;
    }

    @Override
    protected Predicate getPredict() {
        if (operationType == OperationType.LESS_THAN) {
            return cb.lessThan(column, value);
        }else if(operationType == OperationType.GREATER_THAN){
            return cb.greaterThan(column, value);
        }
        return cb.equal(column, value);
    }
}
