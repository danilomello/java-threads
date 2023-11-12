package br.com.alura.threads.basico.lista;

public class TarefaImprimir implements Runnable {


    private final Lista lista;

    public TarefaImprimir(Lista lista) {
        this.lista = lista;
    }

    @Override
    public void run() {

        synchronized (lista) {

            if(!lista.estaCheia()) {
                try {
                    lista.wait();
                    System.out.println("aguardando notificação");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            for (int i = 0; i < lista.tamanho(); i++) {
                System.out.println(i + " - " + lista.pegaElemento(i));
            }
        }
    }
}
