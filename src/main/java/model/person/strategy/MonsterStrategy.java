package model.person.strategy;

import model.Map;
import model.Vector;

public interface MonsterStrategy {
    Vector move(Vector pos, Map map, Vector heroPos);
}
