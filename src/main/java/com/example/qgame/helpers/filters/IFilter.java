package com.example.qgame.helpers.filters;

import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

public abstract class IFilter {
    @Setter
    @Accessors(chain = true)
    protected CriteriaQuery<Object> criteriaQuery;

    @Setter
    @Accessors(chain = true)
    protected CriteriaBuilder cb;

    protected boolean askCheck;

    public IFilter(boolean askCheck) {
        this.askCheck = askCheck;
    }


    public final void execute(){
        if (askCheck){
            this.filter();
        }
    }

    protected abstract void filter();
}
