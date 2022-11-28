package agh.ics.oop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver{

    protected static Map<Vector2d, Animal> animals = new HashMap<>();
    protected static Map<Vector2d, Grass> grasses = new HashMap<>();
    protected final MapVisualizer visualizer = new MapVisualizer(this);

    public static Map<Vector2d, Animal> getAnimals(){
        return animals;
    }

    public static Map<Vector2d, Grass> getGrasses(){
        return grasses;
    }

    abstract public Vector2d lowerLeft();
    abstract public Vector2d upperRight();

    @Override
    public Object objectAt(Vector2d position) {

        if (animals.containsKey(position)){
            return animals.get(position);
        }
        if(grasses.containsKey(position)){
            return grasses.get(position);
        }
        return null;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        if (animals.containsKey(position)){
            return true;
        }

        if(grasses.containsKey(position)){
            return true;
        }

        return false;
    }

    @Override
    public boolean place(Animal animal) {
        if (canMoveTo(animal.getPlace())) {
            animals.put(animal.getPlace(), animal);
            animal.addObserver(this);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        Animal animal = this.animals.get(oldPosition);
        this.animals.remove(oldPosition);
        this.animals.put(newPosition, animal);
    }

    @Override
    public String toString(){
        return visualizer.draw(lowerLeft(), upperRight());
    }
}
