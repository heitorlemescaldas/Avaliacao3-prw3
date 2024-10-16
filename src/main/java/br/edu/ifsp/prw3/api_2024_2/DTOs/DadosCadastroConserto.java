package br.edu.ifsp.prw3.api_2024_2.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroConserto(
        @NotBlank(message = "A data de entrada é obrigatória")
        @Pattern(regexp = "\\d{2}/\\d{2}/\\d{4}", message = "Data de entrada deve estar no formato dd/MM/yyyy")
        String dataEntrada,

        @NotBlank(message = "A data de saída é obrigatória")
        @Pattern(regexp = "\\d{2}/\\d{2}/\\d{4}", message = "Data de saída deve estar no formato dd/MM/yyyy")
        String dataSaida,

        @NotBlank(message = "O nome do mecânico é obrigatório")
        DadosCadastroMecanico mecanico,

        @NotBlank(message = "A marca do veículo é obrigatória")
        @Pattern(regexp = "\\d{4}", message = "O ano do veículo deve estar no formato yyyy")
        DadosCadastroVeiculo veiculo
) {
}
