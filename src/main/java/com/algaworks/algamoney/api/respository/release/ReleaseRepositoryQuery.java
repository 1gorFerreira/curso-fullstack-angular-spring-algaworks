package com.algaworks.algamoney.api.respository.release;

import com.algaworks.algamoney.api.model.Release;
import com.algaworks.algamoney.api.respository.filter.ReleaseFilter;

import java.util.List;

public interface ReleaseRepositoryQuery {

    public List<Release> filter(ReleaseFilter releaseFilter);

}
