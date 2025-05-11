package edu.ntnu.iir.movieticketapp.booking;

public class MovieTicketClient extends Thread {
  private final IMovieTicketServer server;
  private final String customerName;
  private final int ticketsToBook;

  public MovieTicketClient(IMovieTicketServer server, String customerName, int ticketsToBook) {
    this.server = server;
    this.customerName = customerName;
    this.ticketsToBook = ticketsToBook;
  }

  @Override
  public void run() {
    server.bookTickets(customerName, ticketsToBook);
  }
}
