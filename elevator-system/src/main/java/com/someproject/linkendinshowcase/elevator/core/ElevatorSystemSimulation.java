package com.someproject.linkendinshowcase.elevator.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.someproject.linkendinshowcase.elevator.view.ElevatorSystemView;
import javafx.application.Application;

/*
 * This is the main class of the elevator system simulation
 */
public class ElevatorSystemSimulation {

  private final static Logger LOGGER = LogManager.getLogger(ElevatorSystemSimulation.class);

  /**
   * The main method
   * 
   * @param args is the class arguments
   */
  public static void main(String[] args) {
    LOGGER.info("Launching: {}", ElevatorSystemSimulation.class);
    Application.launch(ElevatorSystemView.class, args);
  }

}
