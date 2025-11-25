package com.GameAnalyzer.util;

import com.GameAnalyzer.model.Game;
import com.GameAnalyzer.model.GameRecord;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class CsvAppender {

    /**
     * Appends two lines for the given Game (one per GameRecord) to the CSV file.
     * The CSV format expected: gameId,playerName,result,score
     */
    public static void appendGame(Path csvPath, Game game) throws IOException {
        StringBuilder sb = new StringBuilder();
        for (GameRecord record : game.getRecords()) {
            // ensure no embedded commas — if names can contain commas, you should escape/quote them
            sb.append(record.getGame().getId())
                    .append(",")
                    .append(record.getPlayer().getName())
                    .append(",")
                    .append(record.getResult().name())
                    .append(",")
                    .append(record.getScore())
                    .append(System.lineSeparator());
        }

        // create file if not present, append otherwise
        Files.write(csvPath, sb.toString().getBytes(StandardCharsets.UTF_8),
                StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }
}
