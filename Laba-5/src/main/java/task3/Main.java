package task3;

import java.io.*;
import java.util.*;
import java.util.logging.*;

public class Main {

    private static final Logger logger = Logger.getLogger(Main.class.getName());

    static {
        try {
            InputStream config = Main.class.getResourceAsStream("/logging.properties");
            if (config == null) {
                System.err.println("logging.properties NOT FOUND!");
            } else {
                LogManager.getLogManager().readConfiguration(config);
            }
        } catch (Exception e) {
            System.err.println("Failed to load logging config: " + e.getMessage());
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose language: 1 - English, 2 - Ukrainian");
        int lang = scanner.nextInt();
        scanner.nextLine();

        Locale locale = (lang == 2) ? new Locale("uk") : new Locale("en");
        ResourceBundle rb = ResourceBundle.getBundle("location.messages", locale);

        logger.info("Language selected: " + locale);

        System.out.print(rb.getString("menu.operation"));
        String operation = scanner.nextLine().trim().toUpperCase();

        System.out.print(rb.getString("menu.input"));
        String inputFile = scanner.nextLine().trim();
        if (inputFile.isEmpty())
            inputFile = "Laba-5/src/main/java/task3/files/input.txt";

        System.out.print(rb.getString("menu.output"));
        String outputFile = scanner.nextLine().trim();
        if (outputFile.isEmpty())
            outputFile = "output.txt";

        System.out.print(rb.getString("menu.key"));
        char key = scanner.nextLine().charAt(0);

        logger.fine("Operation: " + operation);
        logger.fine("Input: " + inputFile);
        logger.fine("Output: " + outputFile);

        try {
            switch (operation) {
                case "E" -> encrypt(inputFile, outputFile, key, rb);
                case "D" -> decrypt(inputFile, outputFile, key, rb);
                default -> {
                    logger.warning("Invalid operation!");
                    System.out.println(rb.getString("menu.invalid"));
                }
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "I/O Error", e);
            System.out.println(rb.getString("error") + " " + e.getMessage());
        }
    }

    private static void encrypt(String inputFile, String outputFile, char key, ResourceBundle rb) throws IOException {
        logger.info("Encrypting...");

        try (FileReader fr = new FileReader(inputFile);
             EncryptWriter ew = new EncryptWriter(new FileWriter(outputFile), key)) {

            int c;
            while ((c = fr.read()) != -1)
                ew.write(c);
        }

        logger.info("Encryption done");
        System.out.println(rb.getString("result.encrypted") + " " + outputFile);
    }

    private static void decrypt(String inputFile, String outputFile, char key, ResourceBundle rb) throws IOException {
        logger.info("Decrypting...");

        try (DecryptReader dr = new DecryptReader(new FileReader(inputFile), key);
             FileWriter fw = new FileWriter(outputFile)) {

            int c;
            while ((c = dr.read()) != -1)
                fw.write(c);
        }

        logger.info("Decryption done");
        System.out.println(rb.getString("result.decrypted") + " " + outputFile);
    }
}
