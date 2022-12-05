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
    protected static MapBoundary mapBoundary = new MapBoundary();

    abstract public Vector2d getlowerLeft();
    abstract public Vector2d getupperRight();

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
    public boolean place(Animal animal){
        if (canMoveTo(animal.getPlace())) {
            animals.put(animal.getPlace(), animal);
            mapBoundary.addPosition(animal.getPlace());
            animal.addObserver(this);
            return true;
        } else {
            throw new IllegalArgumentException(animal.getPlace() + " is not legal move");
        }
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        Animal animal = this.animals.get(oldPosition);
        this.animals.remove(oldPosition);
        mapBoundary.removePosition(oldPosition);
        this.animals.put(newPosition, animal);
        mapBoundary.addPosition(newPosition);
    }

    @Override
    public String toString(){
        return visualizer.draw(getlowerLeft(), getupperRight());
    }

    @Override
    public Vector2d getRightCorner(){
        return mapBoundary.getUpperRight();
    }

    @Override
    public Vector2d getLeftCorner(){
        return mapBoundary.getLowerLeft();
    }
}
