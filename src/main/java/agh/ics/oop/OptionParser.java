package agh.ics.oop;

import java.util.Objects;

public class OptionParser {
    MoveDirection[] parse(String[] args){
        int size = args.length;
        int returnSize = 0;
        for (String arg : args) {
            if (arg.equals("f") || arg.equals("forward") || arg.equals("b") || arg.equals("backward") || arg.equals("l") || arg.equals("left") || arg.equals("r") || arg.equals("right")) {
                returnSize++;
            }
        }
        MoveDirection[] howToMove = new MoveDirection[returnSize];
        int currIdx = 0;
        for (int i = 0; i < size; i++){
            switch (args[i]){
                case "f":
                case "forward":
                    howToMove[currIdx] = MoveDirection.FORWARD;
                    currIdx++;
                    break;

                case "b":
                case "backward":
                    howToMove[currIdx] = MoveDirection.BACKWARD;
                    currIdx++;
                    break;

                case "l":
                case "left":
                    howToMove[currIdx] = MoveDirection.LEFT;
                    currIdx++;
                    break;

                case "r":
                case "right":
                    howToMove[currIdx] = MoveDirection.RIGHT;
                    currIdx++;
                    break;
            }
        }
        return howToMove;
    }
}
