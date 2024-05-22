package org.logstash.health;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

abstract class MultiIndicator<T extends Report> implements Indicator<T> {
    private final Set<Indicator<T>> indicators = new HashSet<>();

    @Override
    public T getReport(final ReportBuilder<T> builder) {
        return indicators
                .stream()
                .map(indicator -> indicator.getReport(builder))
                .collect(Collectors.collectingAndThen(Collectors.toList(), reports -> builder.combine(identifier(), reports)));
    }

    protected Set<Indicator<T>> getIndicators() {
        return indicators;
    }

    protected boolean addIndicator(final Indicator<T> indicator) {
        return indicators.add(indicator);
    }
}