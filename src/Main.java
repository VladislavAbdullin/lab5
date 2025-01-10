import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.Map;
import java.util.Set;
import java.util.*;
import java.util.TreeSet;


public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Выберите задание (1 (1.1), 2 (3.1), 3 (4.1), 4 (5.1), 5 (6.1), 6 (7.2)):");
        int v = 0;
        boolean validInput = false;
        while (!validInput) {
            try {
                v = scanner.nextInt();
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Пожалуйста, введите корректное целое число:");
                scanner.next(); // Очистка неверного ввода
            }
        }
        scanner.nextLine();
        switch (v) {
            case 1:
                // Создаем несколько дробей
                Drob drob1 = new Drob(3, 4);  // 3/4
                Drob drob2 = new Drob(5, -10);  //обработка отриц чисел
                Drob drob3 = new Drob(6, 3);  // -2/1
                Drob drob4 = new Drob(6, 3);  // -2/1
                // Выводим дроби и их вещественные значения
                System.out.println("Дробь 1: " + drob1 + ", Значение: " + drob1.getValue());
                System.out.println("Дробь 2: " + drob2 + ", Значение: " + drob2.getValue());
                System.out.println("Дробь 3: " + drob3 + ", Значение: " + drob3.getValue());

                // Проверяем, равны ли дроби
                System.out.println("Дробь 1 и Дробь 2 равны: " + drob1.equals(drob2));  // false
                System.out.println("Дробь 2 и Дробь 3 равны: " + drob2.equals(drob3));
                System.out.println("Дробь 2 и Дробь 3 равны: " + drob3.equals(drob4));  // true
                // Изменяем числитель и знаменатель дроби
                drob1.setNumerator(7);
                drob1.setDenominator(14);
                System.out.println("После изменения: " + drob1 + ", Значение: " + drob1.getValue());
                break;
            case 2:
                List<String> list = new ArrayList<>();

                // Вводим количество элементов в списке
                System.out.print("Введите количество элементов в списке: ");
                int n = scanner.nextInt();
                scanner.nextLine(); // Очистка символа новой строки после ввода числа

                // Вводим элементы списка
                for (int i = 0; i < n; i++) {
                    System.out.print("Введите элемент " + (i + 1) + ": ");
                    String element = scanner.nextLine();
                    list.add(element);
                }

                // Выводим текущий список
                System.out.println("Список до удаления: " + list);

                // Выбор метода удаления
                System.out.println("Выберите способ удаления:");
                System.out.println("1. Удалить по номеру элемента");
                System.out.println("2. Удалить все элементы по значению");

                int choice = scanner.nextInt();
                scanner.nextLine(); // Очистка символа новой строки после ввода числа

                switch (choice) {
                    case 1:
                        // Удаление по номеру элемента
                        System.out.print("Введите номер элемента для удаления  ");
                        int index = scanner.nextInt() - 1;  // Преобразуем в индекс с нуля
                        if (index >= 0 && index < list.size()) {
                            list.remove(index);
                            System.out.println("Список после удаления элемента по индексу: " + list);
                        } else {
                            System.out.println("Неверный номер элемента.");
                        }
                        break;

                    case 2:
                        // Удаление всех элементов по значению
                        System.out.print("Введите элемент для удаления: ");
                        String valueToRemove = scanner.nextLine();
                        list.removeIf(element -> element.equals(valueToRemove));
                        System.out.println("Список после удаления элементов по значению: " + list);
                        break;

                    default:
                        System.out.println("Некорректный выбор.");
                }
                break;
            case 3:

                String filePath = "C:\\Users\\Vladislav\\IdeaProjects\\lab5\\src\\students.txt";

                Map<String, Integer> surnameCount = new HashMap<>();

                try {
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
                    List<String> logins = new ArrayList<>();

                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        line = line.trim();
                        if (line.isEmpty());

                        String[] parts = line.split(" ");
                        if (parts.length < 2) {
                            System.out.println("Ошибка: строка \"" + line + "\" имеет некорректный формат.");

                        }

                        String surname = parts[0];

                        // Формирование логина
                        if (!surnameCount.containsKey(surname)) {
                            logins.add(surname); // Если фамилия новая
                            surnameCount.put(surname, 2);
                        } else {
                            int count = surnameCount.get(surname);
                            logins.add(surname + count); // Если фамилия уже встречалась
                            surnameCount.put(surname, count + 1);
                        }
                    }

                    // Вывод сформированных логинов
                    System.out.println("Сформированные логины:");
                    for (String login : logins) {
                        System.out.println(login);
                    }

                    bufferedReader.close();
                } catch (FileNotFoundException e) {
                    System.out.println("Ошибка: файл не найден. Проверьте путь к файлу.");
                } catch (IOException e) {
                    System.out.println("Ошибка при чтении файла: " + e.getMessage());
                } catch (Exception e) {
                    System.out.println("Произошла ошибка: " + e.getMessage());
                }
              break;
            case 4:
                String voicedConsonants = "бвгджзйлмнр";
                Set<Character> foundConsonants = new TreeSet<>();

                try (BufferedReader bufferedReader = new BufferedReader(new FileReader("C:\\Users\\Vladislav\\IdeaProjects\\lab5\\src\\text2.txt"))) {
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        for (char ch : line.toCharArray()) {
                            if (voicedConsonants.indexOf(ch) != -1) {
                                foundConsonants.add(ch);
                            }
                        }
                    }
                } catch (FileNotFoundException e) {
                    System.out.println("Ошибка: файл не найден. Проверьте путь к файлу.");
                } catch (IOException e) {
                    System.out.println("Ошибка при чтении файла: " + e.getMessage());
                }

                // Вывод результата
                System.out.println("Звонкие согласные буквы в алфавитном порядке: " + String.join(" ", foundConsonants.stream().map(String::valueOf).toList()));
                break;
            case 5:
                // Очередь для хранения элементов
                Queue<String> och = new LinkedList<>(); //используем для реализации класс LinkedList

                try {
                    System.out.print("Введите количество элементов очереди: ");
                    int n8 = scanner.nextInt();
                    scanner.nextLine(); // Очистка буфера ввода

                    if (n8 <= 0) {
                        System.out.println("Ошибка: количество элементов должно быть положительным числом.");
                        return;
                    }

                    System.out.println("Введите элементы очереди (слова, буквы или цифры):");
                    for (int i = 0; i < n8; i++) {
                        String element = scanner.nextLine().trim();

                        // Проверка: элемент не должен быть пустым
                        if (element.isEmpty()) {
                            System.out.println("Ошибка: элемент не может быть пустой строкой.");
                            return;
                        }

                        // Проверка: элемент может содержать только буквы или цифры
                        if (!element.matches("[a-zA-Zа-яА-Я0-9]+")) {
                            System.out.println("Ошибка: элемент должен содержать только буквы или цифры.");
                            return;
                        }

                        och.add(element);
                    }

                    if (och.isEmpty()) {
                        System.out.println("Очередь пуста.");
                        return;
                    }

                    System.out.println("Элементы очереди в обратном порядке:");

                    // Вывод элементов в обратном порядке без стека
                    String[] array = och.toArray(new String[0]); // Преобразуем очередь в массив
                    for (int i = array.length - 1; i >= 0; i--) {
                        System.out.println(array[i]);
                    }

                } catch (InputMismatchException e) {
                    System.out.println("Ошибка: введите корректное целое число.");
                }
                break;
            case 6:
                try {
                    BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Vladislav\\IdeaProjects\\lab5\\src\\text3.txt"));

                    Map<Integer, List<String>> groupedByNumber = new HashMap<>();

                    String line;
                    while ((line = reader.readLine()) != null) {
                        // Убираем лишние пробелы
                        line = line.trim();

                        int colonIndex = line.indexOf(":");

                        // Пропускаем строки без двоеточия
                        if (colonIndex == -1) {
                            continue;
                        }

                        // Извлекаем имя и номер
                        String name = line.substring(0, colonIndex).trim();
                        String numberPart = line.substring(colonIndex + 1).trim();

                        // Пропускаем строки без номера
                        if (numberPart.isEmpty()) {
                            continue;
                        }

                        // Преобразуем номер в целое число
                        int number;
                        try {
                            number = Integer.parseInt(numberPart);
                        } catch (NumberFormatException e) {
                            continue; // Пропускаем строки с некорректным номером
                        }

                        // Приводим имя к виду: первая буква заглавная, остальные строчные
                        if (!name.isEmpty()) {
                            name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
                        } else {
                            continue; // Пропускаем пустые имена
                        }

                        // Добавляем в список для текущего номера
                        groupedByNumber.putIfAbsent(number, new ArrayList<>());
                        groupedByNumber.get(number).add(name);
                    }

                    reader.close();

                    // Вывод результата
                    System.out.println("Результат группировки:");
                    for (Map.Entry<Integer, List<String>> entry : groupedByNumber.entrySet()) {
                        System.out.print(entry.getKey() + ": [");
                        for (int i = 0; i < entry.getValue().size(); i++) {
                            System.out.print(entry.getValue().get(i));
                            if (i < entry.getValue().size() - 1) {
                                System.out.print(", ");
                            }
                        }
                        System.out.println("]");
                    }

                } catch (FileNotFoundException e) {
                    System.out.println("Ошибка: файл не найден. Проверьте путь к файлу.");
                } catch (IOException e) {
                    System.out.println("Ошибка при чтении файла: " + e.getMessage());
                } catch (Exception e) {
                    System.out.println("Произошла непредвиденная ошибка: " + e.getMessage());
                }
                break;
            default:
                System.out.println("Неверный выбор. Пожалуйста, выберите задание (1 (1.1), 2 (3.1), 3 (4.1), 4 (5.1),5 (6.1) 6 (7.2))");
                break;
        }



    }


}
