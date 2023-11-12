package br.com.alura.threads.basico.lista;

public class Lista {

    private String[] elementos = new String[100];
    private int indice = 0;

    public synchronized void adicionaElementos(String elemento) {
        this.elementos[indice] = elemento;
        this.indice++;

        if (this.indice == this.elementos.length) {
            System.out.println("lista cheia, notificando");
            this.notify();
        }
    }

    public int tamanho() {
        return this.elementos.length;
    }

    public String pegaElemento(int posicao) {
        return this.elementos[posicao];
    }

    public boolean estaCheia() {
        return this.indice == this.tamanho();
    }
}
