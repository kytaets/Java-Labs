public class WordPrinter {

    public void printUniqueWords(String[] words) {
        System.out.println("Слова, що містять лише унікальні символи");

        if (words.length == 0) {
            System.out.println("Немає слів, які складаються з різних символів.");
        } else {
            for (int i = 0; i < words.length; i++) {
                System.out.printf("%d) %s%n", i + 1, words[i]);
            }
        }

    }
}
