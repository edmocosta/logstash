package org.logstash.health;

class ManagedPipelinesIndicator extends MultiIndicator<IndicatorReport> {
    public ManagedPipelinesIndicator() {
        addIndicator(new PipelineInstanceIndicator("pipeline-one", Status.GREEN));
        addIndicator(new PipelineInstanceIndicator("pipeline-two", Status.RED));
    }

    @Override
    public String identifier() {
        return "pipelines";
    }
}
