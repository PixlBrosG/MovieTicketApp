package edu.ntnu.iir.movieticketapp;

import edu.ntnu.iir.movieticketapp.booking.*;

public class Main {
  public static void main(String[] args) {
    System.out.println("===== Synchronized Only =====");
    runTest(new MovieTicketServerSyncOnly("Troll", 10));

    System.out.println("\n===== Volatile Only =====");
    runTest(new MovieTicketServerVolatileOnly("Troll", 10));

    System.out.println("\n===== Synchronized + Volatile =====");
    runTest(new MovieTicketServerSyncAndVolatile("Troll", 10));

    System.out.println("\n===== Unsynced and Non-Volatile =====");
    runTest(new MovieTicketServerUnsynced("Troll", 10));
  }

  public static void runTest(IMovieTicketServer server) {
    Thread t1 = new MovieTicketClient(server, "Xiangming", 3);
    Thread t2 = new MovieTicketClient(server, "Ilaria", 2);
    Thread t3 = new MovieTicketClient(server, "Sam", 3);
    Thread t4 = new MovieTicketClient(server, "Andreas", 4);

    t1.start(); t2.start(); t3.start(); t4.start();

    try {
      t1.join(); t2.join(); t3.join(); t4.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    System.out.printf("Final available tickets: %d%n", server.getAvailableTickets());
  }
}
