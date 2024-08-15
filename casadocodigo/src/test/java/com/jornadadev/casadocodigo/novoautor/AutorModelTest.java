// File: src/test/java/com/jornadadev/casadocodigo/novoautor/AutorModelTest.java

package com.jornadadev.casadocodigo.novoautor;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AutorModelTest {

    @Test
    void testConstructor() {
        String nome = "John Doe";
        String descricao = "Author description";
        String email = "john.doe@example.com";

        AutorModel autor = new AutorModel(nome, descricao, email);

        assertEquals(nome, autor.getNome());
        assertEquals(descricao, autor.getDescricao());
        assertEquals(email, autor.getEmail());
        assertNotNull(autor.getDataCriacao());
    }

    @Test
    void testConstructorWithEmptyFields() {
        assertThrows(IllegalArgumentException.class, () -> {
            new AutorModel("", "", "");
        });
    }
}