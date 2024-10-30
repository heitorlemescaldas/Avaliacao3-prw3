package br.edu.ifsp.prw3.api_2024_2.DTOs;

public record DadosResumoConserto(
        Long id,
        String dataEntrada,
        String dataSaida,
        String mecanicoNome,
        String veiculoMarca,
        String veiculoModelo
) {
}
