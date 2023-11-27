package com.algaworks.algamoney.api.service.exception;

public class ReleaseNotFoundException extends EntityNotFoundException {
    public ReleaseNotFoundException(String msg) {
        super(msg);
    }

    public ReleaseNotFoundException(Long id) {
        this(String.format("Release with id %d not found!", id));
    }
}
