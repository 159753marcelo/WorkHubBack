package com.marcelo.workhub.service;
import com.marcelo.workhub.model.Candidatura;
import com.marcelo.workhub.payload.CandidaturaPayloadRequest;
import com.marcelo.workhub.payload.CandidaturaPayloadRequest;
import com.marcelo.workhub.repository.CandidaturaRepository;
import com.marcelo.workhub.repository.CandidaturaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CandidaturaService {
    @Autowired
    private CandidaturaRepository candidaturaRepository;

    public Candidatura salvar(CandidaturaPayloadRequest candidaturaPayloadRequest){
        var Candidatura = new Candidatura( candidaturaPayloadRequest.recemFormado(),    candidaturaPayloadRequest.oportunidade(),  candidaturaPayloadRequest.dataCandidatura());
        return candidaturaRepository.save(Candidatura);
    }

    public List<Candidatura> buscarTodasCandidaturas(){
        return candidaturaRepository.findAll();
    }

    public Candidatura findById(Long id) {
        var Candidatura = candidaturaRepository.findById(id).orElseThrow(() -> new RuntimeException("Erro! Candidatura não encontrado!"));

        return Candidatura;

    }

    public Candidatura update(Long id, CandidaturaPayloadRequest CandidaturaPayloadRequest) {
        var Candidatura = candidaturaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Erro! Candidatura não encontrada!"));


        Candidatura.setRecemFormado(CandidaturaPayloadRequest.recemFormado());
        Candidatura.setOportunidade(CandidaturaPayloadRequest.oportunidade());
        Candidatura.setDataCandidatura(CandidaturaPayloadRequest.dataCandidatura());



        candidaturaRepository.save(Candidatura);

        return Candidatura;
    }


    public void delete(Long id) {
        var Candidatura = candidaturaRepository.findById(id).orElseThrow(() -> new RuntimeException("Erro! Candidatura não encontrado!"));

        candidaturaRepository.deleteById(id);
    }

}
