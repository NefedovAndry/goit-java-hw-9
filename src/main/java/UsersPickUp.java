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

public class UsersPickUp {
    public static void main(String[] args) throws IOException {
        String filePath = "src/main/resources/file_hw9_2.txt";
        String jsonFilePath = "src/main/resources/jsonFile_hw9_2.json";
        File file = new File(filePath);
        if (!file.exists()) {
            throw new FileNotFoundException("File not found!");
        }
        String[] linesArray = readLinesFromFile(file);
        ArrayList<User> users = new ArrayList<>();
        if (linesArray != null) {
            createUsers(users, linesArray);
        }
        createJson(users, jsonFilePath);
    }

    public static String[] readLinesFromFile(File file) throws IOException {
        try (FileReader inputStream = new FileReader(file)) {
            char[] buffer = new char[1024];
            int fileLength = inputStream.read(buffer, 0, 1024);
            String bufferString = (new String(buffer, 0, fileLength)).strip();
            return bufferString.split("\\r\\n");
        } catch (IOException e) {
            e.getStackTrace();
        }
        return null;
    }

    public static void createUsers(ArrayList<User> users, String[] linesArray) throws NumberFormatException {
        for (int i = 1; i < linesArray.length; i++) {
            String[] buffer = linesArray[i].split(" ");
            String name = buffer[0];
            int age = Integer.parseInt(buffer[1]);
            users.add(new User(name, age));
        }
        users.trimToSize();
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
