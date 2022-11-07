package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Vector2dTest {

    @Test
    void equalsTest(){
        Vector2d firstTester = new Vector2d(1, 1);
        Vector2d secondTester = new Vector2d(1, 1);
        Vector2d thirdTester = new Vector2d(0, 0);
        assertTrue(firstTester.equals(secondTester));
        assertFalse(firstTester.equals(thirdTester));
    }

    @Test
    void toStringTest(){
        Vector2d firstTester = new Vector2d(1, 8);
        Vector2d secondTester = new Vector2d(-3, 6);
        assertEquals("(1,8)", firstTester.toString());
        assertNotEquals("(2,1)", secondTester.toString());
    }

    @Test
    void precedesTest(){
        Vector2d firstTester = new Vector2d(1, 1);
        Vector2d secondTester = new Vector2d(2, 2);
        Vector2d thirdTester = new Vector2d(2, 3);
        assertTrue(firstTester.precedes(secondTester));
        assertTrue(firstTester.precedes(firstTester));
        assertTrue(firstTester.precedes(thirdTester));
        assertFalse(secondTester.precedes(firstTester));
    }

    @Test
    void followsTest(){
        Vector2d firstTester = new Vector2d(1, 1);
        Vector2d secondTester = new Vector2d(2, 2);
        Vector2d thirdTester = new Vector2d(2, 3);
        assertFalse(firstTester.follows(secondTester));
        assertTrue(thirdTester.follows(firstTester));
        assertTrue(secondTester.follows(secondTester));
    }

    @Test
    void upperRightTest(){
        Vector2d firstTester = new Vector2d(1, 2);
        Vector2d secondTester = new Vector2d(2, 1);
        Vector2d expectedResult = new Vector2d(2, 2);
        assertEquals(expectedResult, firstTester.upperRight(secondTester));
        assertEquals(expectedResult, secondTester.upperRight(firstTester));
    }

    @Test
    void lowerLeftTest(){
        Vector2d firstTester = new Vector2d(1, 2);
        Vector2d secondTester = new Vector2d(2, 1);
        Vector2d expectedResult = new Vector2d(1, 1);
        assertEquals(expectedResult, firstTester.lowerLeft(secondTester));
        assertEquals(expectedResult, secondTester.lowerLeft(firstTester));
    }

    @Test
    void addTest(){
        Vector2d firstTester = new Vector2d(1, 2);
        Vector2d secondTester = new Vector2d(2, 1);
        Vector2d expectedResult = new Vector2d(3, 3);
        assertEquals(expectedResult, firstTester.add(secondTester));
        assertNotEquals(new Vector2d(1,5), firstTester.add(secondTester));
        assertEquals(expectedResult, secondTester.add(firstTester));
    }

    @Test
    void substractTest(){
        Vector2d firstTester = new Vector2d(1, 2);
        Vector2d secondTester = new Vector2d(2, 1);
        Vector2d expectedResult = new Vector2d(-1, 1);
        assertEquals(expectedResult, firstTester.subtract(secondTester));
        assertNotEquals(expectedResult, secondTester.subtract(firstTester));
    }

    @Test
    void oppositeTest(){
        Vector2d firstTester = new Vector2d(-1, 2);
        Vector2d secondTester = new Vector2d(3, 10);
        Vector2d expectedResult = new Vector2d(1, -2);
        assertEquals(expectedResult, firstTester.opposite());
        assertNotEquals(expectedResult, secondTester.opposite());
    }

}