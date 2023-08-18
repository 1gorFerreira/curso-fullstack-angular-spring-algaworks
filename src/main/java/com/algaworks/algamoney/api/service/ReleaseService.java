package com.algaworks.algamoney.api.service;

import com.algaworks.algamoney.api.model.Person;
import com.algaworks.algamoney.api.model.Release;
import com.algaworks.algamoney.api.respository.ReleaseRepository;
import com.algaworks.algamoney.api.respository.filter.ReleaseFilter;
import com.algaworks.algamoney.api.service.exception.PersonInactiveException;
import com.algaworks.algamoney.api.service.exception.ReleaseNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReleaseService {

    @Autowired
    private ReleaseRepository releaseRepository;

    @Autowired
    private PersonService personService;

    public Page<Release> findAll(ReleaseFilter releaseFilter, Pageable pageable){
        return releaseRepository.filter(releaseFilter, pageable);
    }

    public Optional<Release> findById(Long id){
        Optional<Release> release = releaseRepository.findById(id);
        return release;
    }

    public Release update(Long id, Release release){
        Release newRelease = findById(id).orElseThrow(() -> new ReleaseNotFoundException(id));

        //Se estiver trocando a pessoa do lancamento;
        if(!release.getPerson().equals(newRelease.getPerson())){
            validatePerson(release);
        }

        BeanUtils.copyProperties(release, newRelease, "id");

        return releaseRepository.save(newRelease);
    }

    private void validatePerson(Release release) {
        Person person = null;

        if(release.getPerson() != null){
            person = personService.findPersonById(release.getPerson().getId());
        }

        if(person.isInactive()){
            throw new PersonInactiveException(person.getId());
        }
    }

    public Release save(Release release){
        Person person = personService.findPersonById(release.getPerson().getId());

        if(person.isInactive()){
            throw new PersonInactiveException(person.getId());
        }

        return releaseRepository.save(release);
    }

    public void delete(Long id) {
        Release release = releaseRepository.findById(id).orElseThrow(() -> new ReleaseNotFoundException(id));
        releaseRepository.delete(release);
    }
}
