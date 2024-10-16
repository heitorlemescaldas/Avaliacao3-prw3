package br.edu.ifsp.prw3.api_2024_2.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroVeiculo(
        @NotBlank(message = "A marca do veículo é obrigatória")
        @NotNull
        String marca,

        @NotBlank(message = "O modelo do veículo é obrigatório")
        @NotNull
        String modelo,

        @NotBlank(message = "O ano do veículo é obrigatório")
        @NotNull
        @Pattern(regexp = "\\d{4}", message = "O ano do veículo deve estar no formato yyyy")
        String ano,

        @NotBlank(message = "A cor do veículo é obrigatória")
        @NotNull
        String cor
) {
}
