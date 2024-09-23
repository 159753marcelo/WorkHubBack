package com.marcelo.workhub.controller;
import com.marcelo.workhub.payload.*;
import com.marcelo.workhub.service.CandidaturaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/Candidatura")
public class CandidaturaController {
    @Autowired
    private CandidaturaService candidaturaService;

    @Operation(summary = "Retorna lista com todos os conteúdos",  responses = {
            @ApiResponse(description = "Requisição feita com sucesso", responseCode = "200"),
            @ApiResponse(responseCode = "404", description = "Conteúdos não encontrados"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/Candidatura")
    public ResponseEntity findAll() {
        var list = candidaturaService.buscarTodasCandidaturas();

        return ResponseEntity.ok(list.stream().map(CandidaturaPayloadResponse::new));
    }

    @Operation(summary = "Retorna conteúdo pelo id",  responses = {
            @ApiResponse(description = "Requisição feita com sucesso", responseCode = "200"),
            @ApiResponse(responseCode = "404", description = "Conteúdos não encontrados"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable  (name = "id") Long id) {
        var conteudo = candidaturaService.findById(id);

        return ResponseEntity.ok(new CandidaturaPayloadResponse(conteudo));
    }

    @Operation(summary = "Salva conteúdo",  responses = {
            @ApiResponse(description = "Requisição feita com sucesso", responseCode = "201"),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação"),
            @ApiResponse(responseCode = "403", description = "Requisição não autorizada"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping
    public ResponseEntity save(@RequestBody  CandidaturaPayloadRequest payload, UriComponentsBuilder builder) {
        var Candidatura =  candidaturaService.salvar(payload);
        var uri = builder.path("/Candidatura/{id}").buildAndExpand(Candidatura.getId()).toUri();
       
        return ResponseEntity.created(uri).body(new CandidaturaPayloadResponse(Candidatura));

    }


    @Operation(summary = "Atualiza conteúdo pelo id",  responses = {
            @ApiResponse(description = "Requisição feita com sucesso", responseCode = "200"),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação"),
            @ApiResponse(responseCode = "403", description = "Requisição não autorizada"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PutMapping("/{id}")
    public ResponseEntity update (@PathVariable Long id, @RequestBody CandidaturaPayloadRequest payload) {
        var CandidaturaAtualizada =  candidaturaService.update(id, payload);
        
        return ResponseEntity.ok(new CandidaturaPayloadResponse(CandidaturaAtualizada));
    }

    @Operation(summary = "Deleta conteúdo",  responses = {
            @ApiResponse(description = "Requisição feita com sucesso", responseCode = "204"),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação"),
            @ApiResponse(responseCode = "403", description = "Requisição não autorizada"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        candidaturaService.delete(id);

        return ResponseEntity.noContent().build();
    }


 

}
