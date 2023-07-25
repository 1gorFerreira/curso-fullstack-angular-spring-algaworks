package com.algaworks.algamoney.api.controller;

import com.algaworks.algamoney.api.model.Release;
import com.algaworks.algamoney.api.respository.ReleaseRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/releases")
public class ReleaseController {

    @Autowired
    private ReleaseRepository releaseRepository;

    @GetMapping
    public ResponseEntity<List<Release>> findAll(){
        List<Release> releases = releaseRepository.findAll();
        return ResponseEntity.ok(releases);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Release> findById(@PathVariable Long id){
        Optional<Release> release = releaseRepository.findById(id);
        return release.isPresent() ? ResponseEntity.ok(release.get()) : ResponseEntity.notFound().build();
    }
}
