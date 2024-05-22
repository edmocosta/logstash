package org.logstash.health;

interface Indicator<T extends Report> {
    String identifier();

    T getReport(final ReportBuilder<T> builder);
}
