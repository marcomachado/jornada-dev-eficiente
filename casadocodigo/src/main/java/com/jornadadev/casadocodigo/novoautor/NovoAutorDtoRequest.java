package com.jornadadev.casadocodigo.novoautor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;

public class NovoAutorDtoRequest {

    @NotEmpty( message = "{autor.name.required}")
    private String nome;

    @NotEmpty(message = "{autor.description.required}")
    @Length(max = 400, message = "{autor.description.invalid}")
    private String descricao;

    @Email(message = "{autor.email.invalid}")
    @NotEmpty(message = "{autor.email.required}")
    private String email;

    public NovoAutorDtoRequest(@NotEmpty String nome,
                               @NotEmpty @Length(max = 400) String descricao,
                               @NotEmpty String email) {
        this.nome = nome;
        this.descricao = descricao;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    AutorModel toModel() {
        return new AutorModel(this.nome, this.descricao, this.email);
    }

    @Override
    public String toString() {
        return "AutorDtoRequest{" +
                "nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
