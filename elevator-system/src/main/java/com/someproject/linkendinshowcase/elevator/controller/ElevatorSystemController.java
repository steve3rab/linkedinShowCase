package com.someproject.linkendinshowcase.elevator.controller;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import com.someproject.linkendinshowcase.elevator.model.ElevatorSystemModel;

/**
 * This class represents the elevator system controller
 */
public class ElevatorSystemController {

  private final ElevatorSystemModel model;
  private static final SecureRandom secureRandom = new SecureRandom();

  /**
   * Constructor
   * 
   * @param model {@link ElevatorSystemModel}
   */
  public ElevatorSystemController(ElevatorSystemModel model) {
    this.model = model;
  }

  /**
   * Returns a securely random element from the given list
   * 
   * @param loopNum the loop number
   * @return a list of randomly chosen element
   */
  public List<Integer> goToNextFloor(int loopNum) {
    var floors = model.getFloors();
    if (floors.isEmpty()) {
      return floors;
    }
    List<Integer> nextFloors = new ArrayList<>();
    for (int i = 0; i <= loopNum; i++) {
      int idx = secureRandom.nextInt(floors.size());
      nextFloors.add(floors.get(idx));
    }
    return nextFloors;
  }

}
