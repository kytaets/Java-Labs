import java.util.Scanner;

public class InputReader {
    private final Scanner scanner = new Scanner(System.in);
    public String readLine(String message) {
        System.out.println(message);
        return scanner.nextLine();
    }
}
