package com.someproject.linkendinshowcase.elevator.utils;

/**
 * This class represents the elevator system utilities
 */
public final class ElevatorSystemUtils {

  private ElevatorSystemUtils() {
    // Do nothing
  }

  /**
   * Checks whether the provided string is a strict numeric value. This means the string can be parsed
   * to a {@code double} and is not {@code NaN} or {@code Infinity}.
   *
   * @param str the string to check
   * @return {@code true} if the string represents a finite number, {@code false} otherwise
   */
  public static boolean isStrictNumeric(String str) {
    try {
      double d = Double.parseDouble(str);
      return !Double.isNaN(d) && !Double.isInfinite(d);
    } catch (NumberFormatException e) {
      return false;
    }
  }
}
