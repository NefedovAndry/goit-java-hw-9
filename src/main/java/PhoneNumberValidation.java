/*Дан текстовый файл file_hw9_1.txt, который содержит список номеров телефонов (один на линии).
Необходимо написать метод, который будет считывать файл и выводить в консоль все валидные номера телефонов.

Предполагаем, что "валидный" номер телефона - это строка в одном из двух форматов: (xxx) xxx-xxxx или xxx-xxx-xxxx
(х обозначает цифру).*/

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class PhoneNumberValidation {

    public static final int ONE_KILOBYTE = 1024;
    public static final String LOG_FILE_PATH = "src/main/resources/log_file_hw9_1.txt";

    public static void main(String[] args) throws IOException {
        try {
            String filePath = "src/main/resources/file_hw9_1.txt";
            printValidatedPhoneNumbers(filePath);
            logging("Success!");
        } catch (IOException e) {
            logging(e.getMessage() + "\r\n" + Arrays.toString(e.getStackTrace()));
            System.err.println(e.getMessage());
            System.err.println(Arrays.toString(e.getStackTrace()));
        }
    }

    private static void logging(String message) throws IOException {
        PrintWriter logging = new PrintWriter(new FileWriter(PhoneNumberValidation.LOG_FILE_PATH));
        logging.println("OS: " + System.getProperty("os.name"));
        logging.println("Java version: " + System.getProperty("java.version"));
        logging.println("File separator: " + System.getProperty("file.separator"));
        logging.println("User home directory: " + System.getProperty("user.home"));
        logging.println();
        logging.println(message);
        logging.flush();
        logging.close();
    }

    public static void printValidatedPhoneNumbers(String filePath) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new IOException("File \"" + file.getName() + "\" in path \"" + file.getParent() + "\" not found!");
        }
        String[] inputStringArray = readFromFile(file);
        String[] phoneNumbersArray = getPhoneNumbers(inputStringArray);
        String[] validatedPhoneNumbers = getValidatedPhoneNumbers(phoneNumbersArray);
        System.out.println(Arrays.toString(validatedPhoneNumbers));
    }

    public static String[] readFromFile(File file) throws IOException {
        try (FileReader inputStream = new FileReader(file)) {
            char[] buffer = new char[ONE_KILOBYTE];
            int fileLength = inputStream.read(buffer, 0, ONE_KILOBYTE);
            String bufferString = (new String(buffer, 0, fileLength)).strip();
            return bufferString.split("\\r\\n");
        } catch (IOException e) {
            e.getStackTrace();
        }
        return null;
    }

    public static String[] getPhoneNumbers(String[] inputStringArray) {
        if (inputStringArray != null) {
            int numberOfNotEmptyElements = 0;
            for (String s : inputStringArray) {
                if (!s.isBlank()) {
                    numberOfNotEmptyElements++;
                }
            }
            String[] phoneNumbersArray = new String[numberOfNotEmptyElements];
            for (int i = 0, j = 0; i < inputStringArray.length; i++) {
                if (!inputStringArray[i].isBlank()) {
                    phoneNumbersArray[j++] = inputStringArray[i].trim();
                }
            }
            return phoneNumbersArray;
        } else {
            return null;
        }
    }

    public static String[] getValidatedPhoneNumbers(String[] phoneNumbersArray) {
        if (phoneNumbersArray != null) {
            ArrayList<String> validatedPhoneNumbers = new ArrayList<>();
            for (String phoneNumber : phoneNumbersArray) {
                if (phoneNumber.matches("\\d{3}-\\d{3}-\\d{4}") ||
                        phoneNumber.matches("\\(\\d{3}\\) \\d{3}-\\d{4}")) {
                    validatedPhoneNumbers.add(phoneNumber);
                }
            }
            return validatedPhoneNumbers.toArray(new String[0]);
        } else {
            return null;
        }
    }
}
