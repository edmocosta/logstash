package org.logstash.health;

import java.util.Collection;

interface ReportBuilder<T extends Report> {
    default T buildReport(final Indicator<T> indicator) {
        return indicator.getReport(this);
    }

    T compose(final String key, final Collection<Probe> probes);

    T combine(final String key, final Collection<T> components);
}
