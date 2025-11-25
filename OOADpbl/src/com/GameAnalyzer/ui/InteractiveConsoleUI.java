package com.GameAnalyzer.ui;

import com.GameAnalyzer.model.Game;
import com.GameAnalyzer.model.GameRecord;
import com.GameAnalyzer.model.GameResult;
import com.GameAnalyzer.model.Player;

import java.util.*;
import java.util.stream.Collectors;

public class InteractiveConsoleUI {

    /**
     * Prompts user to add a game. Returns the newly created Game (and adds it to games list),
     * or returns null if user declined to add.
     */
    public Game promptAddNewGame(Scanner scanner, List<Game> games) {
        System.out.print("Do you want to add a new 1v1 basketball game? (y/n): ");
        String answer = scanner.nextLine().trim().toLowerCase(Locale.ROOT);
        if (!answer.equals("y") && !answer.equals("yes")) {
            return null;
        }

        // Generate next game id
        String nextId = generateNextGameId(games);
        System.out.println("Generated game id: " + nextId);

        // Ask for player names (winner then opponent)
        System.out.print("Enter winner's name: ");
        String winnerName = scanner.nextLine().trim();
        while (winnerName.isEmpty()) {
            System.out.print("Winner name cannot be empty. Enter winner's name: ");
            winnerName = scanner.nextLine().trim();
        }

        System.out.print("Enter opponent's name: ");
        String opponentName = scanner.nextLine().trim();
        while (opponentName.isEmpty() || opponentName.equalsIgnoreCase(winnerName)) {
            if (opponentName.isEmpty()) {
                System.out.print("Opponent name cannot be empty. Enter opponent's name: ");
            } else {
                System.out.print("Opponent cannot have same name as winner. Enter opponent's name: ");
            }
            opponentName = scanner.nextLine().trim();
        }

        // Ask only for the opponent's points
        Integer opponentPoints = null;
        while (opponentPoints == null) {
            System.out.print("Enter opponent's final points (integer >= 0): ");
            String p = scanner.nextLine().trim();
            try {
                int val = Integer.parseInt(p);
                if (val < 0) {
                    System.out.println("Points must be >= 0.");
                } else {
                    opponentPoints = val;
                }
            } catch (NumberFormatException ex) {
                System.out.println("Please enter a valid integer.");
            }
        }

        // Compute winner points (to-21 rule with overtime)
        int winnerPoints = (opponentPoints >= 21) ? (opponentPoints + 1) : 21;

        // Build Game and two GameRecord objects
        Game newGame = new Game(nextId);
        Player winner = new Player(winnerName);
        Player opponent = new Player(opponentName);

        GameRecord winnerRecord = new GameRecord(newGame, winner, GameResult.WIN, winnerPoints);
        GameRecord opponentRecord = new GameRecord(newGame, opponent, GameResult.LOSE, opponentPoints);

        newGame.addRecord(winnerRecord);
        newGame.addRecord(opponentRecord);

        games.add(newGame);

        System.out.println("New game added:");
        System.out.println(newGame);
        System.out.printf("  %s (WIN) = %d  vs  %s (LOSE) = %d%n",
                winnerName, winnerPoints, opponentName, opponentPoints);

        Set<String> players = games.stream()
                .flatMap(g -> g.getRecords().stream())
                .map(r -> r.getPlayer().getName())
                .collect(Collectors.toCollection(TreeSet::new));
        System.out.println("All players in dataset: " + players);

        return newGame;
    }

    private String generateNextGameId(List<Game> games) {
        // Try numeric incremental ID first
        int maxId = -1;
        boolean foundNumeric = false;
        for (Game g : games) {
            String id = g.getId();
            try {
                int n = Integer.parseInt(id);
                foundNumeric = true;
                if (n > maxId) maxId = n;
            } catch (NumberFormatException ignored) {
                // non-numeric id - skip
            }
        }
        if (foundNumeric) {
            return String.valueOf(maxId + 1);
        } else {
            // fallback: short UUID
            return UUID.randomUUID().toString();
        }
    }
}

