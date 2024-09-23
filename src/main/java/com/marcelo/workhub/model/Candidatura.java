package com.marcelo.workhub.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name= "candidatura")
@Getter
@Setter
@EqualsAndHashCode(of= "id")
public class Candidatura implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private  Long id;
    @ManyToOne
    @JoinColumn(name = "RecemFormadoId")
    private RecemFormado recemFormado;
    @ManyToOne
    @JoinColumn(name = "OportunidadeId")
    private Oportunidade oportunidade;
    private LocalDateTime dataCandidatura;

    public Candidatura(RecemFormado recemFormado, Oportunidade oportunidade, LocalDateTime dataCandidatura) {
        this.recemFormado = recemFormado;
        this.oportunidade = oportunidade;
        this.dataCandidatura = dataCandidatura;
    }

    public Long getId() {
        return id;
    }

    public RecemFormado getRecemFormado() {
        return recemFormado;
    }

    public void setRecemFormado(RecemFormado recemFormado) {
        this.recemFormado = recemFormado;
    }

    public Oportunidade getOportunidade() {
        return oportunidade;
    }

    public void setOportunidade(Oportunidade oportunidade) {
        this.oportunidade = oportunidade;
    }

    public LocalDateTime getDataCandidatura() {
        return dataCandidatura;
    }

    public void setDataCandidatura(LocalDateTime dataCandidatura) {
        this.dataCandidatura = dataCandidatura;
    }
}
