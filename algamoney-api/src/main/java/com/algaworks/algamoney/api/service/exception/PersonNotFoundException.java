package com.algaworks.algamoney.api.service.exception;

public class PersonNotFoundException extends EntityNotFoundException {
    private static final long serialVersionUID = 1L;

    public PersonNotFoundException(String msg) {
        super(msg);
    }

    public PersonNotFoundException(Long personId) {
        this(String.format("Person with id %d not found!", personId));
    }
}
