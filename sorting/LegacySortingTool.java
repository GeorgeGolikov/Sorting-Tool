package sorting;

import java.util.Scanner;

@Deprecated
public class LegacySortingTool {
    private static void processLongs() {
        Scanner scanner = new Scanner(System.in);

        int counter = 0;
        long maxNum = Long.MIN_VALUE;
        int counterMaxNum = 0;
        while (scanner.hasNextLong()) {
            ++counter;
            long number = scanner.nextLong();
            if (number > maxNum) {
                maxNum = number;
                counterMaxNum = 1;
            }
            else if (number == maxNum) {
                ++counterMaxNum;
            }
        }

        long occurPercentage = Math.round(((double) counterMaxNum / counter) * 100);

        System.out.printf("Total numbers: %d.%n", counter);
        System.out.printf("The greatest number: %d (%d time(s), %d",
                maxNum, counterMaxNum, occurPercentage);
        System.out.println("%).");
    }

    private static void processLines() {
        Scanner scanner = new Scanner(System.in);

        String longestStr = "";
        int maxLength = 0;
        int counter = 0;
        int counterLongestStr = 0;
        while (scanner.hasNextLine()) {
            ++counter;
            String str = scanner.nextLine();
            int length = str.length();
            if (length > maxLength || (length == maxLength && str.compareTo(longestStr) > 0)) {
                maxLength = str.length();
                longestStr = str;
                counterLongestStr = 1;
            } else if (str.equals(longestStr)) {
                ++counterLongestStr;
            }
        }

        long occurPercentage = Math.round(((double) counterLongestStr / counter) * 100);

        System.out.printf("Total lines: %d.%n", counter);
        System.out.printf("The longest line:%n%s%n", longestStr);
        System.out.printf("(%d time(s), %d", counterLongestStr, occurPercentage);
        System.out.println("%).");
    }

    private static void processWords() {
        Scanner scanner = new Scanner(System.in);

        String longestWord = "";
        int maxLength = 0;
        int counter = 0;
        int counterLongestWord = 0;
        while (scanner.hasNext()) {
            ++counter;
            String word = scanner.next();
            int length = word.length();
            if (length > maxLength || (length == maxLength && word.compareTo(longestWord) > 0)) {
                maxLength = word.length();
                longestWord = word;
                counterLongestWord = 1;
            } else if (word.equals(longestWord)) {
                ++counterLongestWord;
            }
        }

        long occurPercentage = Math.round(((double) counterLongestWord / counter) * 100);

        System.out.printf("Total words: %d.%n", counter);
        System.out.printf("The longest word: %s (%d time(s), %d",
                longestWord, counterLongestWord, occurPercentage);
        System.out.println("%).");
    }
}
