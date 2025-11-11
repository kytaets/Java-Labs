package task3;

import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Choose operation (E = Encrypt / D = Decrypt): ");
        String operation = scanner.nextLine().trim().toUpperCase();

        System.out.print("Enter input file path (Enter = default input.txt): ");
        String inputFile = scanner.nextLine().trim();
        if (inputFile.isEmpty()) inputFile = "Laba-5/src/main/java/task3/files/input.txt";

        System.out.print("Enter output file path (Enter = default output.txt): ");
        String outputFile = scanner.nextLine().trim();
        if (outputFile.isEmpty()) outputFile = "";

        System.out.print("Enter key character: ");
        char key = scanner.nextLine().charAt(0);

        try {
            switch (operation) {
                case "E" -> {
                    try (FileReader fr = new FileReader(inputFile);
                         EncryptWriter ew = new EncryptWriter(new FileWriter(outputFile), key)) {

                        int c;
                        while ((c = fr.read()) != -1) {
                            ew.write(c);
                        }
                    }
                    System.out.println("File encrypted to: " + outputFile);
                }
                case "D" -> {
                    try (DecryptReader dr = new DecryptReader(new FileReader(inputFile), key);
                         FileWriter fw = new FileWriter(outputFile)) {

                        int c;
                        while ((c = dr.read()) != -1) {
                            fw.write(c);
                        }
                    }
                    System.out.println("File decrypted to: " + outputFile);
                }
                default -> System.out.println("Invalid operation! Choose E or D.");
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
