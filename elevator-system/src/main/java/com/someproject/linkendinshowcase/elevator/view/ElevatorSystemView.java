package com.someproject.linkendinshowcase.elevator.view;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import com.someproject.linkendinshowcase.elevator.controller.ElevatorSystemController;
import com.someproject.linkendinshowcase.elevator.model.ElevatorSystemModel;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.StringProperty;
import javafx.concurrent.Task;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * This class represents the elevator system view
 */
public class ElevatorSystemView extends Application {

  private static final String TITLE = "ELEVATOR SYSTEM";
  private static final FontAwesomeIconView ELEVATOR_ICON = new FontAwesomeIconView(FontAwesomeIcon.CALENDAR, "20");
  private static final double WINDOW_WIDTH = 1000;
  private static final double WINDOW_HEIGHT = 800;

  private final ExecutorService executorService = Executors.newSingleThreadExecutor();

  private Stage primaryStage;

  private final ElevatorSystemController controller;
  private final ElevatorSystemModel model;

  /**
   * Useful properties
   */
  private StringProperty infoAreaProp;
  private StringProperty consoleAreaProp;

  /**
   * The class constructor
   * 
   */
  public ElevatorSystemView() {
    this.controller = new ElevatorSystemController();
    this.model = new ElevatorSystemModel();
  }

  private VBox createLeftPane() {
    var leftPane = new VBox();
    leftPane.setPadding(new Insets(10));
    leftPane.setAlignment(Pos.TOP_LEFT);
    leftPane.setSpacing(10);

    var infoArea = new TextArea();
    infoArea.setEditable(false);
    infoArea.setWrapText(true);
    infoAreaProp = infoArea.textProperty();

    var consoleArea = new TextArea();
    consoleArea.setEditable(false);
    consoleArea.setWrapText(true);
    consoleAreaProp = consoleArea.textProperty();
    VBox.setVgrow(consoleArea, Priority.ALWAYS);

    leftPane.getChildren().addAll(infoArea, consoleArea);

    return leftPane;
  }

  private HBox createTopPane() {
    var topPane = new HBox();
    topPane.setPadding(new Insets(10));
    topPane.setAlignment(Pos.CENTER_LEFT);
    topPane.setSpacing(10);

    var activeElevatorLabel = new Label("Active elevator (separate with \";\"): ");
    var activeElevatorTextField = new TextField();
    activeElevatorTextField.setPrefWidth(240);
    var activeFloorLabel = new Label("Active floor (separate with \";\"): ");
    var activeFloorTextField = new TextField();
    activeFloorTextField.setPrefWidth(240);

    var submitBtn = new Button("Run");
    submitBtn.setPadding(new Insets(5, 20, 5, 20));
    submitBtn.setGraphic(new FontAwesomeIconView(FontAwesomeIcon.SEND));

    submitBtn.setOnAction(ev -> {
      String activeElevatorText = activeElevatorTextField.getText();
      String activeFloorText = activeFloorTextField.getText();
      activeElevatorTextField.clear();
      activeFloorTextField.clear();

      executeAction(activeElevatorText, activeFloorText);

      ev.consume();
    });

    topPane.getChildren().addAll(activeElevatorLabel, activeElevatorTextField, activeFloorLabel, activeFloorTextField, submitBtn);

    return topPane;
  }

  private void executeAction(String activeElevatorText, String activeFloorText) {
    Task<String> task = new Task<>() {
      @Override
      protected String call() throws Exception {
        if (null != activeElevatorText) {
          Arrays.stream(activeElevatorText.split(";")).collect(Collectors.toList());
        }
        return "Result from background task!";
      }
    };

    executorService.submit(task);
  }

  private GridPane createCenterPane() {
    var centerPane = new GridPane();
    centerPane.setPrefSize(Double.MAX_VALUE, Double.MAX_VALUE);
    centerPane.setHgap(10);
    centerPane.setVgap(10);

    /*
     * 9 elevators from A to I and 10 floors from 0 to 9
     */
    int max_size = 10;
    // Space size
    for (int row = 0; row < max_size; row++) {
      var rowCst = new RowConstraints();
      rowCst.setPercentHeight(9.0);
      rowCst.setValignment(VPos.CENTER);
      centerPane.getRowConstraints().add(rowCst);

      var colCst = new ColumnConstraints();
      colCst.setPercentWidth(9.0);
      colCst.setHalignment(HPos.CENTER);
      centerPane.getColumnConstraints().add(colCst);
    }
    // Row
    for (int row = 0; row < max_size; row++) {
      centerPane.add(new Label(String.valueOf(9 - row)), 0, row);
    }
    // Column
    char c = 'A';
    for (int col = 0; col < max_size; col++) {
      centerPane.add(new Label(String.valueOf(c)), col + 1, max_size);
      c++;
    }

    centerPane.add(ELEVATOR_ICON, 1, 9);

    return centerPane;
  }

  private void initializeStage() {
    primaryStage.setTitle(TITLE);

    var root = new BorderPane();
    BorderPane.setMargin(root, new Insets(12));
    root.setTop(createTopPane());
    root.setLeft(createLeftPane());
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

  @Override
  public void stop() {
    Platform.exit();
    executorService.shutdown(); // Properly shutdown the executor
  }

}
