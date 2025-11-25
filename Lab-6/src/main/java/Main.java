import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SimpleTranslator translator = new SimpleTranslator();

        translator.addWord("hello", "привіт");
        translator.addWord("world", "світ");
        translator.addWord("is", "є");
        translator.addWord("cat", "кіт");
        translator.addWord("dog", "собака");
        translator.addWord("my", "мій");
        translator.addWord("name", "ім'я");
        translator.addWord("i", "я");
        translator.addWord("love", "люблю");
        translator.addWord("java", "джава");

        System.out.println("--- Простий Перекладач ---");

        boolean isRunning = true;

        while (isRunning) {
            System.out.println("\n============== МЕНЮ ==============");
            System.out.println("1. Перекласти фразу");
            System.out.println("2. Додати слово до словника");
            System.out.println("3. Вийти з програми");
            System.out.print("Ваш вибір: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.println("--> Введіть фразу англійською мовою:");
                    String phrase = scanner.nextLine();
                    String translation = translator.translatePhrase(phrase);
                    System.out.println("ПЕРЕКЛАД: " + translation);
                    break;

                case "2":
                    System.out.print("--> Введіть слово англійською: ");
                    String en = scanner.nextLine();
                    System.out.print("--> Введіть переклад українською: ");
                    String ua = scanner.nextLine();
                    translator.addWord(en, ua);
                    System.out.println("✅ Слово успішно додано!");
                    break;

                case "3":
                case "0":
                case "exit":
                    System.out.println("Завершення роботи. До побачення!");
                    isRunning = false;
                    break;

                default:
                    System.out.println("❌ Невідома команда. Будь ласка, оберіть 1, 2 або 3.");
            }
        }

        scanner.close();
    }
}