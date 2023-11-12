package br.com.alura.threads.basico.lista;

public class TarefaAdiconarElemento implements Runnable {

    private final int numeroDoThread;
    private Lista lista;
    public TarefaAdiconarElemento(Lista lista, int numeroDoThread) {
        this.lista = lista;
        this.numeroDoThread = numeroDoThread;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            lista.adicionaElementos("Thread " + numeroDoThread + " - " + i);
        }

    }
}
