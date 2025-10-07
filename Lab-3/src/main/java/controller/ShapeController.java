package controller;

import model.Shape;
import java.util.*;

public class ShapeController {

    public double calcTotalArea(List<Shape> shapes) {
        return shapes.stream().mapToDouble(Shape::calcArea).sum();
    }

    public double calcTotalAreaByType(List<Shape> shapes, Class<?> clazz) {
        return shapes.stream()
                .filter(s -> s.getClass() == clazz)
                .mapToDouble(Shape::calcArea)
                .sum();
    }

    public void sortByArea(List<Shape> shapes) {
        shapes.sort(Comparator.comparingDouble(Shape::calcArea));
    }

    public void sortByColor(List<Shape> shapes) {
        shapes.sort(Comparator.comparing(s -> s.shapeColor));
    }
}
