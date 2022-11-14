package agh.ics.oop;

import java.util.List;

public class SimulationEngine implements IEngine{

    private final IWorldMap map;
    private final List<MoveDirection> directions;

    public SimulationEngine(MoveDirection[] directions, IWorldMap map, Vector2d[] positions){
        this.map = map;
        this.directions = List.of(directions);
        for(Vector2d position : positions){
            Animal animal = new Animal(map, position);
            this.map.place(animal);
        }
    }

    @Override
    public void run() {
        System.out.println(map);
        for(int i = 0; i < directions.size(); i++){
            RectangularMap.getAnimals().get(i % RectangularMap.getAnimals().size()).move(directions.get(i));
            System.out.println(map);
        }
    }
}
