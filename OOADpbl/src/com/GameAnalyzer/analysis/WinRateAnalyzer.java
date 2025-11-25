package com.GameAnalyzer.analysis;

import com.GameAnalyzer.model.Game;
import com.GameAnalyzer.model.GameRecord;
import com.GameAnalyzer.model.GameResult;
import com.GameAnalyzer.model.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WinRateAnalyzer implements Analyzer {

    @Override
    public String getName() {
        return "WinRateAnalyzer";
    }

    @Override
    public AnalysisResult analyze(List<Game> games) {
        Map<Player, Integer> wins = new HashMap<>();
        Map<Player, Integer> total = new HashMap<>();

        for (Game game : games) {
            for (GameRecord record : game.getRecords()) {
                Player player = record.getPlayer();
                total.put(player, total.getOrDefault(player, 0) + 1);
                if (record.getResult() == GameResult.WIN) {
                    wins.put(player, wins.getOrDefault(player, 0) + 1);
                }
            }
        }

        AnalysisResult result = new AnalysisResult(getName());

        for (Player player : total.keySet()) {
            int totalGames = total.get(player);
            int winGames = wins.getOrDefault(player, 0);
            double winRate = totalGames == 0 ? 0.0 : (winGames * 100.0) / totalGames;
            result.addMetric(player.getName() + "_winRate", winRate);
            result.addComment(player.getName(), String.format("Win rate: %.2f%% (%d/%d)", winRate, winGames, totalGames));
        }

        return result;
    }
}
