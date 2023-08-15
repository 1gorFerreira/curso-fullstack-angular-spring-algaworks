package com.algaworks.algamoney.api.respository.release;

import com.algaworks.algamoney.api.model.Release;
import com.algaworks.algamoney.api.respository.filter.ReleaseFilter;
import com.algaworks.algamoney.api.respository.projection.ReleaseResume;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReleaseRepositoryQuery {

    public Page<Release> filter(ReleaseFilter releaseFilter, Pageable pageable);
    public Page<ReleaseResume> resume(ReleaseFilter releaseFilter, Pageable pageable);

}
