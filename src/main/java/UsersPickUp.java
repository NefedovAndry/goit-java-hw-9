/*Задание 2#
Дан текстовый файл file.txt, необходимо считать файл в список объектов User и создать новый файл user.json.

Предполагаем, что каждая строка содержит одинаковое количество "колонок", разделенных пробелом.
Пример: Для файла file.txt со следующим содержанием:

name age
alice 21
ryan 30

Новый файл должен иметь следующий вид:

[
 {
  "name": "alice",
  "age":21
 },
 {
  "name": "ryan",
  "age":30
 }
]

*/

import com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class UsersPickUp {

    public static final int ONE_KILOBYTE = 1024;
    public static final String LOG_FILE_PATH = "src/main/resources/log_file_hw9_2.txt";

    public static void main(String[] args) throws IOException {
        try {
            String filePath = "src/main/resources/file_hw9_2.txt";
            String jsonFilePath = "src/main/resources/jsonFile_hw9_2.json";
            ArrayList<User> users = new ArrayList<>();
            String[] linesArray = readLinesFromFile(filePath);
            createUsers(users, linesArray);
            createJson(users, jsonFilePath);
            logging("Success!");
        } catch (IOException | RuntimeException e) {
            logging(e.getMessage() + "\r\n" + Arrays.toString(e.getStackTrace()));
            System.err.println(e.getMessage());
            System.err.println(Arrays.toString(e.getStackTrace()));
        }

    }

    private static void logging(String message) throws IOException {
        PrintWriter logging = new PrintWriter(new FileWriter(LOG_FILE_PATH));
        logging.println("OS: " + System.getProperty("os.name"));
        logging.println("Java version: " + System.getProperty("java.version"));
        logging.println("File separator: " + System.getProperty("file.separator"));
        logging.println("User home directory: " + System.getProperty("user.home"));
        logging.println();
        logging.println(message);
        logging.flush();
        logging.close();
    }

    public static String[] readLinesFromFile(String filePath) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new IOException("File \"" + file.getName() + "\" in path \"" + file.getParent() + "\" not found!");
        }
        try (FileReader inputStream = new FileReader(file)) {
            char[] buffer = new char[ONE_KILOBYTE];
            int fileLength = inputStream.read(buffer, 0, ONE_KILOBYTE);
            String bufferString = (new String(buffer, 0, fileLength)).strip();
            return bufferString.split("\\r\\n");
        }
    }

    public static void createUsers(ArrayList<User> users, String[] linesArray) throws RuntimeException {
        try {
            for (int i = 1; i < linesArray.length; i++) {
                String[] buffer = linesArray[i].split(" ");
                String name = buffer[0];
                int age = Integer.parseInt(buffer[1]);
                users.add(new User(name, age));
            }
            users.trimToSize();
        } catch (RuntimeException e) {
            throw new IllegalStateException("Array \"" + Arrays.toString(linesArray) + "\" has wrong ages!");
        }
    }

    public static void createJson(ArrayList<User> users, String jsonFilePath) throws IOException {
        Gson gson = new Gson();
        String outputString = gson.toJson(users);
        try (FileWriter output = new FileWriter(jsonFilePath)) {
            output.write(outputString);
        }
    }
}

class User {
    private final String name;
    private final int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
