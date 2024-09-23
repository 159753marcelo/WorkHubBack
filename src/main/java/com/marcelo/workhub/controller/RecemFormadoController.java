package com.marcelo.workhub.controller;


import com.marcelo.workhub.model.RecemFormado;
import com.marcelo.workhub.payload.*;
import com.marcelo.workhub.service.RecemFormadoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/RecemFormado")
public class RecemFormadoController {
    @Autowired
    private RecemFormadoService recemFormadoService;

    @Operation(summary = "Retorna lista com todos os conteúdos",  responses = {
            @ApiResponse(description = "Requisição feita com sucesso", responseCode = "200"),
            @ApiResponse(responseCode = "404", description = "Conteúdos não encontrados"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/RecemFormado")
    public ResponseEntity findAll() {
        var list = recemFormadoService.buscarTodasRecemFormados();

        return ResponseEntity.ok(list.stream().map(RecemFormadoPayloadResponse::new));
    }

    @Operation(summary = "Retorna conteúdo pelo id",  responses = {
            @ApiResponse(description = "Requisição feita com sucesso", responseCode = "200"),
            @ApiResponse(responseCode = "404", description = "Conteúdos não encontrados"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable  (name = "id") Long id) {
        var recemFormado = recemFormadoService.findById(id);

        return ResponseEntity.ok(new RecemFormadoPayloadResponse(recemFormado));
    }

    @Operation(summary = "Salva conteúdo",  responses = {
            @ApiResponse(description = "Requisição feita com sucesso", responseCode = "201"),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação"),
            @ApiResponse(responseCode = "403", description = "Requisição não autorizada"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping
    public ResponseEntity save(@RequestBody  RecemFormadoPayloadRequest payload, UriComponentsBuilder builder) {
        var RecemFormado =  recemFormadoService.salvar(payload);
        var uri = builder.path("/RecemFormado/{id}").buildAndExpand(RecemFormado.getId()).toUri();

        return ResponseEntity.created(uri).body(new RecemFormadoPayloadResponse(RecemFormado));

    }


    @Operation(summary = "Atualiza conteúdo pelo id",  responses = {
            @ApiResponse(description = "Requisição feita com sucesso", responseCode = "200"),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação"),
            @ApiResponse(responseCode = "403", description = "Requisição não autorizada"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PutMapping("/{id}")
    public ResponseEntity update (@PathVariable Long id, @RequestBody RecemFormadoPayloadRequest payload) {
        var RecemFormadoAtualizado =  recemFormadoService.update(id, payload);

        return ResponseEntity.ok(new RecemFormadoPayloadResponse(RecemFormadoAtualizado));
    }

    @Operation(summary = "Deleta conteúdo",  responses = {
            @ApiResponse(description = "Requisição feita com sucesso", responseCode = "204"),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação"),
            @ApiResponse(responseCode = "403", description = "Requisição não autorizada"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        recemFormadoService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Retorna lista de filmes",  responses = {
            @ApiResponse(description = "Requisição feita com sucesso", responseCode = "200"),
            @ApiResponse(responseCode = "404", description = "Conteúdos não encontrados"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/{idEmpresa}/oportunidade")
    public ResponseEntity fazerCandidatura (@PathVariable(name = "idEmpresa") Long id, @RequestBody CandidaturaPayloadRequest candidaturaPayloadRequest) {
        var Candidatura = recemFormadoService.fazerCandidatura(id, candidaturaPayloadRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(new CandidaturaPayloadResponse(Candidatura));
    }
    @Operation(summary = "Retorna lista de filmes",  responses = {
            @ApiResponse(description = "Requisição feita com sucesso", responseCode = "200"),
            @ApiResponse(responseCode = "404", description = "Conteúdos não encontrados"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/recemFormado/{qualificacao}")
    public ResponseEntity    procuraRecemFormado (@PathVariable(name = "qualificacao") String qualificacao) {
        var recemFormadoList = recemFormadoService.qualificacaoRecemFormado(qualificacao);

        return ResponseEntity.status(HttpStatus.CREATED).body(recemFormadoList.stream().map(RecemFormadoPayloadResponse::new));
    }
    @Operation(summary = "Retorna lista de filmes",  responses = {
            @ApiResponse(description = "Requisição feita com sucesso", responseCode = "200"),
            @ApiResponse(responseCode = "404", description = "Conteúdos não encontrados"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/oportunidade/{titulo}")
    public ResponseEntity   procuraOportunidadePorTitulo (@PathVariable(name = "titulo") String titulo) {
        var oportunidadeList = recemFormadoService.procuraOportunidadePorTitulo(titulo);

        return ResponseEntity.ok().body(oportunidadeList.stream().map(OportunidadePayloadResponse::new));
    }

}
