package com.algaworks.algamoney.api.controller;

import com.algaworks.algamoney.api.event.ResourceCreatedEvent;
import com.algaworks.algamoney.api.model.Person;
import com.algaworks.algamoney.api.respository.PersonRepository;
import com.algaworks.algamoney.api.service.PersonService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/persons")
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private PersonService personService;

    @GetMapping
    @PreAuthorize("hasAuthority('SEARCH_PERSON')")
    public ResponseEntity<Page<Person>> search(@RequestParam(required = false, defaultValue = "") String name, Pageable pageable) {
        Page<Person> persons = personRepository.findByNameContaining(name, pageable);
        return ResponseEntity.ok(persons);
    }

    @PreAuthorize("hasAuthority('SCOPE_READ') and hasAuthority('SEARCH_PERSON')")
    @GetMapping("/{id}")
    public ResponseEntity<Person> findById(@PathVariable Long id){
        Optional<Person> person = personRepository.findById(id);
        return person.isPresent() ? ResponseEntity.ok(person.get()) : ResponseEntity.notFound().build();
    }

    @PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('REGISTER_PERSON')")
    @PostMapping
    public ResponseEntity<Person> create(@Valid @RequestBody Person person, HttpServletResponse response){
        Person personSaved = personRepository.save(person);
        publisher.publishEvent(new ResourceCreatedEvent(this, response, personSaved.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(personSaved);
    }

    @PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('REMOVE_PERSON')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remove(@PathVariable Long id){

        if(!personRepository.existsById(id)) {
            throw new EmptyResultDataAccessException(1);
        }

        personRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('REGISTER_PERSON')")
    @PutMapping("/{id}")
    public ResponseEntity<Person> update(@PathVariable Long id, @Valid @RequestBody Person person){
        Person personSaved = personService.update(id, person);
        return ResponseEntity.ok(personSaved);
    }

    @PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('REGISTER_PERSON')")
    @PutMapping("/{id}/active")
    public ResponseEntity<Void> updateActiveProperty(@PathVariable Long id, @RequestBody Boolean active){
        personService.updateActiveProperty(id, active);
        return ResponseEntity.noContent().build();
    }

}
