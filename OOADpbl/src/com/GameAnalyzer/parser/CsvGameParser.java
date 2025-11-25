package com.GameAnalyzer.parser;

import com.GameAnalyzer.model.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class CsvGameParser implements Parser {

    @Override
    public List<Game> parse(File file) throws IOException {
        Map<String, Game> gameMap = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = br.readLine(); // header, will be skipped if present

            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                String[] parts = line.split(",");
                if (parts.length != 4) {
                    System.out.println("Skipping invalid line: " + line);
                    continue;
                }

                String gameId = parts[0].trim();
                String playerName = parts[1].trim();
                String resultStr = parts[2].trim().toUpperCase();
                String scoreStr = parts[3].trim();

                GameResult result;
                try {
                    result = GameResult.valueOf(resultStr);
                } catch (IllegalArgumentException e) {
                    System.out.println("Skipping line with invalid result: " + line);
                    continue;
                }

                int score;
                try {
                    score = Integer.parseInt(scoreStr);
                } catch (NumberFormatException e) {
                    System.out.println("Skipping line with invalid score: " + line);
                    continue;
                }

                Game game = gameMap.computeIfAbsent(gameId, Game::new);
                Player player = new Player(playerName);
                GameRecord record = new GameRecord(game, player, result, score);
                game.addRecord(record);
            }
        }

        return new ArrayList<>(gameMap.values());
    }
}
