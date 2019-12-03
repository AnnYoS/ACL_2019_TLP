package modelTest.personTest;

import math.Point;
import math.Vector;
import model.Map;
import model.person.Hero;
import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HeroTest {
    @Test
    public void testSetSpeed() {
        Hero hero = new Hero(new Point(0, 0), 10);

        hero.setSpeed(new Vector(10, 10));

        assertEquals(new Vector(10, 10), hero.getSpeed());
        assertEquals(0, hero.getPos().getX());
        assertEquals(0, hero.getPos().getY());
        assertEquals(new Vector(0, 0), hero.getDrawPos());
    }

    @Test
    public void testEvolveStatic() {
        Hero hero = new Hero(new Point(0, 0), 10);
        hero.setSpeed(new Vector(0.5f, 0));

        Map env = EasyMock.createStrictMock(Map.class);

        EasyMock.expect(env.isWalkable(new Point(1, 0))).andReturn(true);

        EasyMock.replay(env);

        hero.evolve(env, 1);

        EasyMock.verify(env);

        assertEquals(new Point(0, 0), hero.getPos());
        assertEquals(new Vector(0.5f, 0f), hero.getDrawPos());
        assertEquals(new Vector(0.5f, 0f), hero.getAcc());
    }

    @Test
    public void testEvolveWithMove() {
        Hero hero = new Hero(new Point(0, 0), 10);
        hero.setSpeed(new Vector(1f, 0));

        Map env = EasyMock.createStrictMock(Map.class);

        EasyMock.expect(env.isWalkable(new Point(1, 0))).andReturn(true);

        EasyMock.replay(env);

        hero.evolve(env, 1);

        EasyMock.verify(env);

        assertEquals(new Point(1, 0), hero.getPos());
        assertEquals(new Vector(1f, 0f), hero.getDrawPos());
        assertEquals(new Vector(0f, 0f), hero.getAcc());
    }

    @Test
    public void testEvolveWithNotWalkable() {
        Hero hero = new Hero(new Point(0, 0), 10);
        hero.setSpeed(new Vector(1f, 0));

        Map env = EasyMock.createStrictMock(Map.class);

        EasyMock.expect(env.isWalkable(new Point(1, 0))).andReturn(false);

        EasyMock.replay(env);

        hero.evolve(env, 1);

        EasyMock.verify(env);

        assertEquals(new Point(0, 0), hero.getPos());
        assertEquals(new Vector(0f, 0f), hero.getDrawPos());
        assertEquals(new Vector(0f, 0f), hero.getAcc());
    }

    @Test
    public void testEvolveWithNotWalkableWithAccNot0() {
        Hero hero = new Hero(new Point(0, 0), 10);
        hero.setSpeed(new Vector(1f, 0));
        hero.setAcc(new Vector(0.5f, 0f));

        Map env = EasyMock.createStrictMock(Map.class);

        EasyMock.expect(env.isWalkable(new Point(1, 0))).andReturn(false);

        EasyMock.replay(env);

        hero.evolve(env, 1);

        EasyMock.verify(env);

        assertEquals(new Point(0, 0), hero.getPos());
        assertEquals(new Vector(0f, 0f), hero.getDrawPos());
        assertEquals(new Vector(0f, 0f), hero.getAcc());
    }
}
