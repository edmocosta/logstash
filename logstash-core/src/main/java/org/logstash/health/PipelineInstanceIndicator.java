package org.logstash.health;

public class PipelineInstanceIndicator extends ProbeIndicator<IndicatorReport> {

    private final String pipeline;

    public PipelineInstanceIndicator(final String pipeline, final Status status) {
        this.pipeline = pipeline;
        addProbe(new Probe() {
            @Override
            public String identifier() {
                return pipeline;
            }

            @Override
            public ProbeCapture capture() {
                Diagnosis diagnosis = null;
                if (status != Status.GREEN) {
                    diagnosis = new Diagnosis(String.format("Pipeline %s is unhealthy", pipeline), "Restart Logstash", "https://foo.bar/restart");
                }

                return new ProbeCapture("pipeline_health", status, diagnosis, null);
            }
        });
    }

    @Override
    public String identifier() {
        return pipeline;
    }
}