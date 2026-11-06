package br.com.desafiohabitos.repositorio;

import br.com.desafiohabitos.modelo.Habito;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HabitoRepository extends JpaRepository<Habito, Long> {
}
