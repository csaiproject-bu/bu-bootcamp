import java.io.*;
import java.util.ArrayList;

public class GradeAnalyzer {

    private static int invalidLineCount = 0;

    /**
     * Main method.
     * Takes input scores.txt , reports.txt files and a boolean flag as arguments.
     * @param args
     *
     * 1. Read data from reports.txt file
     * 2. Checks if the file has contents and filters valid data.
     * 3. Generate statics.
     * 4. Generates a report using this data, prints to console and saves to reports.txt file.
     * 5. Raises and catches exceptions where necessary.
     *
     */

    public static void main(String[] args) {

        if (args.length != 3) {
            System.err.println("Usage: java GradeAnalyzer scores.txt reports.txt true/false.");
            System.err.println("false-static hardcoded data, true- data fetched from reports.txt file");
            System.exit(1); // Exit with error code
        }
        boolean flag = Boolean.parseBoolean(args[2]);
        ArrayList<Integer> scores = new ArrayList<>();
        if (flag) {
            // Step 3: reading scores from input scores txt file
            scores = readScores(args[0]);
        } else {
            // Testing using hardcoded list:
            scores = testData();
            System.out.println("Average Score from Hardcoded list: " + calculateAverage(testData()));            
        }

        // Step 4: calculate statistics

        if (scores.isEmpty())
        {
            System.out.println("No valid scores to report.");
            System.out.println("Average score not calcualted : " + GradeAnalyzer.calculateAverage(scores));
        }
        else
        {
            //System.out.println(String.format("Average score from "+args[0]+" file data : %8.2f", GradeAnalyzer.calculateAverage(scores)));
            //Step 5: Find the Highest and Lowest Scores
            int highest = Integer.MIN_VALUE;
            int lowest = Integer.MAX_VALUE;
            for (int score : scores)
                { if (score > highest) { highest = score; }
                if (score < lowest) { lowest = score; } }
            
            //Step 6: Implement writeReport
            //Step 6a: Build report lines:
            ArrayList<String> reportLines = new ArrayList<>();
            reportLines = buildReportLines(scores, GradeAnalyzer.calculateAverage(scores), highest, lowest);
            System.out.println("\n\n");
            //Step 6b: print report lines:
            printToConsole(reportLines);
            //Step 6c: write to report.txt file:
            writeReport(reportLines, args[1]);
        }
    }

    // Test Data - hardcoded.
    public static ArrayList<Integer> testData() {
        // ArrayList with initial capacity of 10
        ArrayList<Integer> averagesList = new ArrayList<>(10);
        averagesList.add(99);
        averagesList.add(80);
        averagesList.add(65);
        averagesList.add(53);
        averagesList.add(90);
        averagesList.add(88);
        averagesList.add(58);
        averagesList.add(97);
        averagesList.add(95);
        return averagesList;
    }

    // Returns a list of valid scores read from the file
    //Step 3
    public static ArrayList<Integer> readScores(String filename) {
        ArrayList<Integer> scores = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            int lineNumber = 0;
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                line = line.trim();
                if (line.isEmpty()) {
                    continue; // skip blank lines
                }

                try {
                    int score = Integer.parseInt(line);
                    if (score < 0 || score > 100) {
                        System.out.printf(
                                "Warning: skipped out-of-range score on line %d: %s%n",
                                lineNumber, line);
                        invalidLineCount++;
                    } else {
                    scores.add(score);
                    }
                } catch (NumberFormatException e) {
                       System.out.printf(
                                "Warning: skipped Invalid number on line %d: %s%n",
                                lineNumber, line);
                    invalidLineCount++;
                }
            }

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return scores;
    }

    // Returns the average of a list of scores, or 0.0 if the list is empty
    //Step 4
    public static double calculateAverage(ArrayList<Integer> scores) {
        // If the list is empty, return 0.0 immediately
        if (scores.isEmpty()) {
            return 0.0;
        }

        // Loop through all scores and accumulate the total in a double
        double total = 0.0;
        for (int score : scores) {
            total += score;
        }

        // Return the total divided by scores.size()
        return total / scores.size();
    }

    
    public static ArrayList<String> buildReportLines(ArrayList<Integer> scores,
            double avg, int high, int low) {
        ArrayList<String> lines = new ArrayList<>();

        //Calculating Grade bands.
        int countA = 0;
        int countB = 0;
        int countC = 0;
        int countD = 0;
        int countF = 0;

        for (int score : scores) {
            if (score >= 90) {
                countA++;
            } else if (score >= 80) {
                countB++;
            } else if (score >= 70) {
                countC++;
            } else if (score >= 60) {
                countD++;
            } else {
                countF++;
            }
        }


        lines.add("");
        lines.add("=== Grade Analysis Report ===");
        lines.add("");

        lines.add("");
        lines.add(String.format("Total scores processed: %3d", scores.size()));
        lines.add(String.format("Invalid data lines skipped:  %3d", invalidLineCount));
        lines.add("");

        lines.add("******************************************");

        lines.add(String.format("Average score: %8.2f", avg));
        lines.add(String.format("Highest score: %8d", high));
        lines.add(String.format("Lowest score:  %8d", low));
        lines.add("");
        lines.add("******************************************");

        lines.add("Grade distribution:");
        lines.add(String.format("  A (90-100):   %d", countA));
        lines.add(String.format("  B (80-89):    %d", countB));
        lines.add(String.format("  C (70-79):    %d", countC));
        lines.add(String.format("  D (60-69):    %d", countD));
        lines.add(String.format("  F (below 60): %d", countF));
        lines.add("");
        return lines;
    }

    // Writes to standard console.
    public static void printToConsole(ArrayList<String> reportLines) {
        for (String line : reportLines) {
            System.out.println(line);
        }
    }

    // Writes the report to report.txt using BufferedWriter/FileWriter.
    public static void writeReport(ArrayList<String> reportLines, String outputFileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {
            for (String line : reportLines) {
                writer.write(String.format("%s%n", line));
            }
            System.out.println("Report successfully written to " + outputFileName);
        } catch (IOException e) {
            System.out.println("Error writing report: " + e.getMessage());
        }
    }

    
}
