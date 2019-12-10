package math;

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

    public int getXasInt() {
        return Math.round(x);
    }

    public int getYasInt() {
        return Math.round(y);
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public Vector clone() {
        return new Vector(x, y);
    }

    public void add(Vector v) {
        x += v.x;
        y += v.y;
    }

    public void add(Point p) {
        x += p.getX();
        y += p.getY();
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

    public void div(float c) {
        x /= c;
        y /= c;
    }

    public float length() {
        return (float) Math.sqrt(x * x + y * y);
    }

    public float distance(Vector v) {
        Vector tmp = v.clone();

        tmp.sub(this);
        return tmp.length();
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

    @Override
    public String toString() {
        return "Vector{" +
                "x = " + x +
                ", y = " + y +
                '}';
    }
}
