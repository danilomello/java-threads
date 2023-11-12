package br.com.alura.threads.basico.banheiro;

public class PrincipalBanheiro {

    public static void main(String[] args) {
        Banheiro banheiro = new Banheiro();
        Thread convidado1 = new Thread(new TarefaNumero1(banheiro), "João");
        Thread convidado2 = new Thread(new TarefaNumero2(banheiro), "Michel");
        Thread limpeza = new Thread(new TarefaLimpeza(banheiro), "Limpeza");


        // Na classe Thread existe um método setPriority com propósito de determinar a
        // prioridade de uma Thread quando outras também competem pelo mesmo recuerso.
        // A prioridade é um valor inteiro entre 1 e 10, sendo 10 a prioridade mais alta
        limpeza.setPriority(9);
        limpeza.setDaemon(true);
//        Thread convidado3 = new Thread(new TarefaNumero1(banheiro), "Maria");
//        Thread convidado4 = new Thread(new TarefaNumero2(banheiro), "Ana");

        convidado1.start();
        convidado2.start();
        limpeza.start();
//        convidado3.start();
//        convidado4.start();
    }
}
