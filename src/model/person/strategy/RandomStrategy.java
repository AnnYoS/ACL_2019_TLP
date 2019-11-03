package model.person.strategy;

import model.Map;
import model.Point;

import java.util.Random;

public class RandomStrategy implements MonsterStrategy {
    private Random rand = new Random();
    @Override
    public Point move(Point pos, Map map) {
        Point pos2 = null;
        boolean exit = false;
        int x;
        int y;
        while(!exit) {
            x = pos.getX();
            y = pos.getY();
            int dep = rand.nextInt(5);
            switch (dep) {
                case 1: // En haut
                    y = y-1;
                    break;
                case 2: // A droite
                    x = x+1;
                    break;
                case 3: // En bas
                    y = y+1;
                    break;
                case 4: // A gauche
                    x = x-1;
                    break;
            }
            if(x != pos.getX() || y != pos.getY()) {
                Point newPos = new Point(x, y);
                if (map.isWalkable(newPos)) {
                    pos2 = newPos;
                    exit = true;
                }
            }
        }
        return pos2;
    }
}
