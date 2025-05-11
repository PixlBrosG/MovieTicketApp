package edu.ntnu.iir.movieticketapp.util;

public final class Ansi {
  public static final boolean ENABLED = true;

  public static final String RESET = ENABLED ? "\u001B[0m" : "";
  public static final String RED = ENABLED ? "\u001B[31m" : "";
  public static final String GREEN = ENABLED ? "\u001B[32m" : "";
  public static final String YELLOW = ENABLED ? "\u001B[33m" : "";
  public static final String CYAN = ENABLED ? "\u001B[36m" : "";

  private Ansi() {
    throw new UnsupportedOperationException("Utility class");
  }
}
