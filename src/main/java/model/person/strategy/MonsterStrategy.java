package model.person.strategy;

import model.Map;
import model.Point;

public interface MonsterStrategy {
    Point move(Point pos, Map map, Point heroPos);
}
