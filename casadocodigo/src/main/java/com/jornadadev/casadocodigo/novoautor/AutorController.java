package com.jornadadev.casadocodigo.novoautor;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
//CDD(2)
public class AutorController {

    Logger logger = LogManager.getLogger(AutorController.class);

    @PersistenceContext
    EntityManager entityManager;

    @PostMapping("/autores")
    @Transactional
    public ResponseEntity<?> salva(@RequestBody @Valid NovoAutorDtoRequest autorDto){ //1
        var model = autorDto.toModel(); //2
        logger.info("AutorModel ready to save {}", model);
        entityManager.persist(model);
        logger.info("AutorModel savo DB {}", model.toString());
        return ResponseEntity.ok(model.toString());
    }
}
