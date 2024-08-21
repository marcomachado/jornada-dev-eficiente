package com.jornadadev.casadocodigo.novoautor;

import com.jornadadev.casadocodigo.excecoes.ApiError;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.RequestContextUtils;

import java.util.Locale;
import java.util.Optional;

@Controller
//CDD(2)
public class AutorController {

    Logger logger = LogManager.getLogger(AutorController.class);

    @PersistenceContext
    EntityManager entityManager;
    private EmailDuplicadoValidator emailDuplicadoValidator;

    public AutorController(EmailDuplicadoValidator emailDuplicadoValidator) {
        this.emailDuplicadoValidator = emailDuplicadoValidator;
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(emailDuplicadoValidator);
    }

    @Transactional
    @PostMapping("/autores")
    public ResponseEntity<?> salva(@RequestBody @Valid NovoAutorDtoRequest autorDto){ //1
        logger.info("### iniciando salva ###");
        var model = autorDto.toModel(); //2
        logger.info("AutorModel ready to save {}", model);
        entityManager.persist(model);
        logger.info("AutorModel savo DB {}", model.toString());
        return ResponseEntity.ok(model.toString());
    }
}
