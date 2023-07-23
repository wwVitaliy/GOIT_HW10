import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Завдання 2
 * Є текстовий файл file.txt.
 * Необхідно прочитати файл, перетворити його в список об'єктів типу User, і записати їх у новий файл user.json.
 * Формат файлу:
 * - перший рядок - заголовок
 * - кожний наступний рядок - окремий об'єкт, кожна колонка розділена пробілом
 * Приклад:
 * Для файлу file.txt із вмістом:
 * name age
 * alice 21
 * ryan 30
 * необхідно створити наступний файл user.json:
 * [
 * {
 * "name": "alice",
 * "age":21
 * },
 * {
 * "name": "ryan",
 * "age":30
 * }
 * ]
 */

public class Task_2 {
    public static void main(String[] args) throws IOException {

        // Create file object
        String filePath = "Files/file.txt";
        File file = new File(filePath);

        // Create dir and file on local disc
        File dir = new File(file.getParent());
        dir.mkdir();
        file.createNewFile();

        // Create list of users
        LinkedList<User> users = new LinkedList<>();

        // Read data from file and create users
        try (Scanner scanner = new Scanner(new FileReader(file))) {
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }
            while (scanner.hasNextLine()) {
                User user = new User(scanner.next(), scanner.nextInt());
                users.add(user);
            }
        } catch (FileNotFoundException e) {
            System.out.println("e.getMessage() = " + e.getMessage());
        } catch (InputMismatchException e){
            System.out.println("Wrong data in the file");
        }

        // Create JSON file object
        File jsonFile = new File("Files/user.json");

        // Create JSON file on local disc
        jsonFile.createNewFile();

        // Write users list to JSON file
        try (FileWriter fw = new FileWriter(jsonFile)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(users);
            fw.write(json);
        }
    }
}


class User {
    private String name;
    private int age;

    public User() {
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}