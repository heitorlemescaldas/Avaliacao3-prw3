package br.edu.ifsp.prw3.api_2024_2.controller;

import br.edu.ifsp.prw3.api_2024_2.ConsertoRepository;
import br.edu.ifsp.prw3.api_2024_2.DTOs.DadosCadastroConserto;
import br.edu.ifsp.prw3.api_2024_2.models.Conserto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consertos")
public class ConsertoController {

    @Autowired
    private ConsertoRepository repository;

    @PostMapping
    public ResponseEntity<Conserto> cadastrarConserto(@RequestBody DadosCadastroConserto dados) {
        Conserto conserto = new Conserto(dados);
        repository.save(conserto);
        return ResponseEntity.ok(conserto);
    }
}

