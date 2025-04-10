package io.github.dalt1x.gerenciadorDeEventos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.dalt1x.gerenciadorDeEventos.entity.Evento;

public interface EventoRepository extends JpaRepository<Evento, Long> {
}
