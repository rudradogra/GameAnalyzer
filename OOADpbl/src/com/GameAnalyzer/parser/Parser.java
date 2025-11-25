package com.GameAnalyzer.parser;

import com.GameAnalyzer.model.Game;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface Parser {
    List<Game> parse(File file) throws IOException;
}
