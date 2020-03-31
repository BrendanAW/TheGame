package org.example;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.util.*;

import static org.example.Dialogue.CREATION_TITLE;
import static org.example.Dialogue.FIGHTERS;

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
    final String SQUARE = "square";
    final String RECTANGLE = "rectangle";
    int turn = 0;
    int rows, cols;
    Slider fightersAmount;
    List<String> moveButts = Arrays.asList("Up", "Right", "Down", "Left");
    List<String> actButts = Arrays.asList("Fight", "Nothing", "Sepuku", "Special");

    public void initialize() {
        hideAllButtons();

    }

    @FXML
    void open() {
        intro();
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
        if (result.isPresent() && result.get() == ButtonType.OK) {
            buildDataMap(controller.getW(),controller.getH());
        }
    }

    void createFighters() {
        textToPlayer.setText(FIGHTERS);
        fightersAmount = new Slider();
        fightersAmount.setMin(2.0);
        fightersAmount.setMax(4.0);
        gameController.add(fightersAmount, 3, 0);
        togglePlayerInput();
        textToComputer.setVisible(false);
    }

    void toggleControllerButtons(List<String> tempButts) {
        gameController.getChildren().forEach(b -> {
            if (b instanceof Button) {
                tempButts.forEach(s -> {
                    if (((Button) b).getText().equalsIgnoreCase(s)) {
                        b.setVisible(!b.visibleProperty().get());
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
                tempCircle.setCenterX(100);
                tempCircle.setCenterY(100);
                tempCircle.setRadius(50);
                tempCircle.getStyleClass().add("circle");
                gridMap.add(tempCircle, y, x);
            }
        });
    }

    void hideAllButtons() {
        gameController.getChildren().forEach(node -> node.setVisible(false));
    }

    void togglePlayerInput() {
        boolean visible = !textToComputer.isVisible();
        textToComputer.setVisible(visible);
        submit.setVisible(visible);
    }

    public void clearTextToComputer() {
        textToComputer.setText("");
    }

    @FXML
    void sendToComputer() {
        String s = textToComputer.getText();
        if (s.equalsIgnoreCase(SQUARE) || s.equalsIgnoreCase(RECTANGLE)) {
            togglePlayerInput();
            switch (turn) {
                case 0:
                    turn++;
                    createFighters();
                    break;
                default:
                    break;
            }
        } else {
            String temp = textToPlayer.getText();
            textToPlayer.setText("");
            textToPlayer.setText(temp + "\nPlease enter a valid response");
        }
    }
}
