package org.logstash.health;

interface Probe {
    String identifier();

    ProbeCapture capture();
}
