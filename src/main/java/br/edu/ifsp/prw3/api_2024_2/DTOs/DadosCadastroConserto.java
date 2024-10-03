package br.edu.ifsp.prw3.api_2024_2.DTOs;

public record DadosCadastroConserto(String dataEntrada, String dataSaida, DadosCadastroMecanico mecanico, DadosCadastroVeiculo veiculo) {
}
