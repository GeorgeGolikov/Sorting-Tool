package sorting;

public class SortingTool {
    public static void stage4(String sortingType, String dataType,
                              String inputFile, String outputFile) {
        if ("byCount".equals(sortingType)) {
            ProcessingByCount.process(dataType, inputFile, outputFile);
        } else {
            ProcessingNaturally.process(dataType, inputFile, outputFile);
        }
    }
}
