import controller.ShapeController;
import view.ShapeView;

public class Main {
    public static void main(String[] args) {
        ShapeView view = new ShapeView();
        ShapeController controller = new ShapeController(view);
        controller.start();
    }
}
