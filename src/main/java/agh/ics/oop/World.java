package agh.ics.oop;

public class World {
    public static void main(String[] args) {

        MoveDirection[] directions = new OptionsParser().parse(args);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4), new Vector2d(10, 10)};
        IWorldMap map = new GrassField(10, positions);
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();

        /*
        MoveDirection[] directions = new OptionsParser().parse(args);
        IWorldMap map = new RectangularMap(10, 5);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4), new Vector2d(0, 0) };
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
        */

    }

    public static void run(Directions[] data, int len){
        System.out.println("Start");
        for(int i = 0; i < len; i++){
            switch (data[i]){
                case FORWARD:
                    System.out.println("Zwierzak idzie do przodu");
                    break;
                case BACKWARD:
                    System.out.println("Zwierzak idzie do tyłu");
                    break;
                case RIGHT:
                    System.out.println("Zwierzak skręca w prawo");
                    break;
                case LEFT:
                    System.out.println("Zwierzak skręca w lewo");
                    break;
                default:
                    break;
            }
        }
        System.out.println("Stop");
    }

    public static void convert(String[] data, Directions[] moves){
        int len = data.length;
        for(int i = 0; i < len; i++) {
            switch (data[i]) {
                case "f" -> moves[i] = Directions.FORWARD;
                case "b" -> moves[i] = Directions.BACKWARD;
                case "r" -> moves[i] = Directions.RIGHT;
                case "l" -> moves[i] = Directions.LEFT;
            }
        }
    }
}
