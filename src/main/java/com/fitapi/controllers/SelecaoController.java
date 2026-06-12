package com.fitapi.controllers;

import com.fitapi.dtos.request.SelecaoRequestDTO;
import com.fitapi.dtos.response.JogadorResponseDTO;
import com.fitapi.dtos.response.PartidaResponseDTO;
import com.fitapi.dtos.response.SelecaoResponseDTO;
import com.fitapi.services.interfaces.SelecaoService;
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
@RequestMapping("/selecoes")
@RequiredArgsConstructor
public class SelecaoController {

    private final SelecaoService selecaoService;

    @GetMapping
    public List<SelecaoResponseDTO> listar() {
        return selecaoService.listar();
    }

    @GetMapping("/{id}")
    public SelecaoResponseDTO buscarPorId(@PathVariable Long id) {
        return selecaoService.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SelecaoResponseDTO criar(@RequestBody SelecaoRequestDTO selecaoRequest) {
        return selecaoService.criar(selecaoRequest);
    }

    @PutMapping("/{id}")
    public SelecaoResponseDTO atualizar(@PathVariable Long id, @RequestBody SelecaoRequestDTO selecaoRequest) {
        return selecaoService.atualizar(id, selecaoRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) {
        selecaoService.remover(id);
    }

    @GetMapping("/{id}/jogadores")
    public List<JogadorResponseDTO> listarJogadores(@PathVariable Long id) {
        return selecaoService.listarJogadores(id);
    }

    @GetMapping("/{id}/partidas")
    public List<PartidaResponseDTO> listarPartidas(@PathVariable Long id) {
        return selecaoService.listarPartidas(id);
    }
}
