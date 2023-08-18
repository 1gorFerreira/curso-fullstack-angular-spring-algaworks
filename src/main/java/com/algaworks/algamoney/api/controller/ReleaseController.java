package com.algaworks.algamoney.api.controller;

import com.algaworks.algamoney.api.event.ResourceCreatedEvent;
import com.algaworks.algamoney.api.model.Release;
import com.algaworks.algamoney.api.respository.ReleaseRepository;
import com.algaworks.algamoney.api.respository.filter.ReleaseFilter;
import com.algaworks.algamoney.api.respository.projection.ReleaseResume;
import com.algaworks.algamoney.api.service.ReleaseService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/releases")
public class ReleaseController {

    @Autowired
    private ReleaseService releaseService;

    @Autowired
    private ApplicationEventPublisher publisher;
    @Autowired
    private ReleaseRepository releaseRepository;

    @PreAuthorize("hasAuthority('SCOPE_READ') and hasAuthority('SEARCH_RELEASE')")
    @GetMapping
    public ResponseEntity<Page<Release>> findAll(ReleaseFilter releaseFilter, Pageable pageable){
        Page<Release> releases = releaseService.findAll(releaseFilter, pageable);
        return ResponseEntity.ok(releases);
    }

    @PreAuthorize("hasAuthority('SCOPE_READ') and hasAuthority('SEARCH_RELEASE')")
    @GetMapping(params = "resume")
    public ResponseEntity<Page<ReleaseResume>> resume(ReleaseFilter releaseFilter, Pageable pageable){
        Page<ReleaseResume> releases = releaseRepository.resume(releaseFilter, pageable);
        return ResponseEntity.ok(releases);
    }

    @PreAuthorize("hasAuthority('SCOPE_READ') and hasAuthority('SEARCH_RELEASE')")
    @GetMapping("/{id}")
    public ResponseEntity<Release> findById(@PathVariable Long id){
        Optional<Release> release = releaseService.findById(id);
        return release.isPresent() ? ResponseEntity.ok(release.get()) : ResponseEntity.notFound().build();
    }

    @PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('REGISTER_RELEASE')")
    @PostMapping
    public ResponseEntity<Release> create(@Valid @RequestBody Release release, HttpServletResponse response) {
        Release releaseSaved = releaseService.save(release);
        publisher.publishEvent(new ResourceCreatedEvent(this, response, releaseSaved.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(releaseSaved);
    }

    @PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('REGISTER_RELEASE')")
    @PutMapping("/{id}")
    public ResponseEntity<Release> update(@PathVariable Long id, @Valid @RequestBody Release release){
        Release newRelease = releaseService.update(id, release);
        return ResponseEntity.ok(newRelease);
    }

    @PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('REMOVE_RELEASE')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        releaseService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
