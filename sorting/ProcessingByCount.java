package sorting;

import java.io.PrintWriter;
import java.util.*;

public class ProcessingByCount {
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
        Map<Long, Integer> hashMap = scanNums(inputFile);
        List<Map.Entry<Long, Integer>> entries = sortNums(hashMap);
        printSorted(entries, "numbers", outputFile);
    }

    private static void processLines(String inputFile, String outputFile) {
        Map<String, Integer> hashMap = scanLines(inputFile);
        List<Map.Entry<String, Integer>> entries = sortStr(hashMap);
        printSorted(entries, "lines", outputFile);
    }

    private static void processWords(String inputFile, String outputFile) {
        Map<String, Integer> hashMap = scanWords(inputFile);
        List<Map.Entry<String, Integer>> entries = sortStr(hashMap);
        printSorted(entries, "words", outputFile);
    }

    private static HashMap<Long, Integer> scanNums(String inputFile) {
        Scanner scanner = IOHandling.openInput(inputFile);
        HashMap<Long, Integer> hashMap = new HashMap<>();

        while (scanner.hasNext()) {
            String next = scanner.next();
            if (next.matches("[-+]?\\d+")) {
                Long key = Long.parseLong(next);
                hashMap.merge(key, 1, Integer::sum);
            } else {
                System.out.printf("%n\"%s\" is not a long. It will be skipped.%n", next);
            }
        }

        scanner.close();
        return hashMap;
    }

    private static HashMap<String, Integer> scanLines(String inputFile) {
        Scanner scanner = IOHandling.openInput(inputFile);
        HashMap<String, Integer> hashMap = new HashMap<>();

        while (scanner.hasNextLine()) {
            String key = scanner.nextLine();
            hashMap.merge(key, 1, Integer::sum);
        }

        scanner.close();
        return hashMap;
    }

    private static HashMap<String, Integer> scanWords(String inputFile) {
        Scanner scanner = IOHandling.openInput(inputFile);
        HashMap<String, Integer> hashMap = new HashMap<>();

        while (scanner.hasNext()) {
            String key = scanner.next();
            hashMap.merge(key, 1, Integer::sum);
        }

        scanner.close();
        return hashMap;
    }

    private static List<Map.Entry<Long, Integer>> sortNums(Map<Long, Integer> hashMap) {
        List<Map.Entry<Long, Integer>> entries = new LinkedList<>(hashMap.entrySet());

//        entries.sort(Comparator.comparingInt(Map.Entry::getValue));
        entries.sort((e1, e2) -> {
            // sort byCount
            int val1 = e1.getValue();
            int val2 = e2.getValue();
            if (val1 != val2) return val1 - val2;

            // sort naturally if values equal
            long key1 = e1.getKey();
            long key2 = e2.getKey();
            if (key1 - key2 > 0) return 1;
            if (key1 - key2 == 0) return 0;
            return -1;
        });

        return entries;
    }

    private static List<Map.Entry<String, Integer>> sortStr(Map<String, Integer> hashMap) {
        List<Map.Entry<String, Integer>> entries = new LinkedList<>(hashMap.entrySet());

        entries.sort((e1, e2) -> {
            // sort byCount
            int val1 = e1.getValue();
            int val2 = e2.getValue();
            if (val1 != val2) return val1 - val2;

            // sort naturally if values equal
            return e1.getKey().compareTo(e2.getKey());
        });

        return entries;
    }

    private static <T> void printSorted(List<Map.Entry<T, Integer>> data, String dataType, String outputFile) {
        PrintWriter printWriter = IOHandling.openOutput(outputFile);

//        int sum = 0;
//        data.forEach(e -> sum += e.getValue());

        int totalNums = data.stream().mapToInt(Map.Entry::getValue).sum();
        printWriter.printf("Total %s: %d.%n", dataType, totalNums);

        data.forEach((e) -> {
            int val = e.getValue();
            long occurPercentage = Math.round(((double) val / totalNums) * 100);
            printWriter.printf("%s: %d time(s), %d", e.getKey(), val, occurPercentage);
            printWriter.println("%");
        });

        printWriter.close();
    }
}
