package br.com.alura.threads.avancado.servidor;

import java.io.PrintStream;

public class ComandoC2 implements  Runnable {
    private final PrintStream saida;

    public ComandoC2(PrintStream saida) {
        this.saida = saida;
    }

    @Override
    public void run() {
        System.out.println("Executando comando c2");

        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        saida.println("Comando c2 executado com sucesso");
    }
}
