package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

import java.util.List;

public class App extends Application {

    private final GridPane grid = new GridPane();
    private IWorldMap map;

    private Stage primaryStage = new Stage();
    @Override
    public void start(Stage primaryStage){

        try{
            TextField textField = new TextField();
            Button startButton = getStartButton(textField);
            HBox hBox = new HBox(grid, textField, startButton);
            Scene scene = new Scene(hBox, 700, 700);
            primaryStage.setScene(scene);
            primaryStage.show();
        }catch (IllegalArgumentException ex){
            System.out.println(ex.getMessage());
        }
    }

    public Button getStartButton(TextField textField){
        Button startButton = new Button("Start");

        startButton.setOnAction((action) -> {
            String text = textField.getText();
            MoveDirection[] directions = new OptionsParser().parse(text.split(" "));
            Vector2d[] positions = {new Vector2d(2, 2), new Vector2d(3, 4), new Vector2d(11, 11)};
            IWorldMap map = new GrassField(10, positions);
            IEngine engine = new SimulationEngine(directions, map, positions, this);
            Thread engineThread = new Thread(engine::run);
            engineThread.start();
        });

        return startButton;
    }

    public void drawMap(IWorldMap map){
        //Stage primaryStage = new Stage();
        primaryStage.setTitle("Mapa zwierząt: ");

        GridPane gridPane = new GridPane();
        gridPane.setGridLinesVisible(true);
        gridPane.setPadding(new Insets(5, 5, 5, 5));

        Vector2d maxVector = map.getRightCorner();
        Vector2d minVector = map.getLeftCorner();
        int maxX = maxVector.getX() + 2;
        int maxY = maxVector.getY() + 2;
        int minX = minVector.getX();
        int minY = minVector.getY();


        for (int i = minX; i < maxX; i++) {
            ColumnConstraints columnConstraints = new ColumnConstraints(100);
            columnConstraints.setPercentWidth(100.0 / Math.abs(maxX - minX));
            gridPane.getColumnConstraints().add(columnConstraints);
        }

        for (int i = minY; i < maxY; i++) {
            RowConstraints rowConstraints = new RowConstraints(100);
            rowConstraints.setPercentHeight(100.0 / Math.abs(maxY - minY));
            gridPane.getRowConstraints().add(rowConstraints);
        }

        for (int i = 0; i < Math.abs(maxY - minY); i++) {
            for (int j = 0; j < Math.abs(maxX - minX); j++) {

                String text = "";
                boolean filled = false;
                if (i == 0 && j == 0) text = "y/x";
                else if (i == 0) text = String.valueOf(minX + j - 1);
                else if (j == 0) text = String.valueOf(maxY - i - 1);
                else {
                    if (map.objectAt(new Vector2d(minX + j - 1, maxY - i - 1)) != null) {
                        filled = true;
                        GuiElementBox obj = new GuiElementBox((IMapElement) map.objectAt(new Vector2d(minX + j - 1, maxY - i - 1)));
                        Label label = new Label();
                        GridPane.setHalignment(label, HPos.CENTER);
                        GridPane.setConstraints(label, j, i);
                        gridPane.add(obj.vBox, j, i);
                    }
                }
                if (!filled) {
                    Label newLabel = new Label(text);
                    GridPane.setConstraints(newLabel, j, i);
                    GridPane.setHalignment(newLabel, HPos.CENTER);
                    gridPane.add(newLabel, j, i);
                }
            }

        }

        Scene scene = new Scene(gridPane, 50 * maxX, 50 * maxY);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void renderMap(IWorldMap map){
        grid.setGridLinesVisible(false);
        grid.getColumnConstraints().clear();
        grid.getRowConstraints().clear();
        grid.getChildren().clear();
        grid.setGridLinesVisible(true);
        drawMap(map);
    }
}
