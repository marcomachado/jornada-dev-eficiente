package com.jornadadev.casadocodigo.novoautor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.context.MessageSource;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import java.util.Locale;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class EmailDuplicadoValidatorTest {

    private AutorRepository autorRepository;
    private MessageSource messageSource;
    private EmailDuplicadoValidator emailDuplicadoValidator;

    @BeforeEach
    void setUp() {
        autorRepository = Mockito.mock(AutorRepository.class);
        messageSource = Mockito.mock(MessageSource.class);
        emailDuplicadoValidator = new EmailDuplicadoValidator(autorRepository, messageSource);
    }

    @Test
    void shouldRejectDuplicateEmail() {
        NovoAutorDtoRequest request = new NovoAutorDtoRequest("John Doe", "john.doe@example.com", "Description");
        Errors errors = new BeanPropertyBindingResult(request, "request");

        when(autorRepository.findByEmail(anyString())).thenReturn(Optional.of(new AutorModel()));
        when(messageSource.getMessage(anyString(), Mockito.isNull(), Mockito.any(Locale.class)))
                .thenReturn("Email já cadastrado");

        emailDuplicadoValidator.validate(request, errors);

        assertTrue(errors.hasErrors());
        assertEquals("Email já cadastrado", errors.getFieldError("email").getDefaultMessage());
    }

    @Test
    void shouldAcceptUniqueEmail() {
        NovoAutorDtoRequest request = new NovoAutorDtoRequest("John Doe", "john.doe@example.com", "Description");
        Errors errors = new BeanPropertyBindingResult(request, "request");

        when(autorRepository.findByEmail(anyString())).thenReturn(Optional.empty());

        emailDuplicadoValidator.validate(request, errors);

        assertFalse(errors.hasErrors());
    }
}
