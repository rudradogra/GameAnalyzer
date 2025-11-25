package com.GameAnalyzer.ui;

import com.GameAnalyzer.analysis.AnalysisResult;
import com.GameAnalyzer.model.Game;

import java.util.List;
import java.util.Map;

public class ConsoleUI {

    public void showGames(List<Game> games) {
        System.out.println("=== Loaded Games ===");
        for (Game g : games) {
            System.out.println(g);
        }
        System.out.println();
    }

    public void showAnalysis(List<AnalysisResult> results) {
        System.out.println("=== Analysis Results ===");
        for (AnalysisResult result : results) {
            System.out.println("Analyzer: " + result.getAnalyzerName());
            for (Map.Entry<String, Double> metric : result.getMetrics().entrySet()) {
                System.out.println("  Metric " + metric.getKey() + " = " + metric.getValue());
            }
            for (Map.Entry<String, String> comment : result.getComments().entrySet()) {
                System.out.println("  Comment for " + comment.getKey() + ": " + comment.getValue());
            }
            System.out.println();
        }
    }
}
