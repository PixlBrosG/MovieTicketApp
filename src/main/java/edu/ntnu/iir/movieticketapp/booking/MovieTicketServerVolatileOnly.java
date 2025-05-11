package edu.ntnu.iir.movieticketapp.booking;

import edu.ntnu.iir.movieticketapp.util.ColorPrint;

public class MovieTicketServerVolatileOnly implements IMovieTicketServer {
  private final String movieName;
  private volatile int availableTickets;

  public MovieTicketServerVolatileOnly(String movieName, int totalTickets) {
    this.movieName = movieName;
    this.availableTickets = totalTickets;
  }

  @Override
  public boolean bookTickets(String customerName, int numberOfTickets) {
    ColorPrint.info("%s is trying to book %d tickets. Available: %d%n",
        customerName, numberOfTickets, availableTickets);

    if (availableTickets >= numberOfTickets) {
      try {
        Thread.sleep(10); // Simulate delay to expose race conditions
      } catch (InterruptedException ignored) {
        // Handle interruption
        Thread.currentThread().interrupt();
      }

      availableTickets -= numberOfTickets;
      ColorPrint.success("%s successfully booked %d tickets. Remaining: %d%n",
          customerName, numberOfTickets, availableTickets);
      return true;
    } else {
      ColorPrint.error("%s failed to book %d tickets. Not enough available.%n",
          customerName, numberOfTickets);
      return false;
    }
  }

  @Override
  public int getAvailableTickets() {
    return availableTickets;
  }
}
