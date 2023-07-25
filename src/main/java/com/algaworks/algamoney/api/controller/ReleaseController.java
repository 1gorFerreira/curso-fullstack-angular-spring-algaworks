package com.algaworks.algamoney.api.controller;

import com.algaworks.algamoney.api.event.ResourceCreatedEvent;
import com.algaworks.algamoney.api.model.Release;
import com.algaworks.algamoney.api.respository.ReleaseRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/releases")
public class ReleaseController {

    @Autowired
    private ReleaseRepository releaseRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

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

    @PostMapping
    public ResponseEntity<Release> criar(@Valid @RequestBody Release release, HttpServletResponse response) {
        Release releaseSaved = releaseRepository.save(release);
        publisher.publishEvent(new ResourceCreatedEvent(this, response, releaseSaved.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(releaseSaved);
    }
}
