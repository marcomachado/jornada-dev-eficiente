package com.jornadadev.casadocodigo.novoautor;

import jakarta.persistence.EntityManager;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AutorControllerTest {

    private AutorController autorController;
    private EntityManager entityManager;
    private EmailDuplicadoValidator emailDuplicadoValidator;
    private MessageSource messageSource;

    @BeforeEach
    void setUp() {
        entityManager = Mockito.mock(EntityManager.class);
        emailDuplicadoValidator = Mockito.mock(EmailDuplicadoValidator.class);
        messageSource = Mockito.mock(MessageSource.class);
        autorController = new AutorController(emailDuplicadoValidator);
        autorController.entityManager = entityManager;
    }

    @Test
    void shouldPersistNewAuthor() {
        NovoAutorDtoRequest request = new NovoAutorDtoRequest("John Doe", "john.doe@example.com", "Description");
        HttpServletRequest httpServletRequest = Mockito.mock(HttpServletRequest.class);

        ResponseEntity<?> response = autorController.salva(request);

        verify(entityManager, times(1)).persist(any(AutorModel.class));
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

}