package com.jornadadev.casadocodigo.novoautor;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;
import org.springframework.util.Assert;

import java.time.LocalDateTime;

@Entity
@Table(name = "autor")
public class AutorModel {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty( message = "O nome é obrigatório")
    private String nome;

    @NotEmpty(message = "O endereço é obrigatório")
    @Length(max = 400, message = "A descricao deverá ter no máximo {max} caracteres")
    private String descricao;

    @Email(message = "Email com formato inválido")
    @NotEmpty(message = "O email é obrigatório")
    private String email;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime dataCriacao = LocalDateTime.now();

    public AutorModel(@NotEmpty String nome,
                      @NotEmpty @Length(max = 400) String descricao,
                      @NotEmpty String email) {

        Assert.hasLength(nome, "O nome é obrigatório");
        Assert.hasLength(descricao, "A descrição é obrigatória");
        Assert.hasLength(email, "O email é obrigatório");

        this.nome = nome;
        this.descricao = descricao;
        this.email = email;
    }

    @Deprecated
    public AutorModel() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    @Override
    public String toString() {
        return "AutorModel{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", email='" + email + '\'' +
                ", dataCriacao=" + dataCriacao +
                '}';
    }
}
