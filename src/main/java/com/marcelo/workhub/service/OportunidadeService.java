package com.marcelo.workhub.service;
import com.marcelo.workhub.model.Oportunidade;
import com.marcelo.workhub.payload.OportunidadePayloadRequest;
import com.marcelo.workhub.payload.OportunidadePayloadRequest;
import com.marcelo.workhub.repository.OportunidadeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class OportunidadeService {
    @Autowired
    private OportunidadeRepository OportunidadeRepository;

    public Oportunidade salvar( OportunidadePayloadRequest OportunidadePayloadRequest){ 
        var Oportunidade = new Oportunidade( OportunidadePayloadRequest.titulo(), OportunidadePayloadRequest.descricao(), OportunidadePayloadRequest.dataPublicacao(), OportunidadePayloadRequest.empresa(), OportunidadePayloadRequest.listaCandidatura());
        return OportunidadeRepository.save(Oportunidade);
    }

    public List<Oportunidade> buscarTodasOportunidades(){
        return OportunidadeRepository.findAll();
    }

    public Oportunidade findById(Long id) {
        var Oportunidade = OportunidadeRepository.findById(id).orElseThrow(() -> new RuntimeException("Erro! Oportunidade não encontrado!"));

        return Oportunidade;

    }

    public Oportunidade update(Long id, OportunidadePayloadRequest oportunidadePayloadRequest) {
        var Oportunidade = OportunidadeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Erro! Oportunidade não encontrada!"));


        Oportunidade.setTitulo(oportunidadePayloadRequest.titulo());
        Oportunidade.setDescricao(oportunidadePayloadRequest.descricao());
        Oportunidade.setDataPublicacao(oportunidadePayloadRequest.dataPublicacao());
        Oportunidade.setEmpresa(oportunidadePayloadRequest.empresa());
        Oportunidade.setListaCandidatura(oportunidadePayloadRequest.listaCandidatura());


        OportunidadeRepository.save(Oportunidade);

        return Oportunidade;
    }


    public void delete(Long id) {
        var Oportunidade = OportunidadeRepository.findById(id).orElseThrow(() -> new RuntimeException("Erro! Oportunidade não encontrado!"));

        OportunidadeRepository.deleteById(id);
    }

    public List<Oportunidade> tituloOportunidade(String titulo){
        return  OportunidadeRepository.findByTitulo(titulo);
    }

}
