package io.github.dalt1x.gerenciadorDeEventos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.dalt1x.gerenciadorDeEventos.entity.Participante;

public interface ParticipanteRepository extends JpaRepository<Participante, Long>  {
    List<Participante> findByEventoId(Long eventoId);
}
