package br.com.alura.threads.avancado.servidor;

import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;

public class DistribuirTarefas implements Runnable {
    private final ExecutorService threadPool;
    private Socket socket;
    private ServidorTarefas servidor;

    public DistribuirTarefas(ExecutorService threadPool, Socket socket, ServidorTarefas servidor) {
        this.threadPool = threadPool;
        this.socket = socket;
        this.servidor = servidor;
    }

    @Override
    public void run() {

        try {
            System.out.println("Distribuindo tarefas para " + socket);
            Scanner entradaCliente = new Scanner(socket.getInputStream());
            PrintStream saidaCliente = new PrintStream(socket.getOutputStream());

            while (entradaCliente.hasNextLine()) {
                String comando = entradaCliente.nextLine();
                System.out.println("Comando recebido: " + comando);

                switch (comando) {
                    case "c1": {
                        saidaCliente.println("Usou o comando c1");
                        ComandoC1 c1 = new ComandoC1(saidaCliente);
                        // aproveita a thread pool do servidor
                        threadPool.execute(c1);
                        break;
                    }
                    case "c2": {
                        saidaCliente.println("Usou o comando c2");
                        ComandoC2 comandoC2 = new ComandoC2(saidaCliente);
                        threadPool.execute(comandoC2);
                        break;
                    }
                    case "fim": {
                        saidaCliente.println("Desligando o servidor");
                        servidor.parar();
                        break;
                    }
                    default: {
                        saidaCliente.println("usou comando não existente aqui");
                    }
                }
            }
            entradaCliente.close();
            saidaCliente.close();
        } catch (Exception e) {
            e.printStackTrace();
        }



    }
}
