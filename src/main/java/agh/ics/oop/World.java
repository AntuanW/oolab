package agh.ics.oop;

public class World {
    public static void main(String[] args) {
        System.out.println("System wystartował");
        Directions[] moves = new Directions[100];
        convert(args, moves);
        run(moves, args.length);
        System.out.println("System zakończył działanie");
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