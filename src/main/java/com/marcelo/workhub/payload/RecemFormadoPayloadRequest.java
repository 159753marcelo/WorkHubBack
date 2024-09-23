package com.marcelo.workhub.payload;

import com.marcelo.workhub.model.Candidatura;

import java.util.List;

public record RecemFormadoPayloadRequest(String nome, String email, String telefone, String  curriculo, List<Candidatura> listaCandidaturas) {
}
