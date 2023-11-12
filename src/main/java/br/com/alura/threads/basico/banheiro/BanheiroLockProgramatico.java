package br.com.alura.threads.basico.banheiro;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 vimos o poder do bloco synchronized em como ele nos ajuda a trabalhar com tarefas
 atômicas. Como alternativa, podemos também conseguir esse bloqueio de forma
 explícita (programaticamente) através de uma classe chamada ReentrantLock
 */
public class BanheiroLockProgramatico {

    private Lock lock = new ReentrantLock();

    public void fazNumero1() {

        lock.lock();
        System.out.println("entrando no banheiro");
        System.out.println("fazendo coisa rapida");

        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("dando descarga");
        System.out.println("lavando a mao");
        System.out.println("saindo do banheiro");
        lock.unlock();
    }

    public void fazNumero2() {

        lock.lock();
        System.out.println("entrando no banheiro");
        System.out.println("fazendo coisa demorada");

        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("dando descarga");
        System.out.println("lavando a mao");
        System.out.println("saindo do banheiro");
        lock.unlock();
    }
}
