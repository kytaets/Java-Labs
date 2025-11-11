package task2.view;

import java.util.List;
import java.util.Scanner;

public class ShapeView {
    private final Scanner scanner = new Scanner(System.in);

    public void displayMenu() {
        System.out.println("\n=== Shape Menu ===");
        System.out.println("1. Add Circle");
        System.out.println("2. Add Rectangle");
        System.out.println("3. Add Triangle");
        System.out.println("4. Show all shapes");
        System.out.println("5. Show total area");
        System.out.println("6. Show total area by type");
        System.out.println("7. Sort by area");
        System.out.println("8. Sort by color");
        System.out.println("0. Exit");
    }

    public void displayMessage(String msg) {
        System.out.println(msg);
    }

    public String getInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public double getDoubleInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid number, try again.");
            }
        }
    }

    public void displayList(List<String> items) {
        if (items == null || items.isEmpty()) {
            System.out.println("⚠️ No items to display.");
            return;
        }
        for (String item : items) {
            System.out.println(item);
        }
    }
}
