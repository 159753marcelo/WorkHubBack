package com.marcelo.workhub.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name= "empresas")
@EqualsAndHashCode(of= "id")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Empresa implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;
    private  String nome;
    private String email;
    private String  senha;

    @JsonIgnore
    @OneToMany(mappedBy = "empresa")
    private List<Oportunidade> listaOportunidades = new ArrayList<>();

    public Empresa(String nome, String email, String senha, List<Oportunidade> listaOportunidades) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.listaOportunidades = listaOportunidades;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Oportunidade> getListaOportunidades() {
        return listaOportunidades;
    }

    public void setListaOportunidades(List<Oportunidade> listaOportunidades) {
        this.listaOportunidades = listaOportunidades;
    }
}
