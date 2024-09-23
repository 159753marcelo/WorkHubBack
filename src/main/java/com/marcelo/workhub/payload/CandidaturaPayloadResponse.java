package com.marcelo.workhub.payload;

import com.marcelo.workhub.model.Candidatura;
import com.marcelo.workhub.model.Oportunidade;
import com.marcelo.workhub.model.RecemFormado;

import java.time.LocalDateTime;

public record CandidaturaPayloadResponse( Long id,RecemFormado recemFormado, Oportunidade oportunidade, LocalDateTime dataCandidatura) {

    public CandidaturaPayloadResponse(Candidatura candidatura){
        this(candidatura.getId(), candidatura.getRecemFormado(), candidatura.getOportunidade(), candidatura.getDataCandidatura());
    }

}
