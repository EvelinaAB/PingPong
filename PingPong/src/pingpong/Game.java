/*
 *Primer hilo es GAME, y le siguen dos hilos PLAYER1 y PLAYER2
 */
package pingpong;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author ewelina
 */
//public class Game {

/* public static void main(String[] args) {
        Lock lock = new ReentrantLock();

        Player player1 = new Player("ping", lock);
        Player player2 = new Player("pong", lock);

        player1.setNextPlayer(player2);
        player2.setNextPlayer(player1);

        System.out.println("Game starting...!");

        player1.setPlay(true);

        Thread thread2 = new Thread(player2);
        thread2.start();
        Thread thread1 = new Thread(player1);
        thread1.start();

        //Let the players play!
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Tell the players to stop
        thread1.interrupt();
        thread2.interrupt();

        //Wait until players finish
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Game finished!");
    }
}
 */
public class Game {

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();

        Player player1 = new Player("ping", lock);
        Player player2 = new Player("pong", lock);

        player1.setNextPlayer(player2);
        player2.setNextPlayer(player1);

        System.out.println("Game starting...!");

        player1.setPlay(true);

        ExecutorService executor = Executors.newFixedThreadPool(2);

        executor.execute(player1);
        executor.execute(player2);

        sleep(2000);

        executor.shutdownNow();

        try {
            executor.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted while waiting for players to finish");
        }

        System.out.println("Game finished!");
    }

    public static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
