package com.algaworks.algamoney.api.controller;

import com.algaworks.algamoney.api.model.Category;
import com.algaworks.algamoney.api.respository.CategoryRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    public List<Category> listAll(){
        return categoryRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Category> create(@RequestBody Category category, HttpServletResponse response){
        Category categorySaved = categoryRepository.save(category);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(categorySaved.getId()).toUri();

        return ResponseEntity.created(uri).body(categorySaved);
    }

    @GetMapping("/{id}")
    public Category findById(@PathVariable  Long id){
        return categoryRepository.findById(id).orElse(null);
    }

}
