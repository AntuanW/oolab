package agh.ics.oop;

import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

public class Animal {
    private MapDirection currentDirection;
    private Vector2d place;
    private IWorldMap map;
    private final List<IPositionChangeObserver> observers = new LinkedList<>();

    public Animal( IWorldMap map, Vector2d initialPosition){
        this.map = map;
        this.place = initialPosition;
        this.currentDirection = MapDirection.NORTH;
    }

    public Animal(IWorldMap map){
        this(map, new Vector2d(2, 2));
    }

    public Animal(){
        this(new RectangularMap(5, 5));
    }

    public MapDirection getCurrentDirection(){
        return currentDirection;
    }

    public Vector2d getPlace(){
        return place;
    }

    @Override
    public String toString(){
        return  switch (currentDirection){
            case NORTH -> "^";
            case EAST -> ">";
            case SOUTH -> "v";
            case WEST -> "<";
            case NONE -> " ";
        };
    }

    boolean isAt(Vector2d position){
        return place.x == position.x && place.y == position.y;
    }

    void move(MoveDirection direction){

        Vector2d newPlace = place;

        switch (direction){
            case LEFT -> {
                currentDirection = currentDirection.previous();
            }
            case RIGHT -> {
                currentDirection = currentDirection.next();
            }
            case FORWARD -> {
                newPlace = place.add(currentDirection.toUnitVector());
            }

            case BACKWARD -> {
                newPlace = place.subtract(currentDirection.toUnitVector());
            }
        }

        if(map.canMoveTo(newPlace)){
            positionChanged(this.place, newPlace);
            place = newPlace;
        }
    }

    public void addObserver(IPositionChangeObserver observer){
        observers.add(observer);
    }

    public void removeObserver(IPositionChangeObserver observer){
        observers.remove(observer);
    }

    private void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        for (IPositionChangeObserver observer : this.observers){
            observer.positionChanged(oldPosition, newPosition);
        }
    }

}
