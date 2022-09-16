package com.example.qgame.helpers.filters.products;

import com.example.qgame.QGameApplication;
import com.example.qgame.helpers.filters.products.filterOptionsImpls.PriceFilterOption;
import com.example.qgame.helpers.filters.products.results.MinMaxResult;
import org.hibernate.Session;
import org.hibernate.transform.AliasToBeanConstructorResultTransformer;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.hibernate.transform.AliasToEntityMapResultTransformer;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class FilterOptionFilter {

    private FilterQueryBuilderResult filterQueryBuilderResult;
    private EntityManager entityManager;
    private CriteriaBuilder cb;
    private Root<Object> productRoot;

    public FilterOptionFilter(FilterQueryBuilderResult filterQueryBuilderResult) {
        this.filterQueryBuilderResult = filterQueryBuilderResult;
        this.entityManager = QGameApplication.getContext().getBean(EntityManager.class);
        this.cb = entityManager.getCriteriaBuilder();
        this.productRoot = filterQueryBuilderResult.getProductRoot();
    }


    public List<IFilterOption> getOptions() {
        return Arrays.asList(getPriceFilterOption());
    }

    //--------------------------------------------------------
    private IFilterOption getPriceFilterOption() {

        CriteriaQuery<Object> criteria = filterQueryBuilderResult.getCriteria();

        criteria.select(
                cb.construct(MinMaxResult.class,
                        cb.<Double>min(productRoot.<Double>get("price")),
                        cb.<Double>max(productRoot.<Double>get("price"))
                )
        );

        Query query = entityManager.createQuery(criteria);

        MinMaxResult result = (MinMaxResult) query.getSingleResult();

        return new PriceFilterOption((double) result.getMin(), (double) result.getMax());
    }
}
