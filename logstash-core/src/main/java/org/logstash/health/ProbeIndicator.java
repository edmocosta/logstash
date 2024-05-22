package org.logstash.health;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

abstract class ProbeIndicator<T extends Report> implements Indicator<T> {
    private final Set<Probe> probes = new HashSet<>();

    protected void addProbe(final Probe probe) {
        probes.add(probe);
    }

    protected Set<Probe> getProbes() {
        return Collections.unmodifiableSet(probes);
    }

    @Override
    public T getReport(final ReportBuilder<T> builder) {
        return builder.compose(identifier(), Collections.unmodifiableSet(probes));
    }
}
