import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public static void main(String[] args) {
        List<JournalEntry> journal = new ArrayList<>();

        while (true) {
            System.out.println("\n=== Меню ===");
            System.out.println("1. Додати запис");
            System.out.println("2. Показати всі записи");
            System.out.println("3. Вийти");
            System.out.print("Ваш вибір: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    journal.add(createEntry());
                    break;
                case "2":
                    if (journal.isEmpty()) {
                        System.out.println("Журнал порожній.");
                    } else {
                        System.out.println("\n=== Записи журналу ===");
                        journal.forEach(System.out::println);
                    }
                    break;
                case "3":
                    System.out.println("Вихід...");
                    return;
                default:
                    System.out.println("Неправильний вибір, спробуйте ще раз.");
            }
        }
    }

    private static JournalEntry createEntry() {
        String surname = readNonEmpty("Введіть прізвище: ");
        String name = readNonEmpty("Введіть ім'я: ");
        LocalDate birthDate = readDate("Введіть дату народження (dd.MM.yyyy): ");
        String phone = readPhone("Введіть телефон (наприклад: +380123456789): ");
        String address = readNonEmpty("Введіть адресу (вулиця, будинок, квартира): ");

        return new JournalEntry(surname, name, birthDate, phone, address);
    }

    private static String readNonEmpty(String prompt) {
        String input;
        do {
            System.out.print(prompt);
            input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Поле не може бути порожнім, спробуйте ще раз.");
            }
        } while (input.isEmpty());
        return input;
    }

    private static LocalDate readDate(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return LocalDate.parse(input, dateFormatter);
            } catch (DateTimeParseException e) {
                System.out.println("Неправильний формат дати! Використовуйте dd.MM.yyyy.");
            }
        }
    }

    private static String readPhone(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (input.matches("^\\+?\\d{10,13}$")) {
                return input;
            } else {
                System.out.println("Неправильний номер телефону! Використовуйте формат +380*********.");
            }
        }
    }
}
