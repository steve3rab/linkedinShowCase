package com.someproject.linkendinshowcase.elevator;

import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This class represents the elevator
 */
public class Elevator {

  private final static Logger LOGGER = LogManager.getLogger(Elevator.class);

  private String label;
  private int posX = 1;
  private int posY;

  private final AtomicInteger currentPosY = new AtomicInteger(posY);
  private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor(r -> {
    var t = new Thread(r);
    t.setDaemon(true); // Daemon thread: won't block app shutdown
    t.setName("MoveToTarget-Scheduler");
    return t;
  });

  public Elevator(String label, int posX) {
    this.label = label;
    this.posX = posX;
    this.posY = 1;
  }

  public int getPosX() {
    return posX;
  }

  public int getPosY() {
    return currentPosY.get();
  }

  private static void shutdownScheduler(ScheduledExecutorService scheduler) {
    try {
      scheduler.shutdownNow();
      if (!scheduler.awaitTermination(1, TimeUnit.SECONDS)) {
        LOGGER.error("Scheduler did not terminate properly.");
      }
    } catch (InterruptedException e) {
      LOGGER.error("Scheduler shutdown interrupted.");
      Thread.currentThread().interrupt(); // restore interrupt flag
    }
  }

  public void moveTo(int toYpos, int delayMillis) {
    Runnable[] task = new Runnable[1];
    task[0] = () -> {
      try {
        int value = currentPosY.get();
        LOGGER.debug("Current: {}", value);

        if (value == toYpos) {
          LOGGER.debug("Reached target: {}", toYpos);
          shutdownScheduler(scheduler);
        } else {
          if (value < toYpos) {
            currentPosY.incrementAndGet();
          } else {
            currentPosY.decrementAndGet();
          }

          scheduler.schedule(task[0], delayMillis, TimeUnit.MILLISECONDS);
        }
      } catch (Exception e) {
        LOGGER.error("Scheduler task error:", e.getMessage());
        shutdownScheduler(scheduler);
      }
    };

    try {
      scheduler.schedule(task[0], delayMillis, TimeUnit.MILLISECONDS);
    } catch (RejectedExecutionException e) {
      LOGGER.error("Scheduler rejected task: {}", e.getMessage());
      shutdownScheduler(scheduler);
    }
  }

  @Override
  public String toString() {
    return label + "{x=" + posX + ", y=" + getPosY() + "}";
  }

  @Override
  public boolean equals(Object obj) {
    return super.equals(obj);
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }
}
