package com.GameAnalyzer.analysis;

import com.GameAnalyzer.model.Game;

import java.util.List;

public interface Analyzer {
    String getName();
    AnalysisResult analyze(List<Game> games);
}
