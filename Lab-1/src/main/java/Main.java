import java.util.*;

//  5. Знайти слова, які складаються тільки з різних символів. На виході – масив String.

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введіть рядок зі словами:");
        String input = sc.nextLine();

        String[] words = input.split("\\s+");

        List<String> result = new ArrayList<>();

        for (String word : words) {
            if (hasAllUniqueChars(word)) {
                result.add(word);
            }
        }

        String[] output = result.toArray(new String[0]);

        System.out.println("Слова з різних символів:");
        System.out.println(Arrays.toString(output));
    }

    static boolean hasAllUniqueChars(String word) {
        Set<Character> chars = new HashSet<>();
        for (char c : word.toCharArray()) {
            if (!chars.add(c)) {
                return false;
            }
        }
        return true;
    }
}
