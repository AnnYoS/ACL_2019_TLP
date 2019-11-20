package model.person;

import model.Map;
import model.Vector;
import model.person.strategy.MonsterStrategy;

public class Monster implements Person {

    public static float SPEED = 0.005f;

    private Vector pos;
    private Vector speed;
    private int lifepoints;
    private MonsterStrategy moveStrategy;

    public Monster(Vector pos, MonsterStrategy moveStrategy, int lp) {
        this.pos = pos;
        this.moveStrategy = moveStrategy;
        this.lifepoints = lp;
        speed = new Vector(0, 0);
    }

    @Override
    public void attack(Person p){
        p.looseLP(1);
    }

    @Override
    public void looseLP(int lp) {
        lifepoints = lifepoints - lp;
    }

    @Override
    public Vector getSpeed() {
        return speed;
    }

    @Override
    public void setSpeed(Vector v) {
        speed = v;
    }

    public Vector getPos() {
        return pos;
    }

    public void setPos(Vector pos) {
        this.pos = pos;
    }

    public MonsterStrategy getMoveStrategy() {
        return moveStrategy;
    }

    public void calcSpeed(Map map, Vector heroPos) {
        Vector tmp =  moveStrategy.move(this.pos, map, heroPos);
        speed = tmp;
    }

    public int getLifepoints() { return lifepoints; }
}
