package org.logstash.health;

import java.util.List;
import java.util.Map;

public class IndicatorReport implements Report {
    private final String key;
    private final Status status;
    private final List<Diagnosis> diagnoses;
    private final Details details;
    private final Map<String, IndicatorReport> indicators;

    IndicatorReport(
            final String key,
            final Status status,
            final List<Diagnosis> diagnoses,
            final Details details,
            final Map<String, IndicatorReport> indicators
    ) {
        this.key = key;
        this.status = status;
        this.diagnoses = diagnoses;
        this.details = details;
        this.indicators = indicators;
    }

    public String getKey() {
        return key;
    }

    @Override
    public Status getStatus() {
        return status;
    }

    public List<Diagnosis> getDiagnoses() {
        return diagnoses;
    }

    public Details getDetails() {
        return details;
    }

    public Map<String, IndicatorReport> getIndicators() {
        return indicators;
    }

    @Override
    public String toString() {
        return "IndicatorReport{" +
                "key='" + key + '\'' +
                ", status=" + status +
                ", diagnoses=" + diagnoses +
                ", details=" + details +
                ", indicators=" + indicators +
                '}';
    }
}
