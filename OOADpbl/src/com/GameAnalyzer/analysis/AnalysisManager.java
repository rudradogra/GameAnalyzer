package com.GameAnalyzer.analysis;

import com.GameAnalyzer.model.Game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AnalysisManager {
    private final List<Analyzer> analyzers = new ArrayList<>();

    public AnalysisManager(List<Analyzer> analyzers) {
        if (analyzers != null) {
            this.analyzers.addAll(analyzers);
        }
    }

    public void addAnalyzer(Analyzer analyzer) {
        if (analyzer != null) {
            analyzers.add(analyzer);
        }
    }

    public List<Analyzer> getAnalyzers() {
        return Collections.unmodifiableList(analyzers);
    }

    public List<AnalysisResult> runAll(List<Game> games) {
        List<AnalysisResult> results = new ArrayList<>();
        for (Analyzer analyzer : analyzers) {
            results.add(analyzer.analyze(games));
        }
        return results;
    }
}
