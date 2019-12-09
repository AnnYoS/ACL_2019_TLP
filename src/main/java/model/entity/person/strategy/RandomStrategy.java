package model.entity.person.strategy;

import math.Point;
import model.Map;
import math.Vector;
import model.entity.person.Monster;

import java.util.Random;

public class RandomStrategy implements MonsterStrategy {
    private Random rand = new Random();
    @Override
    public Vector move(Monster m, Map map, Point heroPos) {
        Point pos = m.getPos();

        Point newPos = null;
        Vector newSpeed = null;
        boolean exit = false;
        float x;
        float y;
        while(!exit) {
            x = pos.getX();
            y = pos.getY();
            int dep = rand.nextInt(5);
            switch (dep) {
                case 1: // En haut
                    y = y-1;
                    newSpeed = new Vector(0f, -Monster.SPEED);
                    break;
                case 2: // A droite
                    x = x+1;
                    newSpeed = new Vector(Monster.SPEED, 0f);
                    break;
                case 3: // En bas
                    y = y+1;
                    newSpeed = new Vector(0f, Monster.SPEED);
                    break;
                case 4: // A gauche
                    x = x-1;
                    newSpeed = new Vector(-Monster.SPEED, 0f);
                    break;
            }
            if(x != pos.getX() || y != pos.getY()) {
                newPos = new Point((int)x, (int)y);
                if (map.isWalkable(newPos)) {
                    exit = true;
                }
            }
        }
        return newSpeed;
    }
}
