package com.alura.agenda.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Contato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    private String nome;

    @NotNull
    @NotBlank
    private String dddCelular;

    @NotNull
    @NotBlank
    private String numeroCelular;

    @NotNull
    @NotBlank
    private String email;

    public Contato() {
    }

    public Contato(Long id, String nome, String dddCelular, String numeroCelular, String email) {
        this.id = id;
        this.nome = nome;
        this.dddCelular = dddCelular;
        this.numeroCelular = numeroCelular;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDddCelular() {
        return dddCelular;
    }

    public void setDddCelular(final String dddCelular) {
        this.dddCelular = dddCelular;
    }

    public String getNumeroCelular() {
        return numeroCelular;
    }

    public void setNumeroCelular(final String numeroCelular) {
        this.numeroCelular = numeroCelular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

}
