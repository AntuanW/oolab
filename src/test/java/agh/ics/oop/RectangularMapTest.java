package agh.ics.oop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RectangularMapTest{

    static String[] args;
    static MoveDirection[] directions;
    static IWorldMap map;
    static Vector2d[] positions;
    static IEngine engine;

    @BeforeEach
    void SetUpMethod() {
        args = new String[] {"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f", "b", "b"};
        directions = new OptionsParser().parse(args);
        map = new RectangularMap(10, 5);
        positions = new Vector2d[]{new Vector2d(0, 0), new Vector2d(2, 2), new Vector2d(3, 4)};
        engine = new SimulationEngine(directions, map, positions);
        engine.run();
    }

    @Test
    void canMoveToTest(){
        boolean vector1 = map.canMoveTo(new Vector2d(0, 4));
        boolean vector2 = map.canMoveTo(new Vector2d(3, 2));
        boolean vector3 = map.canMoveTo(new Vector2d(6, 4));
        assertFalse(vector1);
        assertFalse(vector2);
        assertFalse(vector2);
        assertFalse(map.canMoveTo(new Vector2d(10, 2)));
        assertFalse(map.canMoveTo(new Vector2d(1, 5)));
        assertFalse(map.canMoveTo(new Vector2d(-3, 3)));
        assertFalse(map.canMoveTo(new Vector2d(5, -1)));
        assertTrue(map.canMoveTo(new Vector2d(0, 0)));
        assertTrue(map.canMoveTo(new Vector2d(9, 4)));
        assertTrue(map.canMoveTo(new Vector2d(5, 1)));
    }

    @Test
    void placeTest(){
        Animal zebra = new Animal(map, new Vector2d(5, 1));
        Animal kot = new Animal(map, new Vector2d(0, 0));
        Animal dzik = new Animal(map, new Vector2d(3, 2));
        boolean zebraTest = map.place(zebra);
        boolean kotTest = map.place(kot);
        boolean dzikTest = map.place(dzik);
        assertTrue(zebraTest);
        assertTrue(kotTest);
        assertFalse(dzikTest);
        assertTrue(map.isOccupied(new Vector2d(5, 1)));
        assertTrue(map.isOccupied(new Vector2d(0, 0)));
        assertTrue(map.isOccupied(new Vector2d(3, 2)));
    }

    @Test
    void isOccupiedTest(){
        assertTrue(map.isOccupied(new Vector2d(0, 4)));
        assertTrue(map.isOccupied(new Vector2d(3, 2)));
        assertTrue(map.isOccupied(new Vector2d(6, 4)));
        assertFalse(map.isOccupied(new Vector2d(0, 0)));
        assertFalse(map.isOccupied(new Vector2d(1, -2)));
        assertFalse(map.isOccupied(new Vector2d(5, 5)));
    }

    @Test
    void ObjectAtTest(){
        Object first = map.objectAt(new Vector2d(0, 4));
        Object second = map.objectAt(new Vector2d(3, 2));
        Object third = map.objectAt(new Vector2d(6, 4));
        System.out.println(first.getClass());
        System.out.println(second.getClass());
        System.out.println(third.getClass());
        assertTrue(first instanceof Animal);
        assertTrue(second instanceof Animal);
        assertTrue(third instanceof Animal);
        assertFalse(map.objectAt(new Vector2d(7, 1)) instanceof Animal);
    }
}