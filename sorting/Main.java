package sorting;

public class Main {

    public static void main(final String[] args) {
        try {
            String sortingType = "default";
            String dataType = "default";
            String pathToInputFile = "";
            String pathToOutputFile = "";

            // parse command-line args
            for (int i = 0; i < args.length; ++i) {
                switch(args[i]) {
                    case "-sortingType":
                        if (i != args.length - 1) {
                            if (args[i + 1].equals("byCount")) {
                                sortingType = "byCount";
                                ++i;
                                continue;
                            } else if (args[i + 1].equals("natural")) {
                                ++i;
                                continue;
                            } else {
                                System.out.printf("%n\"%s\" is not a valid parameter. It will be skipped.%n",
                                        args[i + 1]);
                                ++i;
                            }
                        } else {
                            throw new RuntimeException("No sorting type defined!");
                        }
                        break;
                    case "-dataType":
                        if (i != args.length - 1) {
                            switch (args[i + 1]) {
                                case "long":
                                case "line":
                                case "word":
                                    dataType = args[i + 1];
                                    ++i;
                                    break;
                                default:
                                    System.out.printf("%n\"%s\" is not a valid parameter. It will be skipped.%n",
                                            args[i + 1]);
                                    ++i;
                            }
                        } else {
                            throw new RuntimeException("No data type defined!");
                        }
                        break;
                    case "-inputFile":
                        if (i != args.length - 1) {
                            pathToInputFile = args[i + 1];
                            ++i;
                        } else {
                            throw new RuntimeException("No input file defined!");
                        }
                        break;
                    case "-outputFile":
                        if (i != args.length - 1) {
                            pathToOutputFile = args[i + 1];
                            ++i;
                        } else {
                            throw new RuntimeException("No output file defined!");
                        }
                        break;
                    default:
                        System.out.printf("%n\"%s\" is not a valid parameter. It will be skipped.%n",
                                args[i]);
                }
            }

            SortingTool.stage4(sortingType, dataType, pathToInputFile, pathToOutputFile);
        }
        catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
}
