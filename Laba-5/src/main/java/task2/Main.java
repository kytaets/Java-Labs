package task2;

import task2.controller.ShapeController;
import task2.view.ShapeView;

public class Main {
    public static void main(String[] args) {
        ShapeView view = new ShapeView();
        ShapeController controller = new ShapeController(view);
        controller.start();
    }
}
