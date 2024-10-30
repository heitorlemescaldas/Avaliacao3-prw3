package br.edu.ifsp.prw3.api_2024_2.controller;

import br.edu.ifsp.prw3.api_2024_2.ConsertoRepository;
import br.edu.ifsp.prw3.api_2024_2.DTOs.*;
import br.edu.ifsp.prw3.api_2024_2.models.Conserto;
import br.edu.ifsp.prw3.api_2024_2.models.Mecanico;
import br.edu.ifsp.prw3.api_2024_2.models.Veiculo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/consertos")
public class ConsertoController {

    @Autowired
    private ConsertoRepository repository;

    @GetMapping
    public ResponseEntity<Page<DadosCadastroConserto>> listarConsertos(Pageable pageable) {
        return ResponseEntity.ok(repository.findAll(pageable).map(this::toDadosCadastroConserto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getConserto(@PathVariable Long id) {
        Optional<Conserto> conserto = repository.findById(id);
        if (conserto.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(conserto.get());
    }

    @GetMapping("/resumo")
    public ResponseEntity<List<DadosResumoConserto>> listarResumoConsertos() {
        List<DadosResumoConserto> resumos = repository.findAll().stream()
                .map(this::toDadosResumoConserto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resumos);
    }

    private DadosResumoConserto toDadosResumoConserto(Conserto conserto) {
        return new DadosResumoConserto(
                conserto.getId(),
                conserto.getDataEntrada(),
                conserto.getDataSaida(),
                conserto.getMecanico().getNome(),
                conserto.getVeiculo().getMarca(),
                conserto.getVeiculo().getModelo()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Conserto> atualizarConserto(
            @PathVariable Long id,
            @RequestBody DadosAtualizacaoConserto dados) {

        Conserto conserto = repository.findById(id).orElseThrow(() -> new RuntimeException("Conserto não encontrado"));

        if (dados.dataSaida() != null) {
            conserto.setDataSaida(dados.dataSaida());
        }
        if (dados.mecanicoNome() != null) {
            conserto.getMecanico().setNome(dados.mecanicoNome());
        }
        if (dados.mecanicoAnosExperiencia() != null) {
            conserto.getMecanico().setAnosExperiencia(dados.mecanicoAnosExperiencia());
        }

        repository.save(conserto);
        return ResponseEntity.ok(conserto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirConserto(@PathVariable Long id) {
        Conserto conserto = repository.findById(id).orElseThrow(() -> new RuntimeException("Conserto não encontrado"));
        conserto.setAtivo(false);
        repository.delete(conserto);
        return ResponseEntity.noContent().build();
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
