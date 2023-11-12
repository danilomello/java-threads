package br.com.alura.threads.avancado.cliente;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class ClienteTarefas {

    public static void main(String[] args) throws Exception {

        Socket socket = new Socket("localhost", 12345);
        System.out.println("conexão extabelecida");

        Thread threadEnviaComando = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("--- pode enviar comandos ---");
                    PrintStream saida = new PrintStream(socket.getOutputStream());
                    Scanner teclado = new Scanner(System.in);
                    while (teclado.hasNextLine()) {
                        String linha = teclado.nextLine();
                        if (linha.trim().equals("")) {
                            break;
                        }
                        saida.println(linha);
                    }
                    saida.close();
                    teclado.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread threadRecebeRespostaServidor = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Scanner respostaServidor = new Scanner(socket.getInputStream());
                    System.out.println("--- recebendo dados do servidor ---");
                    while (respostaServidor.hasNextLine()) {
                        String linha = respostaServidor.nextLine();
                        System.out.println(linha);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        threadEnviaComando.start();
        threadRecebeRespostaServidor.start();

        // Abaixou temos o uso de lambda para a chamda da thread.
        // con essa forma não é preciso criar uma classe anônima para
        // execução de tarefas simples em novas threads
//        new Thread( () -> { System.out.println("rodando");} ).start();

        // o comando join informa à thread main que é para "juntar"
        // com esta thread. A thread mais só seguirá com a execução
        // do socket.close() quando esta thread finalizar sua execução
        threadEnviaComando.join();

        // ao enviar o tempo de espera para o método join() dizemos
        // para a thread aguardar X tempo para se juntar; Passado
        // esse tempo a thread principal continuará mesmo que a outra
        // não tenha finalizado ainda
//        threadEnviaComando.join(30000);

        System.out.println("--- fechando socket do cliente ---");
        socket.close();
    }
}
