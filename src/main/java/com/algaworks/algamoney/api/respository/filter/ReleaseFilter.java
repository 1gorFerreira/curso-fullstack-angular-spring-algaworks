package com.algaworks.algamoney.api.respository.filter;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class ReleaseFilter {

    private String description;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dueDateFrom;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dueDateUntil;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDueDateFrom() {
        return dueDateFrom;
    }

    public void setDueDateFrom(LocalDate dueDateFrom) {
        this.dueDateFrom = dueDateFrom;
    }

    public LocalDate getDueDateUntil() {
        return dueDateUntil;
    }

    public void setDueDateUntil(LocalDate dueDateUntil) {
        this.dueDateUntil = dueDateUntil;
    }
}
