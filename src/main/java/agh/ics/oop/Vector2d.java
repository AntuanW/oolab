package agh.ics.oop;

public class Vector2d {
    final int x, y;

    public Vector2d(int a, int b){
        x = a;
        y = b;
    }

    @Override
    public String toString(){
        return "(" + x + "," + y +")";
    }

    public boolean precedes(Vector2d other){
        return x <= other.x && y <= other.y;
    }

    public boolean follows(Vector2d other){
        return x >= other.x && y >= other.y;
    }

    public Vector2d add(Vector2d other){
        return new Vector2d(x + other.x, y + other.y);
    }

    public Vector2d subtract(Vector2d other){
        return new Vector2d(x - other.x, y - other.y);
    }


    public Vector2d upperRight(Vector2d other) {

        int firstCoord = (x >= other.x) ? x : other.x;
        int secondCoord = (y >= other.y) ? y : other.y;

        return new Vector2d(firstCoord, secondCoord);
    }


    public Vector2d lowerLeft(Vector2d other) {

        int firstCoord = (x <= other.x) ? x : other.x;
        int secondCoord = (y <= other.y) ? y : other.y;

        return new Vector2d(firstCoord, secondCoord);
    }


    public Vector2d opposite(){
        return new Vector2d(-x, -y);
    }

    @Override
    public boolean equals(Object other){
        if (this == other)
            return true;
        if (!(other instanceof Vector2d))
            return false;
        Vector2d that = (Vector2d) other;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        return x ^ y;
    }

}
