package org.logstash.health;

public class Diagnosis {
    private final String cause;
    private final String action;
    private final String helpUrl;

    Diagnosis(final String cause, final String action, final String helpUrl) {
        this.cause = cause;
        this.action = action;
        this.helpUrl = helpUrl;
    }

    public String getCause() {
        return cause;
    }

    public String getAction() {
        return action;
    }

    public String getHelpUrl() {
        return helpUrl;
    }
}
