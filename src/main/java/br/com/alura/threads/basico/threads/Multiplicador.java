package br.com.alura.threads.basico.threads;

// a classe Thread já implement a interface Runnable, sendo assim é possível criar
// uma subclasse dela e sobrescrever o método run. Assim não precisaria da classe
// separada para implementar a tarefa executar
public class Multiplicador extends Thread {

    public void run() {
        // calculo
    }

    // o método acima pode ser chamado e iniciado da seguinte forma
    // Multiplicador multiplicador = new Multiplicador();
    // multiplicador.start();
}
