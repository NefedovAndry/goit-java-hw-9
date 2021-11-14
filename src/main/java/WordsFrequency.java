/*Задание 3#
Напишите метод, который будет подсчитывать частоту каждого слова в файле words.txt.

Предполагаем, что:
1) words.txt содержит только слова в нижнем регистре, разделенные пробелом;
2) каждое слово содержит только символы-буквы в нижнем регистре;
3) слова разделены одним или несколькими пробелами, либо переносом строки.

Пример:
Для файла words.txt со следующим содержанием:

the day is sunny the the

the sunny is is

Метод должен вернуть частоту:

the 4
is 3
sunny 2
day 1

Обратите внимание! Вывод на консоль должен быть отсортирован на частоте слов (от наибольшей к наименьшей)
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class WordsFrequency {
    public static void main(String[] args) throws IOException {
        String filePath = "src/main/resources/words.txt";
        printWordsFrequency(filePath);
    }

    private static void printWordsFrequency(String filePath) throws IOException {
        String[] words = readLinesFromFile(filePath);
        Map<String, Integer> unsortedMap = equalWordsCount(words);
        Map<String, Integer> sortedByValueMap = sortByComparator(unsortedMap, false);
        printMap(sortedByValueMap);
    }

    public static String[] readLinesFromFile(String filePath) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new FileNotFoundException("File not found!");
        }
        try (FileReader inputStream = new FileReader(file)) {
            char[] buffer = new char[1024];
            int fileLength = inputStream.read(buffer, 0, 1024);
            String bufferString = (new String(buffer, 0, fileLength)).strip();
            String[] bufferWordsArray = bufferString.split("\\s");
            return removeEmptyElements(bufferWordsArray);
        } catch (IOException e) {
            e.getStackTrace();
        }
        return null;
    }

    public static String[] removeEmptyElements(String[] array) {
        int notEmptyIndex = 0;
        for (String element : array) {
            if (!element.isBlank()) {
                notEmptyIndex++;
            }
        }
        String[] elements = new String[notEmptyIndex];
        int index = 0;
        for (String element : array) {
            if (!element.isBlank()) {
                elements[index++] = element;
            }
        }
        return elements;
    }

    public static HashMap<String, Integer> equalWordsCount(String[] words) {
        ArrayList<String> wordsList = new ArrayList<>(List.of(words));
        wordsList.sort(Comparator.naturalOrder());
        HashMap<String, Integer> result = new HashMap<>();
        int count = 0;
        String comparingWord = wordsList.get(0);
        for (String word : wordsList) {
            if (comparingWord.equals(word)) {
                count++;
            } else {
                result.put(comparingWord, count);
                comparingWord = word;
                count = 1;
            }
        }
        result.put(comparingWord, count);
        return result;
    }

    private static Map<String, Integer> sortByComparator(Map<String, Integer> unsortMap, boolean order) {
        List<Entry<String, Integer>> list = new LinkedList<Entry<String, Integer>>(unsortMap.entrySet());
        // Sorting the list based on values
        Collections.sort(list, new Comparator<Entry<String, Integer>>() {
            public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
                if (order) {
                    return o1.getValue().compareTo(o2.getValue());
                }
                else {
                    return o2.getValue().compareTo(o1.getValue());
                }
            }
        });

        // Maintaining insertion order with the help of LinkedList
        Map<String, Integer> sortedMap = new LinkedHashMap<>();
        for (Entry<String, Integer> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }

    public static void printMap(Map<String, Integer> map) {
        for (Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " : "+ entry.getValue());
        }
    }
}
