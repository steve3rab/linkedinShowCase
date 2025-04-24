package com.someproject.linkendinshowcase.elevator;

/**
 * This class represents the elevator
 */
public class Elevator {

  private int posX = 1;
  private int posY = 1;

  public Elevator() {
    // TODO Could set the initial position of the elevator
  }

  public int getPosX() {
    return posX;
  }

  public int getPosY() {
    return posY;
  }

  public void setPosY(int posY) {
    this.posY = posY;
  }
}
