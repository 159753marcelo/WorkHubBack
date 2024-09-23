package com.marcelo.workhub.service;
import com.marcelo.workhub.model.Empresa;
import com.marcelo.workhub.model.Oportunidade;
import com.marcelo.workhub.model.RecemFormado;
import com.marcelo.workhub.payload.EmpresaPayloadRequest;
import com.marcelo.workhub.payload.OportunidadePayloadRequest;
import com.marcelo.workhub.repository.EmpresaRepository;
import com.marcelo.workhub.repository.OportunidadeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class EmpresaService  {
    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private OportunidadeService oportunidadeService;

    @Autowired
    private  RecemFormadoService recemFormadoService;

    public Empresa salvar( EmpresaPayloadRequest empresaPayloadRequest){
        var empresa = new Empresa( empresaPayloadRequest.nome(), empresaPayloadRequest.email(), empresaPayloadRequest.senha(), empresaPayloadRequest.listaOportunidades());
        return empresaRepository.save(empresa);
    }

    public List<Empresa> buscarTodasEmpresas(){
        return empresaRepository.findAll();
    }

    public Empresa findById(Long id) {
        var Empresa = empresaRepository.findById(id).orElseThrow(() -> new RuntimeException("Erro! Empresa não encontrado!"));

        return Empresa;

    }

    public Empresa update(Long id, EmpresaPayloadRequest empresaPayloadRequest) {
        var empresa = empresaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Erro! Empresa não encontrada!"));


        empresa.setNome(empresaPayloadRequest.nome());
        empresa.setEmail(empresaPayloadRequest.email());
        empresa.setSenha(empresaPayloadRequest.senha());
        empresa.setListaOportunidades(empresaPayloadRequest.listaOportunidades());


        empresaRepository.save(empresa);

        return empresa;
    }


    public void delete(Long id) {
        var Empresa = empresaRepository.findById(id).orElseThrow(() -> new RuntimeException("Erro! Empresa não encontrado!"));

        empresaRepository.deleteById(id);
    }

    public Oportunidade criarOportunidade (Long id, OportunidadePayloadRequest oportunidadePayloadRequest){

        var empresa = findById(id);
        var oportunidade = new Oportunidade(oportunidadePayloadRequest.titulo(), oportunidadePayloadRequest.descricao(), oportunidadePayloadRequest.dataPublicacao(), empresa, oportunidadePayloadRequest.listaCandidatura());

        oportunidadeService.salvar(oportunidadePayloadRequest);
        empresa.getListaOportunidades().add(oportunidade);
        empresaRepository.save(empresa);
        return oportunidade;

    }

    public List<RecemFormado> procuraRecemFormado (String qualificacao){
      return recemFormadoService.qualificacaoRecemFormado(qualificacao);
    }

}
