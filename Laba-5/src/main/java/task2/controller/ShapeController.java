package task2.controller;

import task2.model.*;
import task2.view.ShapeView;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.io.*;

public class ShapeController {

    private final List<Shape> shapes = new ArrayList<>();
    private final ShapeView view;
    private final ShapeFileManager fileManager = new ShapeFileManager();

    public ShapeController(ShapeView view) {
        this.view = view;
    }

    public void start() {
        boolean running = true;
        while (running) {
            view.displayMenu();
            String choiceStr = view.getInput("Choose option: ");

            int choice;
            try {
                choice = Integer.parseInt(choiceStr);
            } catch (NumberFormatException e) {
                view.displayMessage("Invalid input! Enter a number.");
                continue;
            }

            switch (choice) {
                case 1 -> addCircle();
                case 2 -> addRectangle();
                case 3 -> addTriangle();
                case 4 -> showAllShapes();
                case 5 -> showTotalArea();
                case 6 -> showTotalAreaByType();
                case 7 -> sortByArea();
                case 8 -> sortByColor();
                case 9 -> saveToFile();
                case 10 -> loadFromFile();
                case 0 -> {
                    view.displayMessage("Exiting...");
                    running = false;
                }
                default -> view.displayMessage("Invalid choice!");
            }

        }
    }

    private void addCircle() {
        String color = view.getInput("Enter color: ");
        double radius = view.getDoubleInput("Enter radius: ");
        shapes.add(new Circle(color, radius));
        view.displayMessage("Circle added!");
    }

    private void addRectangle() {
        String color = view.getInput("Enter color: ");
        double width = view.getDoubleInput("Enter width: ");
        double height = view.getDoubleInput("Enter height: ");
        shapes.add(new Rectangle(color, width, height));
        view.displayMessage("Rectangle added!");
    }

    private void addTriangle() {
        String color = view.getInput("Enter color: ");
        double base = view.getDoubleInput("Enter base: ");
        double height = view.getDoubleInput("Enter height: ");
        shapes.add(new Triangle(color, base, height));
        view.displayMessage("Triangle added!");
    }

    private void showAllShapes() {
        List<String> descriptions = shapes.stream()
                .map(Object::toString)
                .toList();
        view.displayList(descriptions);
    }

    private void showTotalArea() {
        double total = shapes.stream().mapToDouble(Shape::calcArea).sum();
        view.displayMessage("Total area of all shapes: " + total);
    }

    private void showTotalAreaByType() {
        String type = view.getInput("Enter type (circle/rectangle/triangle): ").toLowerCase();
        double total = switch (type) {
            case "circle" -> calcTotalAreaByType(Circle.class);
            case "rectangle" -> calcTotalAreaByType(Rectangle.class);
            case "triangle" -> calcTotalAreaByType(Triangle.class);
            default -> {
                view.displayMessage("Unknown type!");
                yield 0;
            }
        };
        view.displayMessage("Total area for " + type + ": " + total);
    }

    private double calcTotalAreaByType(Class<?> clazz) {
        return shapes.stream()
                .filter(s -> s.getClass() == clazz)
                .mapToDouble(Shape::calcArea)
                .sum();
    }

    private void sortByArea() {
        shapes.sort(Comparator.comparingDouble(Shape::calcArea));
        view.displayMessage("Sorted by area!");
    }

    private void sortByColor() {
        shapes.sort(Comparator.comparing(Shape::getColor));
        view.displayMessage("Sorted by color!");
    }

    private void saveToFile() {
        String customDir = view.getInput("Enter directory to save (press Enter for default): ");
        String dirPath = customDir.isBlank()
                ? "Laba-5/src/main/java/task2/savedShapes"
                : customDir;

        String name = view.getInput("Enter filename (without extension): ");
        String filename = name.endsWith(".dat") ? name : name + ".dat";

        try {
            fileManager.saveShapes(shapes, dirPath, filename);
            view.displayMessage("Shapes saved successfully.");
        } catch (IOException e) {
            view.displayMessage("Error saving file: " + e.getMessage());
        }
    }

    private void loadFromFile() {
        String customDir = view.getInput("Enter directory to load from (press Enter for default): ");
        String dirPath = customDir.isBlank()
                ? "Laba-5/src/main/java/task2/savedShapes"
                : customDir;

        String name = view.getInput("Enter filename (without extension): ");
        String filename = name.endsWith(".dat") ? name : name + ".dat";

        try {
            List<Shape> loaded = fileManager.loadShapes(dirPath, filename);
            shapes.clear();
            shapes.addAll(loaded);
            view.displayMessage("Shapes loaded successfully.");
        } catch (IOException | ClassNotFoundException e) {
            view.displayMessage("Error loading file: " + e.getMessage());
        }
    }
}
