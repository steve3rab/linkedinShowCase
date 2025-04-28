package com.someproject.linkendinshowcase.elevator.controller;

import com.someproject.linkendinshowcase.elevator.model.ElevatorSystemModel;

/**
 * This class represents the elevator system controller
 */
public class ElevatorSystemController {

  private final ElevatorSystemModel model;

  /**
   * Constructor
   * 
   * @param model {@link ElevatorSystemModel}
   */
  public ElevatorSystemController(ElevatorSystemModel model) {
    this.model = model;
  }

}
