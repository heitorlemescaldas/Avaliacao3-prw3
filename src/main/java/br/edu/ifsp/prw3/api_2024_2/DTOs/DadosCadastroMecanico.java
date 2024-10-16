package br.edu.ifsp.prw3.api_2024_2.DTOs;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroMecanico(
        @NotBlank(message = "O nome do mecânico é obrigatório")
        String nome,

        int anosExperiencia
) {
}
