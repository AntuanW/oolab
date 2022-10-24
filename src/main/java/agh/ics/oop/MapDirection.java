package agh.ics.oop;

public enum MapDirection {
    NORTH,
    SOUTH,
    EAST,
    WEST,
    NONE;

    public String toString(){
        return switch (this) {
            case NORTH -> "Północ";
            case SOUTH -> "Południe";
            case EAST -> "Wschód";
            case WEST -> "Zachód";
            default -> "NONE";
        };
    }

    public MapDirection next(){
        return switch (this) {
            case NORTH -> EAST;
            case EAST -> SOUTH;
            case SOUTH -> WEST;
            case WEST -> NORTH;
            default -> NONE;
        };
    }

    public MapDirection previous(){
        return switch (this) {
            case EAST -> NORTH;
            case SOUTH -> EAST;
            case WEST -> SOUTH;
            case NORTH -> WEST;
            default -> NONE;
        };
    }

    public Vector2d toUnitVector(){
        int firstCoord, secondCoord;
        switch (this){
            case NORTH -> {
                firstCoord = 0;
                secondCoord = 1;
            }
            case EAST -> {
                firstCoord = 1;
                secondCoord = 0;
            }
            case SOUTH -> {
                firstCoord = 0;
                secondCoord = -1;
            }
            case WEST -> {
                firstCoord = -1;
                secondCoord = 0;
            }
            default -> {
                firstCoord = 0;
                secondCoord = 0;
            }

        }
        return new Vector2d(firstCoord, secondCoord);
    }
}
