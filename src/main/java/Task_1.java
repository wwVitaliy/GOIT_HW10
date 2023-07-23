import java.io.*;
import java.util.Scanner;
import java.util.regex.*;

/**
 * Завдання 1
 * Дано текстовий файл file.txt, в якому є список номерів телефонів (один рядок - один телефон).
 * Необхідно написати метод, який буде читати файл, і виводити в консоль всі валідні номери телефонів.
 * Телефон вважається валідним, якщо він відповідає одному з двох форматів (x - це одна цифра):
 * (xxx) xxx-xxxx
 * xxx-xxx-xxxx
 * <p>
 * Приклад:
 * Для файлу file.txt з наступним змістом:
 * 987-123-4567
 * 123 456 7890
 * (123) 456-7890
 * <p>
 * Метод повинен вивести на екран:
 * 987-123-4567
 * (123) 456-7890
 */
public class Task_1 {

    public static void main(String[] args) throws IOException {

        // Create file object
        String filePath = "Files/file.txt";
        File file = new File(filePath);

        // Create dir and file on local disc
//        File dir = new File(file.getParent());
//        dir.mkdir();
//        file.createNewFile();

        // Read file by lines
        try (Scanner scanner = new Scanner(new FileReader(file))) {
            while (scanner.hasNextLine()) {
                String lineFromFile = scanner.nextLine();

                // Validate line and print in console
                if (isValidNumber(lineFromFile)) {
                    System.out.println(lineFromFile);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("e.getMessage() = " + e.getMessage());
        }
    }

    // Checks if the number corresponds defined formats
    private static boolean isValidNumber(String number) {

        // Set the formats
        String regex1 = "\\(\\d{3}\\) \\d{3}-\\d{4}";    // (xxx) xxx-xxxx
        String regex2 = "\\d{3}-\\d{3}-\\d{4}";    // xxx-xxx-xxxx

        // Create patterns
        Pattern pattern1 = Pattern.compile(regex1);
        Pattern pattern2 = Pattern.compile(regex2);

        // Compare the number to each pattern
        boolean isNumberPattern1 = pattern1.matcher(number).matches();
        boolean isNumberPattern2 = pattern2.matcher(number).matches();

        return isNumberPattern1 || isNumberPattern2;
    }

}

