package model.person.strategy;

import model.Map;
import model.Point;

public class RandomStrategy implements MonsterStrategy {
    @Override
    public Point move(Point pos, Map map) {
        Point pos2 = null;
        boolean exit = false;
        while(!exit) {
            int x = pos.getX();
            int y = pos.getY();
            int dep = 1 + (int)(Math.random() * ((1 - 4) + 1));
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
            Point newPos = new Point(x, y);
            if (map.isWalkable(newPos)){
                pos2 = newPos;
                exit = true;
            }
        }
        return pos2;
    }
}
