package com.someproject.linkendinshowcase.elevator.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import com.someproject.linkendinshowcase.elevator.utils.ElevatorSystemUtils;

/**
 * This class represents the elevator system model
 */
public class ElevatorSystemModel {

  private static final String NEWLINE = System.getProperty("line.separator");
  private List<String> elevators = new ArrayList<>();
  private List<Integer> floors = new ArrayList<>();

  public void setElevators(String activeElevatorText) {
    elevators = Arrays.stream(activeElevatorText.split(";")).collect(Collectors.toUnmodifiableList());
  }

  public void setFloors(String activeFloorText) {
    floors = Arrays.stream(activeFloorText.split(";")).filter(ElevatorSystemUtils::isStrictNumeric).map(Integer::valueOf).collect(Collectors.toUnmodifiableList());
  }

  public List<Integer> getFloors() {
    return floors;
  }

  @Override
  public String toString() {
    return "Active elevator:" + NEWLINE + elevators + NEWLINE + "Active floor:" + NEWLINE + floors;
  }
}
