package com.marcelo.workhub.payload;

import com.marcelo.workhub.model.Oportunidade;

import java.util.List;

public record EmpresaPayloadRequest(String nome, String email, String  senha, List<Oportunidade> listaOportunidades) {
}
