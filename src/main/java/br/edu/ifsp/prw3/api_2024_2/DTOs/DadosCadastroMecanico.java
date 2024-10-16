package br.edu.ifsp.prw3.api_2024_2.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroMecanico(
        @NotBlank
        @NotNull
        String nome,

        @NotNull
        int anosExperiencia
) {
}
