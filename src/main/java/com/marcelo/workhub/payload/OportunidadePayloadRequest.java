package com.marcelo.workhub.payload;

import com.marcelo.workhub.model.Candidatura;
import com.marcelo.workhub.model.Empresa;

import java.time.LocalDateTime;
import java.util.List;

public record OportunidadePayloadRequest(String titulo, String descricao, LocalDateTime dataPublicacao, Empresa empresa, List<Candidatura> listaCandidatura) {


}
