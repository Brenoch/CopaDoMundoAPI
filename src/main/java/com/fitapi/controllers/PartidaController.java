package com.fitapi.controllers;

import com.fitapi.dtos.request.PartidaRequestDTO;
import com.fitapi.dtos.response.PartidaResponseDTO;
import com.fitapi.services.interfaces.PartidaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/partidas")
@RequiredArgsConstructor
public class PartidaController {

    private final PartidaService partidaService;

    @GetMapping
    public List<PartidaResponseDTO> listar() {
        return partidaService.listar();
    }

    @GetMapping("/{id}")
    public PartidaResponseDTO buscarPorId(@PathVariable Long id) {
        return partidaService.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PartidaResponseDTO criar(@RequestBody PartidaRequestDTO partidaRequest) {
        return partidaService.criar(partidaRequest);
    }

    @PutMapping("/{id}")
    public PartidaResponseDTO atualizar(@PathVariable Long id, @RequestBody PartidaRequestDTO partidaRequest) {
        return partidaService.atualizar(id, partidaRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) {
        partidaService.remover(id);
    }
}
