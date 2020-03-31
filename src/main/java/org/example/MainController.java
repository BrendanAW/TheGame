package org.example;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;

import static org.example.Dialogue.CREATION_TITLE;

public class MainController {
    @FXML
    private BorderPane mainPane;
    @FXML
    GridPane gridMap;
    @FXML
    GridPane gameController;
    @FXML
    TextArea textToPlayer;
    @FXML
    TextField textToComputer;
    @FXML
    Button submit;
    Map<String, String> theMap;
    int turn = 0;
    int greatestSize = 0;
    List<String> moveButts = Arrays.asList("Up", "Right", "Down", "Left");
    List<String> actButts = Arrays.asList("Fight", "Nothing", "Sepuku", "Special");

    public void initialize() {
        hideAllButtons();
    }

    @FXML
    void open() {
        intro();

        if (gridMap.getChildren().size() > 1) {

        }
    }

    void intro() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainPane.getScene().getWindow());
        dialog.setTitle(CREATION_TITLE);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("CreationMenu.fxml"));
        try {
            dialog.getDialogPane().setContent(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        CreationMenu controller = loader.getController();

        Optional<ButtonType> result = dialog.showAndWait();
        if (FighterController.getInstance().dataMap.size() > 0) {
            buildDataMap(controller.getW(), controller.getH());
        }
    }

    void toggleControllerButtons(List<String> tempButts) {
        gameController.getChildren().forEach(b -> {
            if (b instanceof Button) {
                tempButts.forEach(s -> {
                    if (((Button) b).getText().equalsIgnoreCase(s)) {
                        b.setDisable(!b.disableProperty().get());
                    }
                });
            }
        });
    }

    void toggleControllerButtons(List<String> oldButts, List<String> visibleButts) {
        List<String> allButts = new ArrayList<>(visibleButts);
        allButts.addAll(oldButts);
        toggleControllerButtons(allButts);
    }


    public void buildDataMap(int w, int h) {
        theMap = new HashMap<>();
        greatestSize = Math.max(w, h);
        for (int i = 0; i < h; i++)
            for (int j = 0; j < w; j++) {
                String temp = i + "," + j;
                theMap.put(temp, "X");
            }
        buildMap();
    }

    void buildMap() {
        theMap.forEach((s, s2) -> {
            String[] coordinates = s.split(",");
            int y = Integer.parseInt(coordinates[0]);
            int x = Integer.parseInt(coordinates[1]);
            if (s2.equalsIgnoreCase("x")) {
                Circle tempCircle = new Circle();
                tempCircle.setRadius(50);
                tempCircle.getStyleClass().add("circle");
                gridMap.add(tempCircle, y, x);
            }
        });
        changeSizes(1, 1);
    }

    void hideAllButtons() {
        gameController.getChildren().forEach(node -> node.setDisable(true));
    }

    void togglePlayerInput() {
        boolean visible = !textToComputer.isVisible();
        textToComputer.setVisible(visible);
        submit.setVisible(visible);
    }

    public void clearTextToComputer() {
        textToComputer.setText("");
    }

    void setStageListener(Stage stage) {
        stage.widthProperty().addListener((obs, number, t1) -> changeSizes((double) number, (double) t1));
        stage.heightProperty().addListener((obs, number, t1) -> System.out.println(number));


    }

    private void changeSizes(double newValue, double oldValue) {
        var scale = oldValue / newValue;
        var x = greatestSize;
        double temp = 0;
        var size = 90;
        for (int i = 5; i <= 10; i++) {
            if (x == i) {
                temp = size * scale;
                break;
            } else
                size -= 10;
        }
        if (temp > size)
            temp = size;
        var radius = temp;
        gridMap.getChildren().forEach(node -> {
            if (node instanceof Circle)
                ((Circle) node).setRadius(radius);
        });
    }
}
