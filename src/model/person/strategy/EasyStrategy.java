package model.person.strategy;

import model.Map;
import model.Point;

public class EasyStrategy implements MonsterStrategy {

    @Override
    public Point move(Point pos, Map map, Point heroPos) {
        int distance =  (int) Math.sqrt((heroPos.getX()-pos.getX())*(heroPos.getX()-pos.getX())+(heroPos.getY()-pos.getY())*(heroPos.getY()-pos.getY()));
        return null;
    }
}
