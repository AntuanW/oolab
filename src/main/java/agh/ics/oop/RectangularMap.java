package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class RectangularMap extends AbstractWorldMap {

    private final int width;
    private final int height;

    public RectangularMap(int width, int height){
        this.width = width;
        this.height = height;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return position.follows(getlowerLeft()) && position.precedes(getupperRight()) && !isOccupied(position);
    }

    @Override
    public Vector2d getlowerLeft() {
        return new Vector2d(0, 0);
    }

    @Override
    public Vector2d getupperRight() {
        return new Vector2d(this.width - 1, this.height -1);
    }
}
