import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Завдання 3
 * Напишіть метод, який буде рахувати частоту кожного слова у файлі words.txt.
 * Вважаємо що:
 * - words.txt містить лише слова в нижньому регістрі, розділені пробілом
 * - Кожне слово містить лише літери в нижньому регістрі
 * - Слова розділені одним або декількома пробілами, або переносом рядка
 * Приклад:
 * Для файлу words.txt із вмістом:
 * the day is sunny the the
 * the sunny is is
 * Метод повинен повернути результат на кшталт:
 * the 4
 * is 3
 * sunny 2
 * day 1
 */

public class Task_3 {
    public static void main(String[] args) {

        // Create file object
        String filePath = "Files/words.txt";
        File file = new File(filePath);

        // Create HashMap with words and frequency
        Map<String, Integer> wordFreq = new HashMap<>();

        // Read words from file and add them to Hashmap.
        try (Scanner scanner = new Scanner(new FileReader(file))) {
            while (scanner.hasNext()) {
                String wordFromText = scanner.next();
                int value;
                if (wordFreq.get(wordFromText) == null) {
                    value = 1;
                } else {
                    value = wordFreq.get(wordFromText) + 1;
                }
                wordFreq.put(wordFromText, value);
            }
        } catch (FileNotFoundException e) {
            System.out.println("e.getMessage() = " + e.getMessage());
        }

        // Print words in descending order according to frequency
        while (!wordFreq.isEmpty()) {

            // Find entry with max value, print it to console and remove from HashMap
            String maxKey = null;
            for (String key : wordFreq.keySet()) {
                if (maxKey == null || wordFreq.get(key) > wordFreq.get(maxKey)) {
                    maxKey = key;
                }
            }
            System.out.println(maxKey + " " + wordFreq.get(maxKey));
            wordFreq.remove(maxKey);
        }
    }

}
