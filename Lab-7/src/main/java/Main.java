import java.util.*;

public class Main {
    public static void main(String[] args) {

        InputReader inputReader = new InputReader();
        WordPrinter printer = new WordPrinter();

        String input = inputReader.readLine("Введіть рядок зі словами:");

        String[] uniqueWords = Arrays.stream(input.split("\\s+"))
                .filter(word -> word.chars().distinct().count() == word.length())
                .toArray(String[]::new);

        printer.printUniqueWords(uniqueWords);
    }
}
