package br.com.alura.threads.basico.banheiro;

public class Banheiro {

    private boolean ehSujo = true;

    public void fazNumero1() {
        String nome = Thread.currentThread().getName();
        System.out.println(nome + " batendo na porta");

        // o synchronized informa que este trcho não pode ser
        // executado em paralelo por mais de uma thread
        // também chamado de mutex
        synchronized (this) {
            System.out.println(nome + " entrando no banheiro");

            while (ehSujo) {
                esperaLaFora(nome);
            }

            System.out.println(nome + " fazendo coisa rápida");
            dormindoUmPouco(5000);
            this.ehSujo = true;
            System.out.println(nome + " dando descarga");
            System.out.println(nome + " lavando a mão");
            System.out.println(nome + " saindo do banheiro");
        }
    }

    public void fazNumero2() {
        String nome = Thread.currentThread().getName();
        System.out.println(nome + " batendo na porta");

        synchronized (this) {
            System.out.println(nome + " entrando no banheiro");

            while (ehSujo) {
                esperaLaFora(nome);
            }

            System.out.println(nome + " fazendo coisa demorada");
            dormindoUmPouco(10000);
            this.ehSujo = true;
            System.out.println(nome + " dando descarga");
            System.out.println(nome + " lavando a mão");
            System.out.println(nome + " saindo do banheiro");
        }
    }

    public void limpa() {
        String nome = Thread.currentThread().getName();
        System.out.println(nome + " a batendo na porta");

        synchronized (this) {

            System.out.println(nome + " entrando no banheiro");

            if (!ehSujo) {
                System.out.println(nome + " não está sujo, vou sair");
                return;
            }

            System.out.println(nome + " limpando o banheiro");
            this.ehSujo = false;

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // o notifyAll informa as threads em espera (wait) que podem seguir
            this.notifyAll();
            this.notify();
            System.out.println(nome + " saindo do banheiro");
        }

    }

    private void esperaLaFora(String nome) {
        System.out.println(nome + ": eca, o banheiro está sujo!");
        try {
            // o wait só pode ser chamado dentro de um bloco sincronizado
            this.wait();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static void dormindoUmPouco(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
