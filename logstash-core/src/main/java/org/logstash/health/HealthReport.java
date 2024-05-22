package org.logstash.health;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class HealthReport {
    private Status status = Status.UNKNOWN;
    private final Map<String, IndicatorReport> indicators = new HashMap<>();

    private HealthReport() {
        // empty
    }

    public Status getStatus() {
        return status;
    }

    public Map<String, IndicatorReport> getIndicators() {
        return Collections.unmodifiableMap(indicators);
    }

    void addIndicator(final String indicator, final IndicatorReport report) {
        indicators.put(indicator, report);
        status = status.reduce(report.getStatus());
    }

    @Override
    public String toString() {
        return "HealthReport{" + "status=" + status + ", indicators=" + indicators + '}';
    }

    static Builder builder(ReportBuilder<IndicatorReport> reportBuilder) {
        return new Builder(reportBuilder);
    }

    public static class Builder {
        private final Set<Indicator<IndicatorReport>> indicators = new HashSet<>();
        private final ReportBuilder<IndicatorReport> reportBuilder;

        Builder(ReportBuilder<IndicatorReport> reportBuilder) {
            this.reportBuilder = reportBuilder;
        }

        Builder withIndicator(Indicator<IndicatorReport> indicator) {
            this.indicators.add(indicator);
            return this;
        }

        HealthReport build() {
            final HealthReport healthReport = new HealthReport();

            indicators.stream()
                    .map(reportBuilder::buildReport)
                    .forEach(p -> healthReport.addIndicator(p.getKey(), p));

            return healthReport;
        }
    }
}
