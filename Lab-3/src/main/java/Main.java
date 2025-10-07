import model.*;
import controller.ShapeController;
import view.ShapeView;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Shape> shapes = Arrays.asList(
                new Rectangle("Red", 4, 5),
                new Circle("Blue", 3),
                new Triangle("Green", 6, 4),
                new Rectangle("Yellow", 2, 7),
                new Circle("Black", 5),
                new Triangle("Orange", 3, 8),
                new Rectangle("Purple", 10, 2),
                new Circle("White", 6),
                new Triangle("Gray", 7, 5),
                new Rectangle("Pink", 8, 3)
        );

        ShapeController controller = new ShapeController();
        ShapeView view = new ShapeView();

        view.displayMessage("=== Original Shapes ===");
        view.displayShapes(shapes);

        view.displayMessage("\nTotal area of all shapes: " + controller.calcTotalArea(shapes));
        view.displayMessage("Total area of Rectangles: " + controller.calcTotalAreaByType(shapes, Rectangle.class));

        controller.sortByArea(shapes);
        view.displayMessage("\n=== Shapes sorted by area ===");
        view.displayShapes(shapes);

        controller.sortByColor(shapes);
        view.displayMessage("\n=== Shapes sorted by color ===");
        view.displayShapes(shapes);
    }
}
