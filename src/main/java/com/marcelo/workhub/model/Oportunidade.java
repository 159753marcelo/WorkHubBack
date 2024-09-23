package com.marcelo.workhub.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name= "oportunidades")
@Getter
@Setter
@EqualsAndHashCode(of= "id")

public class Oportunidade implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;
    private  String titulo;
    private String descricao;
    private LocalDateTime dataPublicacao;
    @ManyToOne
    @JoinColumn(name = "empresaId")
    private Empresa  empresa;

    @JsonIgnore
    @OneToMany(mappedBy = "oportunidade")
    private List<Candidatura> listaCandidatura = new ArrayList<>();

    public Oportunidade(String titulo, String descricao, LocalDateTime dataPublicacao, Empresa empresa, List<Candidatura> listaCandidatura) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataPublicacao = dataPublicacao;
        this.empresa = empresa;
        this.listaCandidatura = listaCandidatura;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(LocalDateTime dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public List<Candidatura> getListaCandidatura() {
        return listaCandidatura;
    }

    public void setListaCandidatura(List<Candidatura> listaCandidatura) {
        this.listaCandidatura = listaCandidatura;
    }
}
