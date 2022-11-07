package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {

    Animal animal0 = new Animal();
    Animal animal1 = new Animal();
    Animal animal2 = new Animal();
    Animal animal3 = new Animal();

    @Test
    void toStringTest(){

        assertEquals("(2,2) Północ", animal0.toString());

        animal0.move(MoveDirection.FORWARD);
        animal0.move(MoveDirection.FORWARD);
        animal0.move(MoveDirection.LEFT);
        animal0.move(MoveDirection.FORWARD);
        assertEquals("(1,4) Zachód", animal0.toString());
    }

    @Test
    void isAtTest(){
        assertTrue(animal1.isAt(new Vector2d(2, 2)));
        assertFalse(animal1.isAt(new Vector2d(-2, 8)));
        animal1.move(MoveDirection.FORWARD);
        animal1.move(MoveDirection.FORWARD);
        animal1.move(MoveDirection.LEFT);
        animal1.move(MoveDirection.FORWARD);
        assertTrue(animal1.isAt(new Vector2d(1, 4)));
        assertFalse(animal1.isAt(new Vector2d(2, 4)));
    }

    @Test
    void moveTest(){

        MoveDirection[] pathFor2 =
                {MoveDirection.LEFT, MoveDirection.FORWARD, MoveDirection.RIGHT, MoveDirection.FORWARD, MoveDirection.LEFT,
                        MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.RIGHT, MoveDirection.FORWARD, MoveDirection.FORWARD};

        Vector2d[] locationsFor2 =
                {new Vector2d(2, 2), new Vector2d(1, 2), new Vector2d(1, 2), new Vector2d(1, 3),
                        new Vector2d(1, 3), new Vector2d(0, 3), new Vector2d(0, 3), new Vector2d(0, 3),
                        new Vector2d(0, 4), new Vector2d(0, 4)};

        MoveDirection[] pathFor3 =
                {MoveDirection.RIGHT, MoveDirection.FORWARD, MoveDirection.RIGHT, MoveDirection.FORWARD, MoveDirection.BACKWARD,
                        MoveDirection.LEFT, MoveDirection.FORWARD, MoveDirection.RIGHT, MoveDirection.FORWARD, MoveDirection.FORWARD,
                        MoveDirection.FORWARD, MoveDirection.RIGHT, MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.RIGHT,
                        MoveDirection.FORWARD, MoveDirection.LEFT, MoveDirection.FORWARD, MoveDirection.LEFT, MoveDirection.FORWARD,
                        MoveDirection.RIGHT, MoveDirection.FORWARD};

        Vector2d[] locationsFor3 =
                {new Vector2d(2, 2), new Vector2d(3, 2), new Vector2d(3, 2), new Vector2d(3, 1),
                        new Vector2d(3, 2), new Vector2d(3, 2), new Vector2d(4, 2), new Vector2d(4, 2),
                        new Vector2d(4, 1), new Vector2d(4, 0), new Vector2d(4, 0), new Vector2d(4, 0),
                        new Vector2d(3, 0), new Vector2d(2, 0), new Vector2d(2, 0), new Vector2d(2, 1),
                        new Vector2d(2, 1), new Vector2d(1, 1), new Vector2d(1, 1), new Vector2d(1, 0),
                        new Vector2d(1, 0), new Vector2d(0, 0)};

        for (int i = 0; i < locationsFor2.length; i++){
            animal2.move(pathFor2[i]);
            assertTrue(animal2.isAt(locationsFor2[i]));
        }
        assertEquals("(0,4) Północ", animal2.toString());

        for (int i = 0; i < locationsFor3.length; i++){
            animal3.move(pathFor3[i]);
            assertTrue(animal3.isAt(locationsFor3[i]));
        }
        assertEquals("(0,0) Zachód", animal3.toString());
    }

}