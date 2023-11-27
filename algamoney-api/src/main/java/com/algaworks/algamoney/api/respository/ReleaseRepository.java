package com.algaworks.algamoney.api.respository;

import com.algaworks.algamoney.api.model.Release;
import com.algaworks.algamoney.api.respository.release.ReleaseRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReleaseRepository extends JpaRepository<Release, Long>, ReleaseRepositoryQuery {
}
