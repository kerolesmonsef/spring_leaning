package com.example.qgame.helpers.filters;

import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;

public abstract class IFilter {

    @Setter
    @Accessors(chain = true)
    protected CriteriaBuilder cb;

    protected boolean askCheck;

    public IFilter(boolean askCheck) {
        this.askCheck = askCheck;
    }


    public final void execute(){
        if (askCheck){
            this.getPredict();
        }
    }

    protected abstract Predicate getPredict();
}
