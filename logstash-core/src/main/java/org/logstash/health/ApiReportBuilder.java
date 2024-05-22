package org.logstash.health;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class ApiReportBuilder implements ReportBuilder<IndicatorReport> {
    @Override
    public IndicatorReport compose(final String key, final Collection<Probe> probes) {
        Status status = Status.UNKNOWN;
        final List<Diagnosis> diagnoses = new ArrayList<>(probes.size());

        for (final Probe probe : probes) {
            final ProbeCapture capture = probe.capture();
            status = capture.getStatus().reduce(status);
            capture.getDiagnosis().ifPresent(diagnoses::add);
        }

        //TODO: From which probe should it get the Details, should it be somehow merged?
        return new IndicatorReport(key, status, diagnoses, new Details(), new HashMap<>());
    }

    @Override
    public IndicatorReport combine(final String key, final Collection<IndicatorReport> components) {
        Status status = Status.UNKNOWN;
        final Map<String, IndicatorReport> indicators = new HashMap<>(components.size());

        for (final IndicatorReport report : components) {
            status = report.getStatus().reduce(status);
            indicators.merge(report.getKey(), report, this::indicatorReportWithBiggerStatus);
            report.getIndicators().forEach((name, value) -> indicators.merge(name, value, this::indicatorReportWithBiggerStatus));
        }

        //TODO: How to merge the Details?
        return new IndicatorReport(key, status, new ArrayList<>(), new Details(), indicators);
    }

    private IndicatorReport indicatorReportWithBiggerStatus(final IndicatorReport left, final IndicatorReport right) {
        if (left.getStatus().compareTo(right.getStatus()) >= 0) {
            return left;
        }

        return right;
    }
}
