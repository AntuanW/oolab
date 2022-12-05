package agh.ics.oop;

import java.util.Objects;

public class OptionsParser {
    public static MoveDirection[] parse(String[] args){

        int size = args.length;
        MoveDirection[] howToMove = new MoveDirection[size];

        for (int i = 0; i < size; i++){
            switch (args[i]){
                case "f":
                case "forward":
                    howToMove[i] = MoveDirection.FORWARD;
                    break;

                case "b":
                case "backward":
                    howToMove[i] = MoveDirection.BACKWARD;
                    break;

                case "l":
                case "left":
                    howToMove[i] = MoveDirection.LEFT;
                    break;

                case "r":
                case "right":
                    howToMove[i] = MoveDirection.RIGHT;
                    break;

                default: throw new IllegalArgumentException(args[i] + " is not legal move specification");
            }
        }
        return howToMove;
    }
}
