package com.GameAnalyzer.analysis;

import com.GameAnalyzer.model.Game;
import com.GameAnalyzer.model.GameRecord;
import com.GameAnalyzer.model.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AverageScoreAnalyzer implements Analyzer {

    @Override
    public String getName() {
        return "AverageScoreAnalyzer";
    }

    @Override
    public AnalysisResult analyze(List<Game> games) {
        Map<Player, Integer> totalScore = new HashMap<>();
        Map<Player, Integer> totalGames = new HashMap<>();

        for (Game game : games) {
            for (GameRecord record : game.getRecords()) {
                Player player = record.getPlayer();
                totalScore.put(player, totalScore.getOrDefault(player, 0) + record.getScore());
                totalGames.put(player, totalGames.getOrDefault(player, 0) + 1);
            }
        }

        AnalysisResult result = new AnalysisResult(getName());

        for (Player player : totalGames.keySet()) {
            int score = totalScore.get(player);
            int gamesCount = totalGames.get(player);
            double avg = gamesCount == 0 ? 0.0 : (double) score / gamesCount;
            result.addMetric(player.getName() + "_avgScore", avg);
            result.addComment(player.getName(), String.format("Average score: %.2f", avg));
        }

        return result;
    }
}
