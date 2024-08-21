package com.jornadadev.casadocodigo.novoautor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Locale;

@Component
public class EmailDuplicadoValidator implements Validator {
    Logger logger = LogManager.getLogger(EmailDuplicadoValidator.class);
    private AutorRepository autorRepository;
    private MessageSource messageSource;

    public EmailDuplicadoValidator(AutorRepository autorRepository, MessageSource messageSource) {
        this.autorRepository = autorRepository;
        this.messageSource = messageSource;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return NovoAutorDtoRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        logger.info("### iniciando validate EmailDuplicado ###");
        if (errors.hasErrors()) {
            return;
        }

        NovoAutorDtoRequest request = (NovoAutorDtoRequest) target;

        if (autorRepository.findByEmail(request.getEmail()).isPresent()) {
            Locale locale = LocaleContextHolder.getLocale();
            String errorMessage = messageSource.getMessage("autor.email.duplicated", null, locale);
            errors.rejectValue("email", null, errorMessage);
        }

    }
}
