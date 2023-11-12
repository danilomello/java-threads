package br.com.alura.threads.avancado.servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

public class ServidorTarefas {

    private ServerSocket servidor;
    private ExecutorService threadPool;

    /**
     * o AtomicBoolean tem as mesmas características de boolean volatil
     *
     * <a href="https://docs.oracle.com/javase/tutorial/essential/concurrency/atomicvars.html">Exemplo e comparação com synchronized</a>
     *
     * <a href="https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/atomic/package-summary.html">documentação</a>
     */
    private AtomicBoolean estaRodando;

    public ServidorTarefas() throws IOException {
        // Inicialização
        System.out.println("--- Iniciando servidor ---");
        this.servidor = new ServerSocket(12345);
        // cria um pool de threads de quantidade constante
        this.threadPool = Executors.newFixedThreadPool(4);
        // cria um pool de threads dinamicamente
//        ExecutorService threadPool = Executors.newCachedThreadPool();
        this.estaRodando = new AtomicBoolean(true);
    }

    public static void main(String[] args) throws Exception {
        ServidorTarefas servidor = new ServidorTarefas();
        servidor.rodar();
        servidor.parar();
    }


    public void rodar() throws IOException {

        while(this.estaRodando.get()) {

            try {
                Socket socket = servidor.accept();
                System.out.println("Aceitando cliente na porta " + socket.getPort());

                DistribuirTarefas distribuirTarefas = new DistribuirTarefas(threadPool, socket, this);
                threadPool.execute(distribuirTarefas);
    //            Thread threadCliente = new Thread(distribuirTarefas);
    //            threadCliente.start();

    //            System.out.println("--- Threads em execução ---");
    //            Set<Thread> todasAsThreads = Thread.getAllStackTraces().keySet();
    //            for (Thread thread: todasAsThreads ) {
    //                System.out.println("Thread: " + thread.getName());
    //            }

            } catch (SocketException e) {
                System.out.println("SockeException, Está rodando? " + this.estaRodando);
            }

        }
    }


    public void parar() throws IOException {
        // finalização
        this.estaRodando.set(false);
        servidor.close();
        threadPool.shutdown();
    }
}
