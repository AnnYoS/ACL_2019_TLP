package model.person;

import model.Map;
import model.Point;
import model.person.strategy.MonsterStrategy;

public class Monster implements Person {
    private Point pos;
    private MonsterStrategy moveStrategy;

    public Monster(Point pos, MonsterStrategy moveStrategy) {
        this.pos = pos;
        this.moveStrategy = moveStrategy;
    }

    public Point getPos() {
        return pos;
    }

    public void setPos(Point pos) {
        this.pos = pos;
    }

    public MonsterStrategy getMoveStrategy() {
        return moveStrategy;
    }

    public Point getMove(Map map){
        return moveStrategy.move(this.pos, map);
    }

}
