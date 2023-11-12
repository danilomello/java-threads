package br.com.alura.threads.basico.banheiro;

public class TarefaLimpeza implements Runnable {

    private Banheiro banheiro;

    public TarefaLimpeza(Banheiro banheiro) {
        this.banheiro = banheiro;
    }

    @Override
    public void run() {
        while (true) {
            banheiro.limpa();
            try {
                Thread.sleep(1500); // limpa a cada 15 segundo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
