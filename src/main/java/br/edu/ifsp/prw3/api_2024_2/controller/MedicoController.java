package br.edu.ifsp.prw3.api_2024_2.controller;

import br.edu.ifsp.prw3.api_2024_2.medico.DadosCadastroMedico;
import br.edu.ifsp.prw3.api_2024_2.medico.Medico;
import br.edu.ifsp.prw3.api_2024_2.medico.MedicoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody DadosCadastroMedico dados){

        repository.save( new Medico(dados) );

    }

}


