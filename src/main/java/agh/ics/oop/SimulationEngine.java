package agh.ics.oop;

import agh.ics.oop.gui.App;
import javafx.application.Platform;

import java.util.ArrayList;
import java.util.List;

public class SimulationEngine implements IEngine{

    private final IWorldMap map;
    private final List<MoveDirection> directions;
    private final List<Animal> animals;
    private final App app;
    private final int moveDelay = 500;

    public SimulationEngine(MoveDirection[] directions, IWorldMap map, Vector2d[] positions, App app){
        this.map = map;
        this.directions = List.of(directions);
        this.animals = new ArrayList<>();
        this.app = app;
        for(Vector2d position : positions){
            Animal animal = new Animal(map, position);
            this.animals.add(animal);
            this.map.place(animal);
        }
    }

    public Animal getAnimal(int i){
        return this.animals.get(i);
    }

    @Override
    public void run() {
        Platform.runLater(() -> {this.app.renderMap(this.map);});

        try{
            Thread.sleep(this.moveDelay);
        }catch( InterruptedException ex){
            System.out.println(ex.getMessage());
        }

        for(int i = 0; i < directions.size(); i++){
            this.animals.get(i % this.animals.size()).move(this.directions.get(i));
            Platform.runLater(() -> {this.app.renderMap(this.map);});

            try{
                Thread.sleep(this.moveDelay);
            }catch( InterruptedException ex){
                System.out.println(ex.getMessage());
            }
        }
    }
}
