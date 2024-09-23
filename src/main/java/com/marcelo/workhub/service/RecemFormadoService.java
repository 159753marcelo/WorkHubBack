package com.marcelo.workhub.service;
import com.marcelo.workhub.model.Candidatura;
import com.marcelo.workhub.model.Oportunidade;
import com.marcelo.workhub.model.RecemFormado;
import com.marcelo.workhub.payload.CandidaturaPayloadRequest;
import com.marcelo.workhub.payload.RecemFormadoPayloadRequest;
import com.marcelo.workhub.repository.RecemFormadoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class RecemFormadoService {
    @Autowired
    private RecemFormadoRepository RecemFormadoRepository;

    @Autowired
    private  OportunidadeService oportunidadeService;

    @Autowired
    private  CandidaturaService candidaturaService;

    public RecemFormado salvar( RecemFormadoPayloadRequest RecemFormadoPayloadRequest){
        var RecemFormado = new RecemFormado( RecemFormadoPayloadRequest.nome(), RecemFormadoPayloadRequest.email(), RecemFormadoPayloadRequest.telefone(), RecemFormadoPayloadRequest.curriculo(), RecemFormadoPayloadRequest.listaCandidaturas());
        return RecemFormadoRepository.save(RecemFormado);
    }

    public List<RecemFormado> buscarTodasRecemFormados(){
        return RecemFormadoRepository.findAll();
    }

    public RecemFormado findById(Long id) {
        var RecemFormado = RecemFormadoRepository.findById(id).orElseThrow(() -> new RuntimeException("Erro! RecemFormado não encontrado!"));

        return RecemFormado;

    }

    public RecemFormado update(Long id, RecemFormadoPayloadRequest RecemFormadoPayloadRequest) {
        var RecemFormado = RecemFormadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Erro! RecemFormado não encontrada!"));


        RecemFormado.setNome(RecemFormadoPayloadRequest.nome());
        RecemFormado.setEmail(RecemFormadoPayloadRequest.email());
        RecemFormado.setTelefone(RecemFormadoPayloadRequest.telefone());
        RecemFormado.setCurriculo(RecemFormadoPayloadRequest.curriculo());
        RecemFormado.setListaCandidaturas(RecemFormadoPayloadRequest.listaCandidaturas());


        RecemFormadoRepository.save(RecemFormado);

        return RecemFormado;
    }


    public void delete(Long id) {
        var RecemFormado = RecemFormadoRepository.findById(id).orElseThrow(() -> new RuntimeException("Erro! RecemFormado não encontrado!"));

        RecemFormadoRepository.deleteById(id);
    }

    public List<RecemFormado> qualificacaoRecemFormado( String qualificacao){
     return RecemFormadoRepository.findByCurriculo(qualificacao);
    }

   public List<Oportunidade> procuraOportunidadePorTitulo( String titulo){
        return oportunidadeService.tituloOportunidade(titulo);
   }

   public Candidatura fazerCandidatura(Long id, CandidaturaPayloadRequest candidaturaPayloadRequest){
        var recemFormado = findById(id);
        var candidatura = new Candidatura( recemFormado,    candidaturaPayloadRequest.oportunidade(),  candidaturaPayloadRequest.dataCandidatura());
        candidaturaService.salvar( candidaturaPayloadRequest);
        recemFormado.getListaCandidaturas().add(candidatura);
        RecemFormadoRepository.save(recemFormado);

        return candidatura;

   }

}
