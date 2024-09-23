package com.marcelo.workhub.payload;

import com.marcelo.workhub.model.Oportunidade;
import com.marcelo.workhub.model.RecemFormado;

import java.time.LocalDateTime;

public record CandidaturaPayloadRequest(RecemFormado recemFormado, Oportunidade oportunidade, LocalDateTime dataCandidatura) {
}
