package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

import java.awt.*;
import java.util.List;

public class App extends Application {
    @Override
    public void start(Stage primaryStage){
        List<String> args = getParameters().getRaw();
        MoveDirection[] directions = new OptionsParser().parse(args.toArray(new String[0]));
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4), new Vector2d(11, 11)};
        IWorldMap map = new GrassField(10, positions);
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();

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


        for (int i = 0; i < maxX; i++){
            ColumnConstraints columnConstraints = new ColumnConstraints(50);
            columnConstraints.setPercentWidth(100.0 / maxX);
            gridPane.getColumnConstraints().add(columnConstraints);
        }

        for (int i = 0; i < maxY; i++){
            RowConstraints rowConstraints = new RowConstraints(50);
            rowConstraints.setPercentHeight(100.0 / maxY);
            gridPane.getRowConstraints().add(rowConstraints);
        }

        for (int i=0; i < maxY; i++){
            for (int j =0; j < maxX; j++){

                int x1 = i;
                String text = "";
                if(i == 0 && j == 0) text = "y/x";
                else if (i == 0) text = String.valueOf(j-1);
                else if (j == 0) text = String.valueOf(maxY - i - 1);
                else{
                    if (map.objectAt(new Vector2d(j-1, i-1)) != null){
                        text = map.objectAt(new Vector2d(j-1, i-1)).toString();
                    }
                    x1 = maxY-i;
                }
                Label newLabel = new Label(text);
                GridPane.setConstraints(newLabel, j, x1);
                GridPane.setHalignment(newLabel, HPos.CENTER);
                gridPane.add(newLabel, j, x1);
            }

        }

        Scene scene = new Scene(gridPane, 50*maxX, 50*maxY);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
