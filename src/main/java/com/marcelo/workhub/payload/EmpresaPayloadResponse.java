package com.marcelo.workhub.payload;

import com.marcelo.workhub.model.Empresa;
import com.marcelo.workhub.model.Oportunidade;

import java.util.List;

public record EmpresaPayloadResponse(Long id,String nome, String email, String  senha, List<Oportunidade> listaOportunidades) {

    public EmpresaPayloadResponse (Empresa empresa){

      this(empresa.getId(),empresa.getNome(),empresa.getEmail(),empresa.getSenha(), empresa.getListaOportunidades());

    }

}
