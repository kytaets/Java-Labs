import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Scanner;

public class ReflectionStringDemo {

    private static void changeStringValue(String str, String newValue) throws Exception {

        // Доступ до поля private byte[] value
        Field valueField = String.class.getDeclaredField("value");
        valueField.setAccessible(true);

        // Доступ до поля private byte coder
        Field coderField = String.class.getDeclaredField("coder");
        coderField.setAccessible(true);

        // coder: 0 = LATIN1, 1 = UTF-16
        byte coder = coderField.getByte(str);

        // Поточний внутрішній масив
        byte[] oldValue = (byte[]) valueField.get(str);

        // Генеруємо байти для нового рядка відповідно до coder
        byte[] newBytes;

        if (coder == 0) {
            // LATIN1
            newBytes = newValue.getBytes("ISO-8859-1");
        } else {
            // UTF-16 big endian
            newBytes = newValue.getBytes("UTF-16BE");
        }

        // Перевірка довжини
        if (newBytes.length > oldValue.length) {
            throw new IllegalArgumentException("Нове значення довше за старе, заміна неможлива.");
        }

        // Копіюємо в існуючий масив
        System.arraycopy(newBytes, 0, oldValue, 0, newBytes.length);

        // Решта — пробіли
        Arrays.fill(oldValue, newBytes.length, oldValue.length, (byte)' ');
    }


    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);

        System.out.println("=== Робота з рядковим літералом ===");
        String literal = "Hello!";
        System.out.println("До зміни: " + literal);

        changeStringValue(literal, "Hi!!!");
        System.out.println("Після зміни: " + literal);


        System.out.println("\n=== З клавіатури ===");
        System.out.print("Введіть рядок: ");
        String input = sc.nextLine();
        System.out.println("До зміни: " + input);

        System.out.print("Нове значення: ");
        String newVal = sc.nextLine();

        changeStringValue(input, newVal);
        System.out.println("Після зміни: " + input);
    }
}
