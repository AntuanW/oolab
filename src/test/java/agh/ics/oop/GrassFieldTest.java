package agh.ics.oop;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GrassFieldTest {

    static String[] args;
    static MoveDirection[] directions;
    static IWorldMap map;
    static Vector2d[] positions;
    static IEngine engine;

    @BeforeAll
    static void setUpMethod(){
        args = new String[] {"f", "b", "r", "r", "l", "r", "f", "f", "r", "r", "r", "r", "f", "f", "r", "f", "f", "r", "f", "f", "r", "f", "f", "r"};
        directions = new OptionsParser().parse(args);
        positions = new Vector2d[] {new Vector2d(2,2), new Vector2d(3,4), new Vector2d(10, 10)};
        map = new GrassField(10, positions);
        engine = new SimulationEngine(directions, map, positions);
        engine.run();
    }

    @Test
    void canMoveTo() {
        boolean vector1 = map.canMoveTo(new Vector2d(2, -1));
        boolean vector2 = map.canMoveTo(new Vector2d(3, 7));
        boolean vector3 = map.canMoveTo(new Vector2d(10, 10));
        assertFalse(vector1);
        assertFalse(vector2);
        assertFalse(vector2);
        assertTrue(map.canMoveTo(new Vector2d(10, 2)));
        assertTrue(map.canMoveTo(new Vector2d(1, 5)));
        assertTrue(map.canMoveTo(new Vector2d(-3, 3)));
        assertTrue(map.canMoveTo(new Vector2d(5, -1)));
        assertTrue(map.canMoveTo(new Vector2d(0, 0)));
        assertTrue(map.canMoveTo(new Vector2d(9, 4)));
        assertTrue(map.canMoveTo(new Vector2d(5, 1)));
    }

    @Test
    void ObjectAtTest(){
        Object first = map.objectAt(new Vector2d(2, -1));
        Object second = map.objectAt(new Vector2d(3, 7));
        Object third = map.objectAt(new Vector2d(10, 10));
        System.out.println(first.getClass());
        System.out.println(second.getClass());
        System.out.println(third.getClass());
        assertTrue(first instanceof Animal);
        assertTrue(second instanceof Animal);
        assertTrue(third instanceof Animal);
        assertFalse(map.objectAt(new Vector2d(7, 1)) instanceof Animal);
    }

}