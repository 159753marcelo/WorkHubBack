package com.marcelo.workhub.payload;

import com.marcelo.workhub.model.Candidatura;
import com.marcelo.workhub.model.Empresa;
import com.marcelo.workhub.model.Oportunidade;

import java.time.LocalDateTime;
import java.util.List;

public record OportunidadePayloadResponse(Long id, String titulo, String descricao, LocalDateTime dataPublicacao, Empresa empresa, List<Candidatura> listaCandidatura) {

    public OportunidadePayloadResponse (Oportunidade oportunidade){
        this( oportunidade.getId(), oportunidade.getTitulo(), oportunidade.getDescricao(), oportunidade.getDataPublicacao(), oportunidade.getEmpresa(), oportunidade.getListaCandidatura());
    }
}
