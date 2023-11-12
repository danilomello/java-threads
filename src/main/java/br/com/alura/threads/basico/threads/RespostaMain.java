package br.com.alura.threads.basico.threads;

public class RespostaMain {

    public static void main(String[] args) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Resposta: execução em classe anônima");
            }
        }).start();
    }
}
