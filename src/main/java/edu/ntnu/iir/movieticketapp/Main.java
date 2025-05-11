package edu.ntnu.iir.movieticketapp;

import edu.ntnu.iir.movieticketapp.booking.*;
import edu.ntnu.iir.movieticketapp.util.ColorPrint;

public class Main {
  public static void main(String[] args) {
    runTest(new MovieTicketServerSyncOnly("Troll", 10));
    runTest(new MovieTicketServerVolatileOnly("Troll", 10));
    runTest(new MovieTicketServerSyncAndVolatile("Troll", 10));
    runTest(new MovieTicketServerUnsynced("Troll", 10));
  }

  public static void runTest(IMovieTicketServer server) {
    ColorPrint.info("%n===== Running Test: %s =====%n", server.getClass().getSimpleName());

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

    ColorPrint.info("Final available tickets: %d%n", server.getAvailableTickets());
  }
}
