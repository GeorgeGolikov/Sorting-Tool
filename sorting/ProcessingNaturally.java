package sorting;

import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class ProcessingNaturally {
    public static void process(String type, String inputFile, String outputFile) {
        switch (type) {
            case "long":
                processNums(inputFile, outputFile);
                break;
            case "line":
                processLines(inputFile, outputFile);
                break;
            default:
                processWords(inputFile, outputFile);
        }
    }

    private static void processNums(String inputFile, String outputFile) {
        List<Long> list = scanNums(inputFile);
        sortNums(list);
        printSorted(list, "numbers", outputFile);
    }

    private static void processLines(String inputFile, String outputFile) {
        List<String> list = scanLines(inputFile);
        sortStr(list);
        printSorted(list, "lines", outputFile);
    }

    private static void processWords(String inputFile, String outputFile) {
        List<String> list = scanWords(inputFile);
        sortStr(list);
        printSorted(list, "words", outputFile);
    }

    private static LinkedList<Long> scanNums(String inputFile) {
        Scanner scanner = IOHandling.openInput(inputFile);
        LinkedList<Long> input = new LinkedList<>();

        while (scanner.hasNext()) {
            String next = scanner.next();
            if (next.matches("[-+]?\\d+")) {
                input.add(Long.parseLong(next));
            } else {
                System.out.printf("%n\"%s\" is not a long. It will be skipped.%n", next);
            }
        }

        scanner.close();
        return input;
    }

    private static LinkedList<String> scanLines(String inputFile) {
        Scanner scanner = IOHandling.openInput(inputFile);
        LinkedList<String> input = new LinkedList<>();

        while (scanner.hasNextLine()) {
            input.add(String.format("%n%s", scanner.nextLine()));
        }

        scanner.close();
        return input;
    }

    private static LinkedList<String> scanWords(String inputFile) {
        Scanner scanner = IOHandling.openInput(inputFile);
        LinkedList<String> input = new LinkedList<>();

        while (scanner.hasNext()) {
            input.add(scanner.next());
        }

        scanner.close();
        return input;
    }

    private static void sortNums(List<Long> input) {
        input.sort((n1, n2) -> {
            if (n1 - n2 > 0) return 1;
            if (n1 - n2 == 0) return 0;
            return -1;
        });
    }

    private static void sortStr(List<String> input) {
        input.sort((s1, s2) -> s1.compareTo(s2));
    }

    private static <T> void printSorted(List<T> data, String dataType, String outputFile) {
        PrintWriter printWriter = IOHandling.openOutput(outputFile);

        printWriter.printf("Total %s: %d.%n", dataType, data.size());
        printWriter.print("Sorted data:");
        for (var val : data) {
            printWriter.printf(" %s", val);
        }
        printWriter.println("\n");

        printWriter.close();
    }
}
