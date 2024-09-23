package com.marcelo.workhub.payload;

import com.marcelo.workhub.model.Candidatura;
import com.marcelo.workhub.model.RecemFormado;

import java.util.List;

public record RecemFormadoPayloadResponse(Long id,String nome, String email, String telefone, String  curriculo, List<Candidatura> listaCandidaturas) {

    public RecemFormadoPayloadResponse (RecemFormado recemFormado){

        this (recemFormado.getId(),recemFormado.getNome(), recemFormado.getEmail(), recemFormado.getTelefone(),recemFormado.getCurriculo(), recemFormado.getListaCandidaturas());


    }
}
