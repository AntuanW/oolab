package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Objects;

public class GrassField extends AbstractWorldMap{

    private final int numberOfGrass;
    private final Vector2d[] positions;
    private final Vector2d lowerLeftCorner = new Vector2d(Integer.MIN_VALUE, Integer.MIN_VALUE);
    private final Vector2d upperRightCorner = new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE);


    public GrassField(int n, Vector2d[] positions) {
        this.numberOfGrass = n;
        this.positions = positions;

        Random generator = new Random();
        int howManyGenerated = 0;
        while(howManyGenerated <= n){
            int x = generator.nextInt( (int) Math.sqrt(10*numberOfGrass) );
            int y = generator.nextInt( (int) Math.sqrt(10*numberOfGrass) );
            Vector2d possiblePosition = new Vector2d(x, y);
            if(!this.isOccupied(possiblePosition)){
                int count = 0;
                for( Vector2d position: positions){
                    if(!position.equals(possiblePosition)){
                        count ++;
                    }
                }
                if(count == positions.length){
                    grasses.add(new Grass(possiblePosition));
                    howManyGenerated++;
                }
            }
        }

    }


    @Override
    public boolean canMoveTo(Vector2d position) {
        boolean can = position.follows(lowerLeftCorner) && position.precedes(upperRightCorner) && !isOccupied(position);
        if (can){
            return true;
        }
        else{
            Object object = objectAt(position);
            if(object instanceof Grass){
                for (Grass grass : grasses) {
                    if (grass.getPosition().equals(position)){
                        Random generator = new Random();
                        int x = generator.nextInt( (int)Math.sqrt(10*numberOfGrass) );
                        int y = generator.nextInt( (int)Math.sqrt(10*numberOfGrass) );
                        while(isOccupied(new Vector2d(x, y))){
                            x = generator.nextInt( (int)Math.sqrt(10*numberOfGrass) );
                            y = generator.nextInt( (int)Math.sqrt(10*numberOfGrass) );
                        }
                        grasses.remove(grass);
                        grasses.add(new Grass(new Vector2d(x, y)));
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public Vector2d lowerLeft(){
        int lowestX;
        int lowestY;

        if(animals.size() != 0){
            lowestX = animals.get(0).getPlace().x;
            lowestY = animals.get(0).getPlace().y;
        }else {
            lowestX = 0;
            lowestY = 0;
        }

        for(Animal animal : animals){
            Vector2d coordinates = animal.getPlace();
            lowestX = Math.min(coordinates.x, lowestX);
            lowestY = Math.min(coordinates.y, lowestY);
        }
        return new Vector2d(lowestX, lowestY);
    }

    @Override
    public Vector2d upperRight(){
        int biggestX;
        int biggestY;

        if(animals.size() != 0){
            biggestX = animals.get(0).getPlace().x;
            biggestY = animals.get(0).getPlace().y;
        }else {
            biggestX = 1;
            biggestY = 1;
        }

        for(Animal animal : animals){
            Vector2d coordinates = animal.getPlace();
            biggestX = Math.max(coordinates.x, biggestX);
            biggestY = Math.max(coordinates.y, biggestY);
        }
        return new Vector2d(biggestX, biggestY);
    }

}
