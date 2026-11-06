package br.com.desafiohabitos.controlador;

import br.com.desafiohabitos.modelo.Habito;
import br.com.desafiohabitos.servico.HabitoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/habitos")
@CrossOrigin(origins = "http://localhost:4200")
public class HabitoController {

    private final HabitoService habitoService;

    public HabitoController(HabitoService habitoService) {
        this.habitoService = habitoService;
    }

    @GetMapping
    public List<Habito> listar() {
        return habitoService.listar();
    }

    @GetMapping("/{id}")
    public Habito buscarPorId(@PathVariable Long id) {
        return habitoService.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Habito criar(@Valid @RequestBody Habito habito) {
        return habitoService.criar(habito);
    }

    @PutMapping("/{id}")
    public Habito atualizar(@PathVariable Long id, @Valid @RequestBody Habito habito) {
        return habitoService.atualizar(id, habito);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        habitoService.excluir(id);
    }

    @PostMapping("/{id}/checkin")
    public Habito realizarCheckin(@PathVariable Long id) {
        return habitoService.realizarCheckin(id);
    }
}
