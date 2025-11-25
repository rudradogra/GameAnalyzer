package com.GameAnalyzer;

import com.GameAnalyzer.analysis.*;
import com.GameAnalyzer.model.Game;
import com.GameAnalyzer.parser.CsvGameParser;
import com.GameAnalyzer.parser.Parser;
import com.GameAnalyzer.ui.ConsoleUI;
import com.GameAnalyzer.util.CsvAppender;

import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class App {

    public static void main(String[] args) {
        if (args.length == 0) {

            System.out.println("Usage: java -jar GameAnalyzer.jar <path_to_csv_file>");
            System.out.println("Or run with program argument: /absolute/path/to/games.csv");
            return;
        }

        String path = args[0];
        File csvFile = new File(path);
        if (!csvFile.exists()) {
            System.out.println("File not found: " + csvFile.getAbsolutePath());
            return;
        }

        Parser parser = new CsvGameParser();
        // after parsing
        List<Game> games;
        try {
            games = parser.parse(csvFile);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return;
        }

        try (Scanner scanner = new Scanner(System.in)) {
            com.GameAnalyzer.ui.InteractiveConsoleUI interactiveUI = new com.GameAnalyzer.ui.InteractiveConsoleUI();
            // promptAddNewGame now returns the newly added Game (or null if none added)
            com.GameAnalyzer.model.Game added = interactiveUI.promptAddNewGame(scanner, games);

            // if a game was added, append it to the same CSV file we loaded (csvFile)
            if (added != null) {
                try {
                    CsvAppender.appendGame(csvFile.toPath(), added);
                    System.out.println("Appended new game to CSV: " + csvFile.getAbsolutePath());
                } catch (IOException ioe) {
                    System.out.println("Warning: could not append to CSV: " + ioe.getMessage());
                }
            }
        }

        Analyzer winRateAnalyzer = new WinRateAnalyzer();


        Analyzer averageScoreAnalyzer = new AverageScoreAnalyzer();

        AnalysisManager manager = new AnalysisManager(Arrays.asList(
                winRateAnalyzer,
                averageScoreAnalyzer
        ));

        List<AnalysisResult> results = manager.runAll(games);

        ConsoleUI ui = new ConsoleUI();
        ui.showGames(games);
        ui.showAnalysis(results);
    }
}
