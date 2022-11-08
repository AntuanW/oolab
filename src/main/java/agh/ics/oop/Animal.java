package agh.ics.oop;

public class Animal {
    private MapDirection currentDirection = MapDirection.NORTH;
    private Vector2d place = new Vector2d(2, 2);

    @Override
    public String toString(){
        return  "(" + place.x + "," + place.y + ") " + currentDirection;
    }

    boolean isAt(Vector2d position){
        return place.x == position.x && place.y == position.y;
    }

    void move(MoveDirection direction){

        Vector2d howToMove = new Vector2d(0, 0);

        Vector2d leftCorner = new Vector2d(0, 0);
        Vector2d rightCorner = new Vector2d(4, 4);

        switch (direction){
            case LEFT -> {
                currentDirection = currentDirection.previous();
            }
            case RIGHT -> {
                currentDirection = currentDirection.next();
            }
            case FORWARD -> {
                howToMove = currentDirection.toUnitVector();
            }

            case BACKWARD -> {
                howToMove = currentDirection.toUnitVector().opposite();
            }
        }

        if(place.add(howToMove).follows(leftCorner) && place.add(howToMove).precedes(rightCorner)){
            place = place.add(howToMove);
        }
    }



}
