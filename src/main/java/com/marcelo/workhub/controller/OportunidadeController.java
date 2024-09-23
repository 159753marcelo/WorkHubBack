package com.marcelo.workhub.controller;


import com.marcelo.workhub.model.Oportunidade;
import com.marcelo.workhub.payload.*;
import com.marcelo.workhub.service.OportunidadeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/Oportunidade")
public class OportunidadeController {
    @Autowired
    private OportunidadeService OportunidadeService;

    @Operation(summary = "Retorna lista com todos os conteúdos",  responses = {
            @ApiResponse(description = "Requisição feita com sucesso", responseCode = "200"),
            @ApiResponse(responseCode = "404", description = "Conteúdos não encontrados"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/Oportunidade")
    public ResponseEntity findAll() {
        var list = OportunidadeService.buscarTodasOportunidades();

        return ResponseEntity.ok(list.stream().map(OportunidadePayloadResponse::new));
    }

    @Operation(summary = "Retorna conteúdo pelo id",  responses = {
            @ApiResponse(description = "Requisição feita com sucesso", responseCode = "200"),
            @ApiResponse(responseCode = "404", description = "Conteúdos não encontrados"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable  (name = "id") Long id) {
        var oportunidade = OportunidadeService.findById(id);

        return ResponseEntity.ok(new OportunidadePayloadResponse(oportunidade));
    }

    @Operation(summary = "Salva conteúdo",  responses = {
            @ApiResponse(description = "Requisição feita com sucesso", responseCode = "201"),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação"),
            @ApiResponse(responseCode = "403", description = "Requisição não autorizada"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping
    public ResponseEntity save(@RequestBody  OportunidadePayloadRequest payload, UriComponentsBuilder builder) {
        var oportunidade =  OportunidadeService.salvar(payload);
        var uri = builder.path("/Oportunidade/{id}").buildAndExpand(oportunidade.getId()).toUri();

        return ResponseEntity.created(uri).body(new OportunidadePayloadResponse(oportunidade));

    }


    @Operation(summary = "Atualiza conteúdo pelo id",  responses = {
            @ApiResponse(description = "Requisição feita com sucesso", responseCode = "200"),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação"),
            @ApiResponse(responseCode = "403", description = "Requisição não autorizada"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PutMapping("/{id}")
    public ResponseEntity update (@PathVariable Long id, @RequestBody OportunidadePayloadRequest payload) {
        var oportunidade =  OportunidadeService.update(id, payload);

        return ResponseEntity.ok(new OportunidadePayloadResponse(oportunidade));
    }

    @Operation(summary = "Deleta conteúdo",  responses = {
            @ApiResponse(description = "Requisição feita com sucesso", responseCode = "204"),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação"),
            @ApiResponse(responseCode = "403", description = "Requisição não autorizada"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        OportunidadeService.delete(id);

        return ResponseEntity.noContent().build();
    }




}
