package practicum;


import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static javax.swing.UIManager.put;


public class Algorithms {

    /**
     *
     * В задачах, в которых заранее не оговорен состав символов в строках,
     * строки могут содержать русские и английские буквы,
     * а также пробелы, знаки препинания, кавычки и скобки.
     *
     * Не использовать при решении регулярные выражения, методы стандартных библиотек
     * java.util.Collections, java.util.Arrays, java.lang.Math, а также методы
     * replace и replaceAll, reverce, equals, indexOf, toLowerCase, toUpperCase
     * split, substring из java.lang.String.
     * Можете использовать циклы, условные операторы,
     * простые типы данных и их обертки.
     *
     * Как изменится сложность ваших решений, если убрать
     * ограничения по использованию функций Java API?
     */

    /**
     * Вычислите максимальное, минимальное и среднее число для списка
     * Верните их суммучисел
     * Список гарантированно содежит элементы
     */
    public static double maxMinAvr(List<Integer> numbers) {
        double[] numArray = new double[numbers.size()];
        for (int i = 0; i < numbers.size(); i++) {
            numArray[i] = numbers.get(i);
        }
        double max = numArray[0];
        double min = numArray[0];
        double sum = numArray[0];
        for (int i = 1; i < numArray.length; i++) {
            if (numArray[i] > max)
                max = numArray[i];
            if (numArray[i] < min) {
                min = numArray[i];
            }
            sum += numArray[i];
        }
        return max + min + sum / numArray.length;
    }


    /**
     * Найдите второе максимальное значение в массиве,
     * если такого нет, то вернуть первое
     * Массив гарантировано содержит элементы
     */
    public static Integer max2(List<Integer> list) {
        if (list.size() == 1) {
            return list.get(0);
        }
        Integer[] numArray = new Integer[list.size()];
        for (int i = 0; i < list.size(); i++) {
            numArray[i] = list.get(i);
        }
        int temp;
        for (int i = 0; i < numArray.length - 1; i++) {
            for (int j = 0; j < numArray.length - 1; j++) {
                if (numArray[j] < numArray[j + 1]) {
                    temp = numArray[j];
                    numArray[j] = numArray[j + 1];
                    numArray[j + 1] = temp;
                }
            }
        }
        int max2 = 0;
        for (int i = 0; i < numArray.length - 1; i++) {
            if (numArray[i + 1] < numArray[i]) {
                max2 = numArray[i + 1];
                break;
            } else max2 = numArray[i];
        }
        return max2;
    }


    /**
     * Удалите число из массива.
     * Верните массив не содержащий этого элемента,
     * но и не содержащий "пропусков" на месте удаленных элементов
     * Например, если из массива [0, 6, 0 ,5, 0] нужно удалить элемент 0,
     * то возвращаться должен массив содержащий два элемента [6, 5]
     */
    public static int[] removeElementFromArray(int[] numbers, int value) {
        int count1 = 0;
        for (int number : numbers) {
            if (number != value) {
                count1++;
            }
        }
        int[] newArray = new int[count1];
        int count2 = 0;
        for (int number : numbers) {
            if (number != value) {
                newArray[count2] = number;
                count2++;
            }
        }
        return newArray;
    }


    /**
     * Удалите все гласные из строки.
     * Например, "мАма Мыла раму" -> "мм Мл рм"
     */
    public static String removeVowels(String str) {

        char[] vowels = {'а', 'А', 'у', 'У', 'е', 'Е', 'ё', 'Ё', 'ы', 'Ы', 'о', 'О', 'э', 'Э', 'я', 'Я', 'и', 'И', 'ю', 'Ю',
                'a', 'A', 'e', 'E', 'i', 'I', 'o', 'O', 'u', 'U'};
        char[] string = str.toCharArray();
        char temp;
        int countRemoves = 0;
        for (int i = 0; i < string.length; i++) {
            for (int k = 0; k < vowels.length; k++) {
                if (string[i] == vowels[k]) {
                    string[i] = '-';
                    countRemoves++;
                }
            }
        }
        int resultArrLength = string.length - countRemoves;
        char[] resultChar = new char[resultArrLength];
        int count = 0;
        for (char c : string) {
            if (c != '-') {
                resultChar[count] = c;
                count++;
            }
        }
        return new String(resultChar);
    }


    public static char[] getAlphabet() {
        char[] alphabet = new char[1105];
        for (char ch = 'А'; ch <= 'Я'; ++ch) {
            alphabet[ch] = (char) (ch + 32);
        }
        for (char ch = 'а'; ch <= 'я'; ++ch) {
            alphabet[ch] = ch;
        }
        for (char ch2 = 'A'; ch2 <= 'Z'; ++ch2) {
            alphabet[ch2] = (char) (ch2 + 32);
        }
        for (char ch2 = 'a'; ch2 <= 'z'; ++ch2) {
            alphabet[ch2] = ch2;
        }
        for (char ch2 = ' '; ch2 <= '@'; ++ch2) {
            alphabet[ch2] = ch2;
        }
        return alphabet;
    }

    /**
     * Убрать повторяющиеся подряд символы в строке
     * например "ммммоолллокко" -> "молоко"
     * (*) - в этой задаче нужно учитывать сочетания
     * повторяющихся букв разного регистра,
     * при этом в выходной строке остается первая буква,
     * например, "мМммооЛллокКОоо" -> "моЛокО",
     */


    public static String removeDublicates(String str) {

        char[] alphabet = getAlphabet();
        char[] string = str.toCharArray();
        if (string.length < 2)
            return String.copyValueOf(string);
        char ch1;
        char ch2;
        String finalStr = "";
        for (int j = 0; j < string.length; j++) {

            if (j == string.length-1){
                finalStr += string[j];
                break;
            }
            ch1 = alphabet[string[j]];
            ch2 = alphabet[string[j+1]];
            if (ch1 == ch2) {
                string[j+1] = string[j];
            } else {
                finalStr += string[j];
            }
        }
        return finalStr;
    }

    /**
     * Сжать строку, удаляя повторяющиеся символы
     * и указывая количество повторов для каждого символа
     * например "мооолооооккооо" -> "м1о3л1о4к2о2"
     */
    public static String zipStr(String str) {
        char[] string = str.toCharArray();
        int count = 1;
        String finalStr = "";
        for (int j = 0; j < string.length; j++) {
            if (j == string.length - 1) {
                finalStr += string[j] + String.valueOf(count);
                break;
            }
            if (string[j]== string[j + 1]) {
                string[j + 1] = string[j];
                count++;
            } else {
                if (count > 1) {
                    finalStr += (string[j] + String.valueOf(count));
                    count = 1;
                } else {
                    finalStr += (string[j] + String.valueOf(count));
                }
            }
        }
        return finalStr;
    }


    /**
     * Выяснить является ли строка палиндромом,
     * то есть  одинаково читается в обоих направлениях.
     * Например, слово "топот" - палиндром, а слово "топор" нет.
     * Строка "А роза упала на лапу Азора" тоже палиндром,
     * а строка "А роза уколола лапу Азора" нет.
     * "A man, a plan, a canal-Panama", тоже палиндром
     *
     * (!) Так как запрещены регулярные выражения
     * и методы преобразования регистра символов из java.lang.String
     * обратите внимание таблицу кодов символов UTF-8
     * (лучше убрать эту подсказку и выдать ее в процессе)
     */

    // в тесте замениля длинное тире на короткое
    public static boolean isPalindrom(String str) {
        if (str == null || str.length() <= 1) {
            return true;
        }
        char[] alphabet = getAlphabet();
        char ch1;
        char ch2;
        for (int i = 0, j = str.length() - 1; i < j; ++i, --j) {
            ch1 = alphabet[str.charAt(i)];
            ch2 = alphabet[str.charAt(j)];
            if (ch1 >= 32 && ch1 <= 63) {
                j++;
                continue;
            }
            if (ch2 >= 32 && ch2 <= 63) {
                i--;
                continue;
            }
            if (ch1 != ch2) {
                return false;
            }
        }
        return true;
    }


    /**
     * Перевернуть все слова в предложении
     * "Кот лакал молоко" -> "тоК лакал околом"
     */
    public static void reverse (char [] ch, int left, int right){
        while (left <= right) {
            char temp = ch[right];
            ch[right] = ch[left];
            ch[left] = temp;
            left++;
            right--;
        }
    }

    public static String reverseWordsInSentence(String sentence) {
        char[] string = sentence.toCharArray();
            int beg = 0;
            reverse(string, 0, string.length - 1);
            for (int i = 0; i < string.length; i++) {
                if (string[i] >= 32  && string[i] <= 63) {
                    reverse(string, beg, i-1);
                    beg = i + 1;
                }
            }
            reverse(string, beg, string.length - 1);
            reverse(string, 0, string.length - 1);

        return new String(string);
    }

    /**
     * Отсортируйте символы в массиве,
     * не используйте дополнительные структуры данных.
     * При вводе массива символов {'c', 'a', 'b'},
     * возвращаться должен тот же отсортированный массив {'a', 'b', 'c'}
     */
    public static char[] sortSymbols(char[] symbols) {
        char temp;
        for (int i = 0; i < symbols.length-1; i++) {
            for (int j = i + 1; j < symbols.length; j++) {
                if (symbols[j] < symbols[i]) {
                    temp = symbols[i];
                    symbols[i] = symbols[j];
                    symbols[j] = temp;
                }
            }
        }
        return symbols;
    }



    /**
     *
     * Выясните являются ли две строки анограммами.
     * Строки являются анограммами, если они состоят из одних и тех же букв
     * Например, слова "кот" и "ток" анограммы, а слова "кот" и  "кит" нет.
    */
    //без учета регистра
    public static boolean isAnogramOf(String word, String anogram) {
        if (word.length() != anogram.length()){
            return false;
        }
        for (int i = 0; i < word.length(); i++) {
            int count = 0;
            for (int j = 0; j < anogram.length(); j++) {
                if (word.charAt(i) == anogram.charAt(j)) {
                    count++;
                    break;
                }
                if (j == anogram.length() - 1 && count == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
    * Выясните, все ли символы в строке встречаются один раз.
     * Если строка содержит повторябщиеся символы,
     * то возвращать false, если не содержит - true
     * Нельзя использовать дополнительные структуры данных.
     *
     * (!) В этой задаче строка может содержать
     * любой символ из таблицы ASCII (127 символов)
     *
     * (!!) Сложность - O(n)
    * */

    public static boolean hasUniqueChars(String str) {
        int [] count = new int[1103];
        for (int i = 0; i < str.length(); i++) {
            int n = str.charAt(i)-1;
            count[n]++;

            if (count[n] > 1) {
                return false;
            }
        }
        return true;
    }
}
