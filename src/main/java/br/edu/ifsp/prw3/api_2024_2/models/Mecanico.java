package br.edu.ifsp.prw3.api_2024_2.models;

import br.edu.ifsp.prw3.api_2024_2.DTOs.DadosCadastroMecanico;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Mecanico {
    @Column(name = "mecanico_nome")
    private String nome;
    @Column(name = "mecanico_anos_experiencia")
    private int anosExperiencia;

    public Mecanico(DadosCadastroMecanico dados) {
        this.nome = dados.nome();
        this.anosExperiencia = dados.anosExperiencia();
    }
}
