package edu.ntnu.iir.movieticketapp.booking;

public interface IMovieTicketServer {
  boolean bookTickets(String customerName, int numberOfTickets);
  int getAvailableTickets();
}
