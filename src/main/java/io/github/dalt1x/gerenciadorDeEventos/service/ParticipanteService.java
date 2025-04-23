package io.github.dalt1x.gerenciadorDeEventos.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import io.github.dalt1x.gerenciadorDeEventos.repository.ParticipanteRepository;
import io.github.dalt1x.gerenciadorDeEventos.dto.ParticipanteDTO;
import io.github.dalt1x.gerenciadorDeEventos.entity.Evento;
import io.github.dalt1x.gerenciadorDeEventos.entity.Participante;
import io.github.dalt1x.gerenciadorDeEventos.repository.EventoRepository;
import io.github.dalt1x.gerenciadorDeEventos.exception.RecursoNaoEncontrado;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ParticipanteService {
    private final ParticipanteRepository participanteRepository;
    private final EventoRepository eventoRepository;

    // lista todos os participantes associados a um evento
    public List<ParticipanteDTO> listarPorEvento(Long eventoId) {
        return participanteRepository.findByEventoId(eventoId)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // Busca um participante pelo ID e lança exceção se não for encontrado
    public ParticipanteDTO buscarPorId(Long id) {
        Participante participante = participanteRepository.findById(id) 
                .orElseThrow(() -> new RecursoNaoEncontrado("Participante não encontrado"));
                return toDTO(participante);
    }

    // Atualiza os dados de um participante existente e verifica se o evento existe
    public ParticipanteDTO atualizar(Long id, ParticipanteDTO dto) {
        Participante existente = participanteRepository.findById(id) 
                .orElseThrow(() -> new RecursoNaoEncontrado("Participante não encontrado"));

        Evento evento = eventoRepository.findById(dto.getEventoId())
                .orElseThrow(() -> new RecursoNaoEncontrado("Evento não encontrado"));

        existente.setNome(dto.getNome());
        existente.setNome(dto.getEmail());
        existente.setEvento(evento);

        return toDTO(participanteRepository.save(existente));
    }

    // Remove um participante pelo ID e lança exceção se não for encontrado
    public void deletar(Long id) {
        Participante participante = participanteRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontrado("Participante não encontrado"));
        participanteRepository.delete(participante);
    }

    // Converte a entidade Participante para DTO
    private ParticipanteDTO toDTO(Participante participante) {
        return ParticipanteDTO.builder()
                .id(participante.getId())
                .nome(participante.getNome())
                .email(participante.getEmail())
                .eventoId(participante.getEvento().getId())
                .build();
    }
}
