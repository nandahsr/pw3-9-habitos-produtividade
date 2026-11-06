package br.com.desafiohabitos.excecao;

public class HabitoNaoEncontradoException extends RuntimeException {

    public HabitoNaoEncontradoException(Long id) {
        super("Hábito com id " + id + " não foi encontrado.");
    }
}
