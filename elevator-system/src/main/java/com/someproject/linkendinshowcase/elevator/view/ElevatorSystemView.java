package com.someproject.linkendinshowcase.elevator.view;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * This class corresponds to the elevator system view
 */
public class ElevatorSystemView extends Application {

  private static final String TITLE = "ELEVATOR SYSTEM";
  private static final double WINDOW_WIDTH = 1000;
  private static final double WINDOW_HEIGHT = 800;
  private Stage primaryStage;

  private HBox createTopPane() {
    var topPane = new HBox();
    topPane.setPadding(new Insets(10));
    topPane.setAlignment(Pos.CENTER_LEFT);
    topPane.setSpacing(10);

    var activeElevatorLabel = new Label("Active elevator (separate with semicolon): ");
    var activeElevatorTextField = new TextField();
    var activeFloorLabel = new Label("Active floor (separate with semicolon): ");
    var activeFloorTextField = new TextField();

    var submitBtn = new Button("Run");
    submitBtn.setGraphic(new FontAwesomeIconView(FontAwesomeIcon.SEND));

    topPane.getChildren().addAll(activeElevatorLabel, activeElevatorTextField, activeFloorLabel, activeFloorTextField, submitBtn);

    return topPane;
  }

  private GridPane createCenterPane() {
    var centerPane = new GridPane();
    centerPane.getColumnConstraints().add(new ColumnConstraints(80));

    /*
     * 10 elevators from A to J and 10 floors from 1 to 10
     */

    return centerPane;
  }

  private void initializeStage() {
    primaryStage.setTitle(TITLE);

    var root = new BorderPane();
    root.setTop(createTopPane());
    root.setCenter(createCenterPane());

    var scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
    primaryStage.setScene(scene);
    primaryStage.setResizable(false);

    primaryStage.setOnCloseRequest(event -> {
      event.consume();
      primaryStage.close();
    });

    primaryStage.show();
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    this.primaryStage = primaryStage;
    initializeStage();
  }

}
