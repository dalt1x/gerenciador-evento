package io.github.dalt1x.gerenciadorDeEventos.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParticipanteDTO {
    private Long id;

    @NotBlank
    private String nome;

    @Email
    @NotBlank
    private String email;

    private String telefone;
    
    @NotNull
    private Long eventoId;
}
