package com.algaworks.algamoney.api.service.exception;

public class PersonInactiveException extends BusinessException {
    private static final long serialVersionUID = 1L;

    public PersonInactiveException(String msg) {
        super(msg);
    }

    public PersonInactiveException(Long personId) {
        this(String.format("Person with id %d not active!", personId));
    }
}
