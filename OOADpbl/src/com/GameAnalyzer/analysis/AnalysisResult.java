package com.GameAnalyzer.analysis;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class AnalysisResult {
    private final String analyzerName;
    private final Map<String, Double> metrics = new HashMap<>();
    private final Map<String, String> comments = new HashMap<>();

    public AnalysisResult(String analyzerName) {
        this.analyzerName = analyzerName;
    }

    public String getAnalyzerName() {
        return analyzerName;
    }

    public void addMetric(String key, double value) {
        metrics.put(key, value);
    }

    public void addComment(String key, String comment) {
        comments.put(key, comment);
    }

    public Map<String, Double> getMetrics() {
        return Collections.unmodifiableMap(metrics);
    }

    public Map<String, String> getComments() {
        return Collections.unmodifiableMap(comments);
    }

    @Override
    public String toString() {
        return "AnalysisResult{" +
                "analyzerName='" + analyzerName + '\'' +
                ", metrics=" + metrics +
                ", comments=" + comments +
                '}';
    }
}
