package com.GameAnalyzer.model;

public class GameRecord {
    private final Game game;
    private final Player player;
    private final GameResult result;
    private final int score;

    public GameRecord(Game game, Player player, GameResult result, int score) {
        this.game = game;
        this.player = player;
        this.result = result;
        this.score = score;
    }

    public Game getGame() {
        return game;
    }

    public Player getPlayer() {
        return player;
    }

    public GameResult getResult() {
        return result;
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "GameRecord{" +
                "game=" + game.getId() +
                ", player=" + player.getName() +
                ", result=" + result +
                ", score=" + score +
                '}';
    }
}
