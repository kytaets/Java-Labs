package task2.model;

import java.io.Serializable;

public abstract class Shape implements Drawable, Serializable {
    protected String shapeColor;

    public Shape(String shapeColor) {
        this.shapeColor = shapeColor;
    }

    public String getColor() {
        return shapeColor;
    }

    public abstract double calcArea();

    @Override
    public String toString() {
        return "Shape[color=" + shapeColor + "]";
    }
}
