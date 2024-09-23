package com.marcelo.workhub.controller;


import com.marcelo.workhub.model.Empresa;
import com.marcelo.workhub.payload.*;
import com.marcelo.workhub.service.EmpresaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/Empresa")
public class EmpresaController {
    @Autowired
    private EmpresaService empresaservice;

    @Operation(summary = "Retorna lista com todos os conteúdos",  responses = {
            @ApiResponse(description = "Requisição feita com sucesso", responseCode = "200"),
            @ApiResponse(responseCode = "404", description = "Conteúdos não encontrados"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/Empresa")
    public ResponseEntity findAll() {
        var list = empresaservice.buscarTodasEmpresas();

        return ResponseEntity.ok(list.stream().map(EmpresaPayloadResponse::new));
    }

    @Operation(summary = "Retorna conteúdo pelo id",  responses = {
            @ApiResponse(description = "Requisição feita com sucesso", responseCode = "200"),
            @ApiResponse(responseCode = "404", description = "Conteúdos não encontrados"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable  (name = "id") Long id) {
        var conteudo = empresaservice.findById(id);

        return ResponseEntity.ok(new EmpresaPayloadResponse(conteudo));
    }

    @Operation(summary = "Salva conteúdo",  responses = {
            @ApiResponse(description = "Requisição feita com sucesso", responseCode = "201"),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação"),
            @ApiResponse(responseCode = "403", description = "Requisição não autorizada"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping
    public ResponseEntity save(@RequestBody  EmpresaPayloadRequest payload, UriComponentsBuilder builder) {
        var Empresa =  empresaservice.salvar(payload);
        var uri = builder.path("/Empresa/{id}").buildAndExpand(Empresa.getId()).toUri();

        return ResponseEntity.created(uri).body(new EmpresaPayloadResponse(Empresa));

    }


    @Operation(summary = "Atualiza conteúdo pelo id",  responses = {
            @ApiResponse(description = "Requisição feita com sucesso", responseCode = "200"),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação"),
            @ApiResponse(responseCode = "403", description = "Requisição não autorizada"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PutMapping("/{id}")
    public ResponseEntity update (@PathVariable Long id, @RequestBody EmpresaPayloadRequest payload) {
        var EmpresaAtualizada =  empresaservice.update(id, payload);

        return ResponseEntity.ok(new EmpresaPayloadResponse(EmpresaAtualizada));
    }

    @Operation(summary = "Deleta conteúdo",  responses = {
            @ApiResponse(description = "Requisição feita com sucesso", responseCode = "204"),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação"),
            @ApiResponse(responseCode = "403", description = "Requisição não autorizada"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        empresaservice.delete(id);

        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Retorna lista de filmes",  responses = {
            @ApiResponse(description = "Requisição feita com sucesso", responseCode = "200"),
            @ApiResponse(responseCode = "404", description = "Conteúdos não encontrados"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/{idEmpresa}/oportunidade")
    public ResponseEntity criarOportunidade (@PathVariable(name = "idEmpresa") Long id, @RequestBody OportunidadePayloadRequest oportunidadePayloadRequest) {
        var oportunidade = empresaservice.criarOportunidade(id, oportunidadePayloadRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(new OportunidadePayloadResponse(oportunidade));
    }
    @Operation(summary = "Retorna lista de filmes",  responses = {
            @ApiResponse(description = "Requisição feita com sucesso", responseCode = "200"),
            @ApiResponse(responseCode = "404", description = "Conteúdos não encontrados"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/recemFormado/{qualificacao}")
    public ResponseEntity    procuraRecemFormado (@PathVariable(name = "qualificacao") String qualificacao, @RequestBody OportunidadePayloadRequest oportunidadePayloadRequest) {
        var recemFormadoList = empresaservice.procuraRecemFormado(qualificacao);

        return ResponseEntity.status(HttpStatus.CREATED).body(recemFormadoList.stream().map(RecemFormadoPayloadResponse::new));
    }



}
