package com.algaworks.algamoney.api.controller;

import com.algaworks.algamoney.api.event.ResourceCreatedEvent;
import com.algaworks.algamoney.api.model.Category;
import com.algaworks.algamoney.api.respository.CategoryRepository;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.flywaydb.core.internal.logging.log4j2.Log4j2Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @PreAuthorize("hasAuthority('SCOPE_READ') and hasAuthority('SEARCH_CATEGORY')")
    @GetMapping
    public ResponseEntity<List<Category>> listAll(){
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        List<Category> categories = categoryRepository.findAll();
        return ResponseEntity.ok(categories);
    }

    @PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('REGISTER_CATEGORY')")
    @PostMapping
    public ResponseEntity<Category> create(@Valid @RequestBody Category category, HttpServletResponse response){
        Category categorySaved = categoryRepository.save(category);
        publisher.publishEvent(new ResourceCreatedEvent(this, response, category.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(categorySaved);
    }

    @PreAuthorize("hasAuthority('SCOPE_READ') and hasAuthority('SEARCH_CATEGORY')")
    @GetMapping("/{id}")
    public ResponseEntity<Category> findById(@PathVariable  Long id){
        Optional<Category> category = categoryRepository.findById(id);
        return category.isPresent() ?
                ResponseEntity.ok(category.get()) : ResponseEntity.notFound().build();
    }

}
