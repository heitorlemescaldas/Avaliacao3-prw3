package br.edu.ifsp.prw3.api_2024_2.DTOs;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroConserto(
        @NotBlank(message = "A data de entrada é obrigatória")
        @NotNull
        @Pattern(regexp = "\\d{2}/\\d{2}/\\d{4}", message = "Data de entrada deve estar no formato dd/MM/yyyy")
        String dataEntrada,

        @NotBlank(message = "A data de saída é obrigatória")
        @NotNull
        @Pattern(regexp = "\\d{2}/\\d{2}/\\d{4}", message = "Data de saída deve estar no formato dd/MM/yyyy")
        String dataSaida,

        @Valid
        DadosCadastroMecanico mecanico,

        @Valid
        DadosCadastroVeiculo veiculo
) {
}
