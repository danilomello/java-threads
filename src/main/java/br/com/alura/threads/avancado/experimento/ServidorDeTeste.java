package br.com.alura.threads.avancado.experimento;

public class ServidorDeTeste {

    /**
     * O uso de volatile faz com que a variável seja
     * acessada diretamente na memória principal. Ou seja, indica que a variável será alterada
     * entre as threads e seu valor não deve ser cacheado na thread
     */
    private volatile boolean estaRodando;

    public static void main(String[] args) throws InterruptedException {
        ServidorDeTeste servidor = new ServidorDeTeste();
        servidor.rodar();
        servidor.alterandoAtributo();
    }

    private void rodar() {

            new Thread(new Runnable() {
                @Override
                public void run() {
                    /*
                    Se otry-catch for inserido de forma a encapsular o a nova thread,
                    as exceções lançadas no método run não serão pegas porque estão
                    rodando em outra pilha
                     */
                    try {
                        System.out.println("Servidor cpmeçando, estaRodando=" + estaRodando);
                        while (!estaRodando) {}

                        if (estaRodando) {
                            throw new RuntimeException("Deu erro na thread...");
                        }

                        System.out.println("Servidor rodando, estaRodando=" + estaRodando);
                        while (estaRodando) {}

                        System.out.println("Servidor terminando, estaRodando=" + estaRodando);
                    } catch (Exception e) {
                        System.out.println("catch na thread main " + e.getMessage());
                    }
                }
            }).start();



    }

    private void alterandoAtributo() throws InterruptedException {
        Thread.sleep(5000);
        System.out.println("Mains alterando estaRodando=true");
        estaRodando = true;

        Thread.sleep(5000);
        System.out.println("Main alterando estaRodando=false");
        estaRodando = false;
    }
}
