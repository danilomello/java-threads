package br.com.alura.threads.basico.lista;

public class PrincipalLista {

    public static void main(String[] args) throws InterruptedException {
//        List<String> lista = Collections.synchronizedList(new ArrayList<>());

        // o Vector implementa uma arraylist já sincronizada
        Lista lista = new Lista();

        for (int i = 0; i < 10; i++) {
            new Thread(new TarefaAdiconarElemento(lista, i)).start();
        }

        new Thread(new TarefaImprimir(lista)).start();

    }
}
