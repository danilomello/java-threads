package br.com.alura.threads.basico.banheiro;

public class TarefaNumero2 implements Runnable {
    private Banheiro banheiro;

    public TarefaNumero2(Banheiro banheiro) {
        this.banheiro = banheiro;
    }

    @Override
    public void run() {
        banheiro.fazNumero2();
    }
}
