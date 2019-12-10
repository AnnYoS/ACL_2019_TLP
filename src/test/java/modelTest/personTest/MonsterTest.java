package modelTest.personTest;

import math.Point;
import math.Vector;
import model.Map;
import model.entity.person.Monster;
import model.entity.person.strategy.RandomStrategy;
import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MonsterTest {

    @Test
    public void testSetSpeed() {
        Monster monster = new Monster(new Point(0, 0), new RandomStrategy(), 10);

        monster.setSpeed(new Vector(10, 10));

        assertEquals(new Vector(10, 10), monster.getSpeed());
        assertEquals(0, monster.getPos().getX());
        assertEquals(0, monster.getPos().getY());
        assertEquals(new Vector(0, 0), monster.getDrawPos());
    }

    @Test
    public void testEvolveStatic() {
        Monster monster = new Monster(new Point(0, 0), new RandomStrategy(), 10);
        monster.setSpeed(new Vector(0.5f, 0));

        Map env = EasyMock.createStrictMock(Map.class);

        EasyMock.expect(env.isWalkable(new Point(1, 0))).andReturn(true);

        EasyMock.replay(env);

        monster.evolve(env, 1);

        EasyMock.verify(env);

        assertEquals(new Point(0, 0), monster.getPos());
        assertEquals(new Vector(0.5f, 0f), monster.getDrawPos());
        assertEquals(new Vector(0.5f, 0f), monster.getAcc());
    }

    @Test
    public void testEvolveWithMove() {
        Monster monster = new Monster(new Point(0, 0), new RandomStrategy(),10);
        monster.setSpeed(new Vector(1f, 0));

        Map env = EasyMock.createStrictMock(Map.class);

        EasyMock.expect(env.isWalkable(new Point(1, 0))).andReturn(true);

        EasyMock.replay(env);

        monster.evolve(env, 1);

        EasyMock.verify(env);

        assertEquals(new Point(1, 0), monster.getPos());
        assertEquals(new Vector(1f, 0f), monster.getDrawPos());
        assertEquals(new Vector(0f, 0f), monster.getAcc());
    }

    @Test
    public void testEvolveWithNotWalkable() {
        Monster monster = new Monster(new Point(0, 0), new RandomStrategy(),10);
        monster.setSpeed(new Vector(1f, 0));

        Map env = EasyMock.createStrictMock(Map.class);

        EasyMock.expect(env.isWalkable(new Point(1, 0))).andReturn(false);

        EasyMock.replay(env);

        monster.evolve(env, 1);

        EasyMock.verify(env);

        assertEquals(new Point(0, 0), monster.getPos());
        assertEquals(new Vector(0f, 0f), monster.getDrawPos());
        assertEquals(new Vector(0f, 0f), monster.getAcc());
    }

    @Test
    public void testEvolveWithNotWalkableWithAccNot0() {
        Monster monster = new Monster(new Point(0, 0),new RandomStrategy(), 10);
        monster.setSpeed(new Vector(1f, 0));
        monster.setAcc(new Vector(0.5f, 0f));

        Map env = EasyMock.createStrictMock(Map.class);

        EasyMock.expect(env.isWalkable(new Point(1, 0))).andReturn(false);

        EasyMock.replay(env);

        monster.evolve(env, 1);

        EasyMock.verify(env);

        assertEquals(new Point(0, 0), monster.getPos());
        assertEquals(new Vector(0f, 0f), monster.getDrawPos());
        assertEquals(new Vector(0f, 0f), monster.getAcc());
    }
}
