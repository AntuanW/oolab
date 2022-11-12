package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class RectangularMap implements IWorldMap{

    private final int width;
    private final int height;
    private final Vector2d lowerLeftCorner;
    private final Vector2d upperRightCorner;
    private final List<Animal> animals;
    private final MapVisualizer visualizer;

    public RectangularMap(int width, int height){
        this.width = width;
        this.height = height;
        this.lowerLeftCorner = new Vector2d(0, 0);
        this.upperRightCorner = new Vector2d(this.width - 1, this.height - 1);
        this.animals = new ArrayList<>();
        this.visualizer = new MapVisualizer(this);
    }
    @Override
    public boolean canMoveTo(Vector2d position) {
        return position.follows(lowerLeftCorner) && position.precedes(upperRightCorner) && !isOccupied(position);
    }

    @Override
    public boolean place(Animal animal) {
        if(!isOccupied(animal.getPlace())){
            animals.add(animal);
            return true;
        }
        return false;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        for(Animal animal : animals){
            if(animal.getPlace().equals(position)){
                return true;
            }
        }
        return false;
    }

    @Override
    public Object objectAt(Vector2d position) {
        for(Animal animal : animals){
            if(animal.getPlace().equals(position)){
                return animal;
            }
        }
        return null;
    }

    public String toString(){
        return visualizer.draw(lowerLeftCorner, upperRightCorner);
    }
}