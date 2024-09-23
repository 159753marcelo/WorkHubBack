package com.marcelo.workhub.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name= "recemformados")
@Getter
@Setter
@EqualsAndHashCode(of= "id")
public class RecemFormado implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;
    private  String nome;
    private String email;
    private String telefone;
    private String  curriculo;

    @JsonIgnore
    @OneToMany(mappedBy = "recemFormado")
    private List<Candidatura> listaCandidaturas = new ArrayList<>();

    public RecemFormado(String nome, String email, String telefone, String curriculo, List<Candidatura> listaCandidaturas) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.curriculo = curriculo;
        this.listaCandidaturas = listaCandidaturas;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCurriculo() {
        return curriculo;
    }

    public void setCurriculo(String curriculo) {
        this.curriculo = curriculo;
    }

    public List<Candidatura> getListaCandidaturas() {
        return listaCandidaturas;
    }

    public void setListaCandidaturas(List<Candidatura> listaCandidaturas) {
        this.listaCandidaturas = listaCandidaturas;
    }
}
