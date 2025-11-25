package com.GameAnalyzer.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Game {
    private final String id;
    private final List<GameRecord> records = new ArrayList<>();

    public Game(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void addRecord(GameRecord record) {
        records.add(record);
    }

    public List<GameRecord> getRecords() {
        return Collections.unmodifiableList(records);
    }

    @Override
    public String toString() {
        return "Game{id='" + id + "', records=" + records + '}';
    }
}
