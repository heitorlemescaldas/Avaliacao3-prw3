package br.edu.ifsp.prw3.api_2024_2;

import br.edu.ifsp.prw3.api_2024_2.models.Conserto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConsertoRepository extends JpaRepository<Conserto, Long> {
    List<Conserto> findAllByAtivoTrue();
}