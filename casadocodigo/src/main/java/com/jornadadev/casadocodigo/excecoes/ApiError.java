package com.jornadadev.casadocodigo.excecoes;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ApiError {
    private HttpStatus status;
    private List<String> errors = new ArrayList<>();
    private @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    LocalDateTime timestamp;

    public ApiError(HttpStatus status, List<String> errors) {
        this.status = status;
        this.errors = errors;
        this.timestamp = LocalDateTime.now();
    }

    public ApiError(HttpStatus status, String error) {
        this.status = status;
        this.errors.add(error);
        this.timestamp = LocalDateTime.now();
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }


    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
