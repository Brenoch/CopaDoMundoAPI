package com.fitapi.controllers;

import com.fitapi.dtos.request.JogadorRequestDTO;
import com.fitapi.dtos.response.JogadorResponseDTO;
import com.fitapi.services.interfaces.JogadorService;
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
@RequestMapping("/jogadores")
@RequiredArgsConstructor
public class JogadorController {

    private final JogadorService jogadorService;

    @GetMapping
    public List<JogadorResponseDTO> listar() {
        return jogadorService.listar();
    }

    @GetMapping("/{id}")
    public JogadorResponseDTO buscarPorId(@PathVariable Long id) {
        return jogadorService.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public JogadorResponseDTO criar(@RequestBody JogadorRequestDTO jogadorRequest) {
        return jogadorService.criar(jogadorRequest);
    }

    @PutMapping("/{id}")
    public JogadorResponseDTO atualizar(@PathVariable Long id, @RequestBody JogadorRequestDTO jogadorRequest) {
        return jogadorService.atualizar(id, jogadorRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) {
        jogadorService.remover(id);
    }
}
