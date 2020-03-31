package org.example;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.List;
//import static org.example.Dialogue.*;

public class CreationMenu {
    @FXML
    private TextField playerTypeName;
    @FXML
    private BorderPane borderPane;
    @FXML
    private VBox mapCreationBox;
    @FXML
    private ChoiceBox<String> fighterChoiceBox;
    @FXML
    private ChoiceBox<String> choiceBox;
    @FXML
    private Spinner<Integer> col;
    @FXML
    private Spinner<Integer> row;
    @FXML
    private Label width;
    @FXML
    private Button continueButton;
    @FXML
    private Label fightersText;
    @FXML
    private Slider fighterQty;
    @FXML
    private Label chooseSize;
    @FXML
    private Label chooseLength;
    @FXML
    private VBox fighterCreationBox;
    @FXML
    private Button nextButton;
    @FXML
    private Button previousButton;
    @FXML
    private Label setFighterName;
    @FXML
    private Button finishButton;
    private int h;
    private int w;
    private BooleanBinding inputFilled;
    private List<String> fightersNames = Arrays.asList(null, null, null, null);
    private int fighterNum = 0;
    String oldName = null;

    public void initialize() {
        inputFilled = new BooleanBinding() {
            {
                bind(continueButton.visibleProperty());
            }

            @Override
            protected boolean computeValue() {
                return (continueButton.visibleProperty().get());
            }
        };
        nextButton.disableProperty().bind(Bindings.createBooleanBinding(() -> fighterChoiceBox.getSelectionModel().isEmpty() || playerTypeName.getText().isEmpty()
                , fighterChoiceBox.selectionModelProperty(), playerTypeName.textProperty()));

        fighterQty.setMax(4);
        fighterQty.setMin(2);
        nextButton.visibleProperty().bind(Bindings.createBooleanBinding(() -> fighterCreationBox.isVisible() && !finishButton.isVisible(),
                fighterCreationBox.visibleProperty(), finishButton.visibleProperty()));
        previousButton.visibleProperty().bind(fighterCreationBox.visibleProperty());
    }

    @FXML
    void setMapSize() {
        if (choiceBox.getSelectionModel().getSelectedItem().equalsIgnoreCase("rectangle")) {
            col.setVisible(true);
            width.setVisible(true);
        } else {
            col.setVisible(false);
            width.setVisible(false);
        }
        continueButton.setDisable(false);
    }

    @FXML
    private void saveInfo() {
        String tempShape = choiceBox.getSelectionModel().getSelectedItem();

        h = row.getValue();
        if (tempShape.equalsIgnoreCase("rectangle")) {
            w = col.getValue();
        } else w = h;
        continueButton.setVisible(false);
        revealNextSection();
    }

    private void revealNextSection() {
        mapCreationBox.setVisible(false);
        fighterCreationBox.setVisible(true);
    }

    @FXML
    private void nextFighter() {
        if (nextButton.disableProperty().get())
            return;

        String name, type;
        name = playerTypeName.getText();
        fightersNames.set(fighterNum, name);

        type = fighterChoiceBox.getSelectionModel().getSelectedItem();
        fighterChoiceBox.getSelectionModel().clearSelection();

        if (oldName == null)
            FighterController.getInstance().addFighter(type, name);
        else FighterController.getInstance().editFighter(oldName, name, type);
        oldName = null;

        previousButton.setDisable(false);

        if (fighterQty.getValue() - 1 == fighterNum) {
            finishButton.setVisible(true);
            switchVisibilities();
            return;
        }

        fighterNum++;
        name = fightersNames.get(fighterNum);
        if (name != null) {
            playerTypeName.setText(name);
            fighterChoiceBox.getSelectionModel().select(FighterController.getInstance().getFighterType(name));
        } else playerTypeName.clear();
        setFighterNameText();

    }

    @FXML
    private void previousFighter() {
        fighterNum--;

        String name = fightersNames.get(fighterNum);
        setFighterNameText();
        playerTypeName.setText(name);
        fighterChoiceBox.getSelectionModel().select(FighterController.getInstance().getFighterType(name));
        if (fighterNum == 0) {
            previousButton.setDisable(true);
        }

        oldName = name;
        if (finishButton.isVisible())
            switchVisibilities();
        finishButton.setVisible(false);
    }

    private void setFighterNameText() {
        StringBuilder temp = new StringBuilder();
        temp.append("Set name of player number ");
        String name = fightersNames.get(fighterNum);

        switch (fighterNum) {
            case 0:
                temp.append("one, ");
                break;
            case 1:
                temp.append("two, ");
                break;
            case 2:
                temp.append("three, ");
                break;
            case 3:
                temp.append("four, ");
                break;
            default:
                return;
        }
        if (name != null)
            temp.append(name);
        setFighterName.setText(temp.toString());

    }

    int getH() {
        return h;
    }

    int getW() {
        return w;
    }

    BooleanBinding inputsFilled() {
        return inputFilled;
    }

    void switchVisibilities() {
        fighterCreationBox.setVisible(!fighterCreationBox.isVisible());
    }

    @FXML
    void closeOut() {
        Stage stage = (Stage) finishButton.getScene().getWindow();
        stage.close();
    }
}
