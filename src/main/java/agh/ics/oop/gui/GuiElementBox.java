package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GuiElementBox {
    Label label;
    VBox vBox = new VBox();

    public GuiElementBox(IMapElement element){
        try {
            Image image = new Image(new FileInputStream(element.getImagePath()));
            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(20);
            imageView.setFitWidth(20);

            if (element instanceof Animal){
                this.label = new Label(((Animal) element).getPlace().toString());
            }else {
                this.label = new Label("Grass");
            }

            this.vBox.getChildren().addAll(imageView, label);
            this.vBox.setAlignment(Pos.CENTER);

        }catch (FileNotFoundException ex){
            System.out.println("No such file exist");
        }
    }
}
