# goit-java-hw-9

Задание 1#
Дан текстовый файл file.txt, который содержит список номеров телефоном (один на линии). Необъодимо написать метод, который будет считывать файл и выводить в консоль все валидные номера телефонов.

Предполагаем, что "валидный" номер телефона - это строка в одном из двух форматов: (xxx) xxx-xxxx или xxx-xxx-xxxx (х обозначает цифру).
Пример:
Для файла file.txt со следующим содержанием:

987-123-4567
123 456 7890
(123) 456-7890

Метод должен вывести на экран

987-123-4567
(123) 456-7890


Задание 2#
Дан текстовый файл file.txt, необходимо считать файл в список объектов User и создать новый файл user.json.

Предполагаем, что каждая строка содержит одинаковое количество "колонок", разделенный пробелом.
Пример:
Для файла file.txt со следующим содержанием:

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


Задание 3#
Напишите метод, который будет подсчитывать частоту каждого слова в файле words.txt.

Предпалагаем, что:
words.txt содержит только слова в нижнем регистре, разделенные пробелом
Каждое слово содержит только символы-буквы в нижнем регистре.
Слова разделены одим или несколькими пробелами, либо переносом строки.
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
