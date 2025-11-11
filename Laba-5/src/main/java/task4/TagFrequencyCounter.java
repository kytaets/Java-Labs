package task4;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.*;

public class TagFrequencyCounter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter URL (for example: https://example.com): ");
        String url = scanner.nextLine();

        try {
            Document doc = Jsoup.connect(url).get();

            Map<String, Integer> tagCount = new HashMap<>();

            for (Element element : doc.getAllElements()) {
                String tagName = element.tagName();
                tagCount.put(tagName, tagCount.getOrDefault(tagName, 0) + 1);
            }

            System.out.println("\n=== Sorting by alphabet ===");
            tagCount.entrySet().stream()
                    .sorted(Map.Entry.comparingByKey())
                    .forEach(entry ->
                            System.out.printf("%-15s -> %d%n", entry.getKey(), entry.getValue())
                    );

            System.out.println("\n=== Sorting by tags frequency ===");
            tagCount.entrySet().stream()
                    .sorted(Map.Entry.comparingByValue())
                    .forEach(entry ->
                            System.out.printf("%-15s -> %d%n", entry.getKey(), entry.getValue())
                    );

        } catch (IOException e) {
            System.out.println("Error connecting to URL: " + e.getMessage());
        }
    }
}
