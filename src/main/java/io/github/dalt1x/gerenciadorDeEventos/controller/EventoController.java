package io.github.dalt1x.gerenciadorDeEventos.controller;

import io.github.dalt1x.gerenciadorDeEventos.dto.EventoDTO;
import io.github.dalt1x.gerenciadorDeEventos.dto.ParticipanteDTO;
import io.github.dalt1x.gerenciadorDeEventos.service.EventoService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/eventos")
@RequiredArgsConstructor
public class EventoController {

    private final EventoService service;

    @GetMapping
    public ResponseEntity<List<EventoDTO>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventoDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<EventoDTO> criar(@Validated @RequestBody EventoDTO dto) {
        return ResponseEntity.ok(service.criar(dto));
    }

    @PostMapping("/{eventoId}/participantes")
    public ResponseEntity<ParticipanteDTO> adicionarParticipante(@PathVariable Long eventoId, @RequestBody ParticipanteDTO dto) {
        ParticipanteDTO novoParticipante = service.adicionarParticipante(eventoId, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoParticipante);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventoDTO> atualizar(@PathVariable Long id, @Validated @RequestBody EventoDTO dto) {
        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}