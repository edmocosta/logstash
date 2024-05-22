package org.logstash.health;

import java.util.Optional;

class ProbeCapture {
    private final String identifier;
    private final Status status;
    private final Diagnosis diagnosis;
    private final Details details;

    ProbeCapture(
            final String identifier,
            final Status status,
            final Diagnosis diagnosis,
            final Details details
    ) {
        this.identifier = identifier;
        this.status = status;
        this.diagnosis = diagnosis;
        this.details = details;
    }

    public static ProbeCapture green(String identifier) {
        return new ProbeCapture(identifier, Status.GREEN, null, null);
    }

    public String getIdentifier() {
        return identifier;
    }

    public Status getStatus() {
        return status;
    }

    public Optional<Diagnosis> getDiagnosis() {
        return Optional.ofNullable(diagnosis);
    }

    public Optional<Details> getDetails() {
        return Optional.ofNullable(details);
    }
}
