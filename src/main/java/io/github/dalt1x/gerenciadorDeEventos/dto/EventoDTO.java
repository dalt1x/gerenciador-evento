package io.github.dalt1x.gerenciadorDeEventos.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.*;
import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventoDTO {

    private Long id;
    
    @NotBlank
    private String nome;

    private String descricao;

    @NotBlank
    private LocalDateTime data;

    @NotBlank
    private String local;

    @NotBlank
    private String organizador;

}