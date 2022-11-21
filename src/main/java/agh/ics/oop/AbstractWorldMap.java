package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

abstract class AbstractWorldMap implements IWorldMap{

    protected static List<Animal> animals = new ArrayList<>();
    protected static List<Grass> grasses = new ArrayList<>();
    protected final MapVisualizer visualizer = new MapVisualizer(this);

    public static List<Animal> getAnimals(){
        return animals;
    }

    public static List<Grass> getGrasses(){
        return grasses;
    }

    abstract public Vector2d lowerLeft();
    abstract public Vector2d upperRight();

    @Override
    public Object objectAt(Vector2d position) {
        for (Animal animal : animals) {
            if (animal.getPlace().equals(position)) {
                return animal;
            }
        }
        for (Grass grass : grasses) {
            if (grass.getPosition().equals(position)) {
                return grass;
            }
        }
        return null;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        for (Animal animal : animals) {
            if (animal.getPlace().equals(position)) {
                return true;
            }
        }
        for (Grass grass : grasses) {
            if (grass.getPosition().equals(position)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean place(Animal animal) {
        if (!isOccupied(animal.getPlace())) {
            animals.add(animal);
            return true;
        } else {
            Object object = objectAt(animal.getPlace());
            if (object instanceof Grass) {
                for (Grass grass : grasses) {
                    if (grass.getPosition().equals(animal.getPlace())) {
                        grasses.remove(grass);
                        break;
                    }
                }
                animals.add(animal);
            } else return false;
        }
        return false;
    }

    @Override
    public String toString(){
        return visualizer.draw(lowerLeft(), upperRight());
    }
}
