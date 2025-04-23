package io.github.dalt1x.gerenciadorDeEventos.service;

import io.github.dalt1x.gerenciadorDeEventos.repository.EventoRepository;
import io.github.dalt1x.gerenciadorDeEventos.repository.ParticipanteRepository;
import io.github.dalt1x.gerenciadorDeEventos.dto.EventoDTO;
import io.github.dalt1x.gerenciadorDeEventos.dto.ParticipanteDTO;
import io.github.dalt1x.gerenciadorDeEventos.entity.Evento;
import io.github.dalt1x.gerenciadorDeEventos.entity.Participante;
import io.github.dalt1x.gerenciadorDeEventos.exception.RecursoNaoEncontrado;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventoService {
    private final EventoRepository eventoRepository;
    private final ParticipanteRepository participanteRepository;
    
    public List<EventoDTO> listarTodos() {
        return eventoRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // Busca um evento pelo ID e lança exceção se não for encontrado
    public EventoDTO buscarPorId(Long id) {
        Evento evento = eventoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontrado("Evento não encontrado"));
                return toDTO(evento);     
    }

    // Retorna a entidade Evento diretamente para uso interno do serviço.
    public Evento buscarEntidadePorId(Long id) {
        return eventoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontrado("Evento não encontrado"));
    }

    public EventoDTO criar(EventoDTO dto) {
        Evento evento = eventoRepository.save(toEntity(dto));
        return toDTO(evento);
    }
    
    public EventoDTO atualizar(Long id, EventoDTO dto) {
        Evento existente = eventoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontrado("Evento não encontrado"));

            existente.setNome(dto.getNome());
            existente.setDescricao(dto.getDescricao());
            existente.setData(dto.getData());
            existente.setLocal(dto.getLocal());
            existente.setOrganizador(dto.getOrganizador());

            return toDTO(eventoRepository.save(existente));
    }

    public void deletar(Long id) {
        Evento evento = eventoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontrado("Evento não encontrado"));
        eventoRepository.delete(evento);
    }

    public ParticipanteDTO adicionarParticipante(Long eventoId, ParticipanteDTO dto) {
        Evento evento = buscarEntidadePorId(eventoId);

        Participante participante = Participante.builder()
                .nome(dto.getNome())
                .email(dto.getEmail())
                .evento(evento)
                .build();

        participante = participanteRepository.save(participante);

        return ParticipanteDTO.builder()
                .id(participante.getId())
                .nome(participante.getNome())
                .email(participante.getEmail())
                .eventoId(evento.getId())
                .build();
    }

    private Evento toEntity(EventoDTO dto) {
        return Evento.builder()
                .id(dto.getId())
                .nome(dto.getNome())
                .descricao(dto.getDescricao())
                .data(dto.getData())
                .local(dto.getLocal())
                .organizador(dto.getOrganizador())
                .build();
    }
    private EventoDTO toDTO(Evento evento) {
        return EventoDTO.builder()
                .id(evento.getId())
                .nome(evento.getNome())
                .descricao(evento.getDescricao())
                .data(evento.getData())
                .local(evento.getLocal())
                .organizador(evento.getOrganizador())
                .build();
    }
}
