package com.algaworks.algamoney.api.service;

import com.algaworks.algamoney.api.model.Person;
import com.algaworks.algamoney.api.respository.PersonRepository;
import com.algaworks.algamoney.api.service.exception.PersonNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public Person findPersonById(Long id){
        Person person = personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException(id));
        return person;
    }

    public Person update(Long id, Person person){
        Person personSaved = findPersonById(id);
        BeanUtils.copyProperties(person, personSaved, "id");
        personSaved = personRepository.save(personSaved);
        return personSaved;
    }

    public void updateActiveProperty(Long id, Boolean active){
        Person personSaved = findPersonById(id);
        personSaved.setActive(active);
        personRepository.save(personSaved);
    }
}
