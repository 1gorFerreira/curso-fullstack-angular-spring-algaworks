package com.algaworks.algamoney.api.respository;

import com.algaworks.algamoney.api.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    Page<Person> findByNameContaining(String nome, Pageable pageable);

}
