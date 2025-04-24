package io.github.dalt1x.gerenciadorDeEventos.controller;

import io.github.dalt1x.gerenciadorDeEventos.dto.ParticipanteDTO;
import io.github.dalt1x.gerenciadorDeEventos.service.ParticipanteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/participantes")
@RequiredArgsConstructor
public class ParticipanteController {

    private final ParticipanteService participanteService;

    @GetMapping("/evento/{eventoId}")
    public ResponseEntity<List<ParticipanteDTO>> listarPorEvento(@PathVariable Long eventoId) {
        return ResponseEntity.ok(participanteService.listarPorEvento(eventoId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParticipanteDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(participanteService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParticipanteDTO> atualizar(@PathVariable Long id, @RequestBody ParticipanteDTO dto) {
        return ResponseEntity.ok(participanteService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        participanteService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
