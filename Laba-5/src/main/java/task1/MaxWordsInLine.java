package task1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class MaxWordsInLine {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String defaultPath = "Laba-5/src/main/java/task1/files/input.txt";

        System.out.print("Enter path to file (press Enter for default): ");
        String inputPath = scanner.nextLine().trim();

        String filePath = inputPath.isEmpty() ? defaultPath : inputPath;

        String maxLine = "";
        int maxWords = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                int wordCount = countWords(line);
                if (wordCount > maxWords) {
                    maxWords = wordCount;
                    maxLine = line;
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return;
        }

        System.out.println("\nMax word line:");
        System.out.println(maxLine);
        System.out.println("Words number: " + maxWords);
    }

    private static int countWords(String line) {
        if (line == null || line.isBlank()) return 0;
        return line.trim().split("\\s+").length;
    }
}
