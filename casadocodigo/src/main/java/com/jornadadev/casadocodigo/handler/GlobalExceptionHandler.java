package com.jornadadev.casadocodigo.handler;

import com.jornadadev.casadocodigo.excecoes.ApiError;
import jakarta.validation.ConstraintViolationException;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    MessageSource messageSource;
    public GlobalExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ApiError handleException(NoHandlerFoundException ex, Locale locale) {
        return new ApiError(HttpStatus.NOT_FOUND, ex.getMessage());
    }
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ApiError handleException(ConstraintViolationException ex, Locale locale) {
        List<String> messages = ex.getConstraintViolations()
                .stream()
                .map(e -> e.getMessage())
                .collect(Collectors.toList());
        return new ApiError(HttpStatus.BAD_REQUEST, messages);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ApiError handleException(MethodArgumentNotValidException ex, Locale locale) {
        List<String> messages = ex.getFieldErrors()
                .stream()
                .map(e -> e.getDefaultMessage())
                .collect(Collectors.toList());
        return new ApiError(HttpStatus.BAD_REQUEST, messages);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ApiError handleException(MissingServletRequestParameterException ex, Locale locale) {
        String message = messageSource.getMessage("exception.requiredParam", new String[]{ex.getParameterName()}, locale);

        return new ApiError(HttpStatus.BAD_REQUEST, message);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ApiError handleException(MethodArgumentTypeMismatchException ex, Locale locale) {
        String msg = messageSource.getMessage("exception.mismatch", new Object[] {ex.getValue(),ex.getRequiredType().getSimpleName()}, locale);
        return new ApiError(HttpStatus.BAD_REQUEST, msg);

    }
    @ExceptionHandler(HttpStatusCodeException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    ApiError handleException(HttpClientErrorException.Unauthorized ex, Locale locale) {
        String msg = messageSource.getMessage("exception.mismatch", null, locale);
        return new ApiError(HttpStatus.BAD_REQUEST, msg);
    }
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ApiError handleException(Exception ex, Locale locale) {
        return new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage());
    }
}
