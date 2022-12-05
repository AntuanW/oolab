package agh.ics.oop;

import agh.ics.oop.gui.App;
import javafx.application.Application;

public class World {
    public static void main(String[] args) {

        Application.launch(App.class, args);

        //f b r r l r f f r r r r f f r f f r f f r f f r
        /*try{
            String[] args2 = new String[]{"f", "b", "r", "r", "l", "r", "f", "f", "r", "r", "r", "r", "f", "f", "r", "f", "f", "r", "f", "f", "r", "f", "f", "r"};
            MoveDirection[] directions = new OptionsParser().parse(args2);
            Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4), new Vector2d(11, 11)};
            IWorldMap map = new GrassField(10, positions);
            IEngine engine = new SimulationEngine(directions, map, positions);
            engine.run();
        }catch (IllegalArgumentException ex){
            System.out.println(ex.getMessage());
        }*/

    }
}
