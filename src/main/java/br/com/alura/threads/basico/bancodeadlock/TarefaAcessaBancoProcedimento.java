package br.com.alura.threads.basico.bancodeadlock;

public class TarefaAcessaBancoProcedimento implements Runnable {
    private PoolDeConexao pool;
    private GerenciadorDeTransacao tx;

    public TarefaAcessaBancoProcedimento(PoolDeConexao pool, GerenciadorDeTransacao tx) {
        this.pool = pool;
        this.tx = tx;
    }

    @Override
    public void run() {
        // o fato das chaves estarem invertidas em relação ao método run da classe TarefaAcessaBanco
        // implica um deadlock. A ordem das chaves deve sempre ser a mesma nas classes que utilizam
        // os mesmos recursos de forma sincronizada/paralela.

        // o deadlock pode ser verificado no JConsole que exibirá a thread com o status DEALOCK
        // o JConsole também possui uma funcionalidade de detecção de deadlock
        synchronized (tx) {
            System.out.println("começando a gerenciar a tx");
            tx.begin();

            synchronized (pool) {
                System.out.println("peguei a chave");
                pool.getConnection();
            }
        }
    }
}
