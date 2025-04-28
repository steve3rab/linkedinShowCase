package com.someproject.linkendinshowcase.elevator.model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import com.someproject.linkendinshowcase.elevator.utils.ElevatorSystemUtils;

/**
 * This class represents the elevator system model
 */
public class ElevatorSystemModel {

  private static final String NEWLINE = System.getProperty("line.separator");
  private Set<String> elevators = new HashSet<>();
  private Set<Integer> floors = new HashSet<>();

  public void setElevators(String activeElevatorText) {
    elevators = Arrays.stream(activeElevatorText.split(";")).collect(Collectors.toUnmodifiableSet());
  }

  public Set<String> getElevators() {
    return elevators;
  }

  public void setFloors(String activeFloorText) {
    floors = Arrays.stream(activeFloorText.split(";")).filter(ElevatorSystemUtils::isStrictNumeric).map(Integer::valueOf).filter(x -> x < 10 & x >= 0).collect(Collectors.toUnmodifiableSet());
  }

  public Set<Integer> getFloors() {
    return floors;
  }

  @Override
  public String toString() {
    return "Active elevator:" + NEWLINE + elevators + NEWLINE + "Active floor:" + NEWLINE + floors;
  }
}
