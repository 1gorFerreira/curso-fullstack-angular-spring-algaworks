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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

public class ReleaseRepositoryImpl implements ReleaseRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<Release> filter(ReleaseFilter releaseFilter, Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Release> criteria = builder.createQuery(Release.class);
        Root<Release> root = criteria.from(Release.class);

        // criar as restricoes
        Predicate[] predicates = createRestrictions(releaseFilter, builder, root);
        criteria.where(predicates);

        TypedQuery<Release> query = manager.createQuery(criteria);
        addPaginationRestrictions(query, pageable);

        return new PageImpl<>(query.getResultList(), pageable, total(releaseFilter));
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

    private void addPaginationRestrictions(TypedQuery<Release> query, Pageable pageable) {
        int actualPage = pageable.getPageNumber();
        int totalPageRegisters = pageable.getPageSize();
        int firstPageRegister = actualPage * totalPageRegisters;

        query.setFirstResult(firstPageRegister);
        query.setMaxResults(totalPageRegisters);
    }

    private Long total(ReleaseFilter releaseFilter) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<Release> root = criteria.from(Release.class);

        Predicate[] predicates = createRestrictions(releaseFilter, builder, root);
        criteria.where(predicates);

        criteria.select(builder.count(root));
        return manager.createQuery(criteria).getSingleResult();
    }
}
