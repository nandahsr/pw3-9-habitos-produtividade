package br.com.desafiohabitos.servico;

import br.com.desafiohabitos.excecao.HabitoNaoEncontradoException;
import br.com.desafiohabitos.modelo.Habito;
import br.com.desafiohabitos.repositorio.HabitoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HabitoService {

    private final HabitoRepository habitoRepository;

    public HabitoService(HabitoRepository habitoRepository) {
        this.habitoRepository = habitoRepository;
    }

    public List<Habito> listar() {
        return habitoRepository.findAll();
    }

    public Habito buscarPorId(Long id) {
        return habitoRepository.findById(id)
                .orElseThrow(() -> new HabitoNaoEncontradoException(id));
    }

    public Habito criar(Habito habito) {
        // Estes valores são controlados pela regra de negócio, não pela requisição.
        habito.setId(null);
        habito.setDiasConsecutivos(0);
        habito.setConquistaAlcancada(false);
        return habitoRepository.save(habito);
    }

    public Habito atualizar(Long id, Habito habitoAtualizado) {
        Habito habitoExistente = buscarPorId(id);
        // A edição só altera os campos permitidos pelo enunciado.
        habitoExistente.setDescricao(habitoAtualizado.getDescricao());
        habitoExistente.setMetaDias(habitoAtualizado.getMetaDias());
        return habitoRepository.save(habitoExistente);
    }

    public void excluir(Long id) {
        Habito habito = buscarPorId(id);
        habitoRepository.delete(habito);
    }

    public Habito realizarCheckin(Long id) {
        Habito habito = buscarPorId(id);
        habito.setDiasConsecutivos(habito.getDiasConsecutivos() + 1);

        if (habito.getDiasConsecutivos() >= habito.getMetaDias()) {
            habito.setConquistaAlcancada(true);
        }

        return habitoRepository.save(habito);
    }
}
