package edu.ntnu.iir.movieticketapp.booking;

public class MovieTicketServerUnsynced implements IMovieTicketServer {
  private final String movieName;
  private int availableTickets;

  public MovieTicketServerUnsynced(String movieName, int totalTickets) {
    this.movieName = movieName;
    this.availableTickets = totalTickets;
  }

  @Override
  public boolean bookTickets(String customerName, int numberOfTickets) {
    System.out.printf("%s is trying to book %d tickets. Available: %d%n",
        customerName, numberOfTickets, availableTickets);

    if (availableTickets >= numberOfTickets) {
      try {
        Thread.sleep(10); // Simulate delay to expose race conditions
      } catch (InterruptedException ignored) {
        // Handle interruption
        Thread.currentThread().interrupt();
      }

      availableTickets -= numberOfTickets;
      System.out.printf("%s successfully booked %d tickets. Remaining: %d%n",
          customerName, numberOfTickets, availableTickets);
      return true;
    } else {
      System.out.printf("%s failed to book %d tickets. Not enough available.%n",
          customerName, numberOfTickets);
      return false;
    }
  }

  @Override
  public int getAvailableTickets() {
    return availableTickets;
  }
}
