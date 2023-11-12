package br.com.alura.threads.basico.buscatextual;

public class PrincipalBusca {

    public static void main(String[] args) {
        String nome = "Marco";

        Thread threadAssinaturas1 = new Thread(new TarefaBuscaTextual("assinaturas1.txt", nome));
        Thread threadAssinaturas2 = new Thread(new TarefaBuscaTextual("assinaturas2.txt", nome));
        Thread threadAutores = new Thread(new TarefaBuscaTextual("autores.txt", nome));

        threadAssinaturas1.start();
        threadAssinaturas2.start();
        threadAutores.start();

        System.out.println("id da thread 1: " + threadAssinaturas1.getId());
        System.out.println("id da thread 2: " + threadAssinaturas2.getId());
        System.out.println("id da thread autores: " + threadAutores.getId());
    }
}
