// File: src/test/java/com/jornadadev/casadocodigo/novoautor/NovoAutorDtoRequestTest.java

package com.jornadadev.casadocodigo.novoautor;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class NovoAutorDtoRequestTest {

    @Test
    void testToModel() {
        String nome = "John Doe";
        String descricao = "Author description";
        String email = "john.doe@example.com";

        NovoAutorDtoRequest dto = new NovoAutorDtoRequest(nome, descricao, email);
        AutorModel autor = dto.toModel();

        assertEquals(nome, autor.getNome());
        assertEquals(descricao, autor.getDescricao());
        assertEquals(email, autor.getEmail());
        assertNotNull(autor.getDataCriacao());
    }

    @Test
    void testToModelWithEmptyFields() {
        assertThrows(IllegalArgumentException.class, () -> {
            new NovoAutorDtoRequest("", "", "").toModel();
        });
    }
}