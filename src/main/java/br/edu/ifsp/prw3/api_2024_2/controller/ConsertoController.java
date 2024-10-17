package br.edu.ifsp.prw3.api_2024_2.controller;

import br.edu.ifsp.prw3.api_2024_2.ConsertoRepository;
import br.edu.ifsp.prw3.api_2024_2.DTOs.DadosCadastroConserto;
import br.edu.ifsp.prw3.api_2024_2.DTOs.DadosCadastroMecanico;
import br.edu.ifsp.prw3.api_2024_2.DTOs.DadosCadastroVeiculo;
import br.edu.ifsp.prw3.api_2024_2.DTOs.DadosResumoConserto;
import br.edu.ifsp.prw3.api_2024_2.models.Conserto;
import br.edu.ifsp.prw3.api_2024_2.models.Mecanico;
import br.edu.ifsp.prw3.api_2024_2.models.Veiculo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/consertos")
public class ConsertoController {

    @Autowired
    private ConsertoRepository repository;

    // Método para retornar todos os dados com paginação
    @GetMapping
    public ResponseEntity<Page<DadosCadastroConserto>> listarConsertos(Pageable pageable) {
        return ResponseEntity.ok(repository.findAll(pageable).map(this::toDadosCadastroConserto));
    }

    // Método para retornar apenas datas, nome do mecânico, marca e modelo (sem paginação)
    @GetMapping("/resumo")
    public ResponseEntity<List<DadosResumoConserto>> listarResumoConsertos() {
        List<DadosResumoConserto> resumos = repository.findAll().stream()
                .map(this::toDadosResumoConserto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resumos);
    }

    // Método para converter um Conserto completo para um DTO de resumo
    private DadosResumoConserto toDadosResumoConserto(Conserto conserto) {
        return new DadosResumoConserto(
                conserto.getDataEntrada(),
                conserto.getDataSaida(),
                conserto.getMecanico().getNome(),
                conserto.getVeiculo().getMarca(),
                conserto.getVeiculo().getModelo()
        );
    }

    @PostMapping
    public ResponseEntity<Conserto> cadastrarConserto(@RequestBody DadosCadastroConserto dados) {
        Conserto conserto = new Conserto(dados);
        repository.save(conserto);
        return ResponseEntity.ok(conserto);
    }

    private DadosCadastroConserto toDadosCadastroConserto(Conserto conserto) {
        return new DadosCadastroConserto(conserto.getDataEntrada(),
                conserto.getDataSaida(),
                toDadosCadastroMecanico(conserto.getMecanico()),
                toDadosCadastroVeiculo(conserto.getVeiculo()));
    }

    private DadosCadastroMecanico toDadosCadastroMecanico(Mecanico mecanico) {
        return new DadosCadastroMecanico(mecanico.getNome(), mecanico.getAnosExperiencia());
    }

    private DadosCadastroVeiculo toDadosCadastroVeiculo(Veiculo veiculo) {
        return new DadosCadastroVeiculo(veiculo.getMarca(), veiculo.getModelo(), veiculo.getAno(), veiculo.getCor());
    }
}
