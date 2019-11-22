package model.person;

import model.Map;
import model.Vector;
import model.person.strategy.MonsterStrategy;

public class Monster implements Person {

    public static float SPEED = 0.0025f;

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

    public void setSpeed(Vector speed) {
        if(! speed.equals(this.speed)) {
            pos.setX(Math.round(pos.getX()));
            pos.setY(Math.round(pos.getY()));
        }

        this.speed = speed;
    }

    @Override
    public void forceSetSpeed(Vector v) {
        if(! speed.equals(this.speed)) {
            pos.setX((int) pos.getX());
            pos.setY((int) pos.getY());
        }
        speed = v;
    }


    public Vector getPos() {
        return pos;
    }

    public void setPos(Vector pos) {
        this.pos = pos;
    }

    public void calcSpeed(Map map, Vector heroPos) {
        Vector tmp =  moveStrategy.move(this.pos, map, heroPos);
        setSpeed(tmp);
    }

    public int getLifepoints() { return lifepoints; }
}
