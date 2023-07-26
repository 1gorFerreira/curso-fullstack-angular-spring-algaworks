package com.algaworks.algamoney.api.respository.release;

import com.algaworks.algamoney.api.model.Release;
import com.algaworks.algamoney.api.respository.filter.ReleaseFilter;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.ArrayList;
import java.util.List;

public class ReleaseRepositoryImpl implements ReleaseRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Release> filter(ReleaseFilter releaseFilter) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Release> criteria = builder.createQuery(Release.class);
        Root<Release> root = criteria.from(Release.class);

        // criar as restricoes
        Predicate[] predicates = createRestrictions(releaseFilter, builder, root);
        criteria.where(predicates);

        TypedQuery<Release> query = manager.createQuery(criteria);
        return query.getResultList();
    }

    protected Predicate[] createRestrictions(ReleaseFilter releaseFilter, CriteriaBuilder builder, Root<Release> root){
        List<Predicate> predicates = new ArrayList<>();

        if(!StringUtils.isEmpty(releaseFilter.getDescription())){
            predicates.add(builder.like(
                    builder.lower(root.get("description")),
                    "%" + releaseFilter.getDescription().toLowerCase() + "%")
            );
        }

        if(releaseFilter.getDueDateFrom() != null){
            predicates.add(
                    builder.greaterThanOrEqualTo(root.get("dueDate"), releaseFilter.getDueDateFrom())
            );
        }

        if(releaseFilter.getDueDateUntil() != null){
            predicates.add(
                    builder.lessThanOrEqualTo(root.get("dueDate"), releaseFilter.getDueDateUntil())
            );
        }

        return predicates.toArray(new Predicate[predicates.size()]);
    }
}
