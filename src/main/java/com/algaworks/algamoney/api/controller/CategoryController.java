package com.algaworks.algamoney.api.controller;

import com.algaworks.algamoney.api.model.Category;
import com.algaworks.algamoney.api.respository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
