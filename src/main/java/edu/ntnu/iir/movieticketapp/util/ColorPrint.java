package edu.ntnu.iir.movieticketapp.util;

public final class ColorPrint {
  private ColorPrint() {}

  public static void info(String format, Object... args) {
    print(Ansi.YELLOW, format, args);
  }

  public static void success(String format, Object... args) {
    print(Ansi.GREEN, format, args);
  }

  public static void error(String format, Object... args) {
    print(Ansi.RED, format, args);
  }

  public static void header(String format, Object... args) {
    print(Ansi.CYAN, format, args);
  }

  private static void print(String color, String format, Object... args) {
    System.out.print(color);
    System.out.printf(format, args);
    System.out.print(Ansi.RESET);
  }
}
