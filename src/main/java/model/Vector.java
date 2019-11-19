package model;

import java.util.Objects;

public class Vector {
    private float x;
    private float y;

    public Vector(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public Vector clone() {
        return new Vector(x, y);
    }

    public void add(Vector v) {
        x += v.x;
        y += v.y;
    }

    public void add(Vector v, float c) {
        x += v.x * c;
        y += v.y * c;
    }

    public void sub(Vector v) {
        x -= v.x;
        y -= v.y;
    }

    public void mult(float c) {
        x *= c;
        y *= c;
    }

    public float length() {
        return (float) Math.sqrt(x * x + y * y);
    }

    public float distance(Vector v) {
        Vector tmp = v.clone();

        tmp.sub(this);
        return tmp.length();
    }

    public int integetManhattanDistance(Vector v) {
        int x1 = (int) v.getX();
        int y1 = (int) v.getY();

        int x2 = (int) getX();
        int y2 = (int) getY();

        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector vector = (Vector) o;
        return x == vector.x &&
                y == vector.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
