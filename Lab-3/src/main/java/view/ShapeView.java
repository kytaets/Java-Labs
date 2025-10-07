package view;

import model.Shape;
import java.util.List;

public class ShapeView {
    public void displayShapes(List<Shape> shapes) {
        for (Shape s : shapes) {
            System.out.println(s);
        }
    }

    public void displayMessage(String msg) {
        System.out.println(msg);
    }
}
