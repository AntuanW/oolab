package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OptionParserTest {

    @Test
    void parseTest(){
        String[] data = {"f", "r", "b", "forward", "left", "l", "backward", "right", "f", "r",
                "b", "forward", "left", "l", "backward", "right"};

        MoveDirection[] expected = {MoveDirection.FORWARD, MoveDirection.RIGHT, MoveDirection.BACKWARD, MoveDirection.FORWARD,
                MoveDirection.LEFT, MoveDirection.LEFT, MoveDirection.BACKWARD, MoveDirection.RIGHT, MoveDirection.FORWARD,
                MoveDirection.RIGHT, MoveDirection.BACKWARD, MoveDirection.FORWARD, MoveDirection.LEFT, MoveDirection.LEFT,
                MoveDirection.BACKWARD, MoveDirection.RIGHT};

        OptionsParser parser = new OptionsParser();
        MoveDirection[] results = parser.parse(data);

        assertEquals(expected.length, results.length);
        for (int i = 0; i < expected.length; i++){
            assertEquals(expected[i], results[i]);
        }

    }

    @Test
    void exceptionTest(){
        String[] data = {"BLAD", "f", "r", "b", "fjl", "forward", "left", "l", "backward", "right", "BLAD", "f", "r",
                "b", "forward", "left", "l", "backward", "right", "BLAD", "BLAD"};
        OptionsParser parser = new OptionsParser();
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> parser.parse(data));
        assertTrue(thrown.getMessage().contains("is not legal move"));
    }

}